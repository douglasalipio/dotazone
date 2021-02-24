package br.com.dotazone.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import br.com.dotazone.DotaZoneBrain
import br.com.dotazone.R
import br.com.dotazone.model.entity.Hero
import br.com.dotazone.model.entity.Item
import br.com.dotazone.model.listeners.BuildHeroAction
import br.com.dotazone.model.service.AdapterAction
import br.com.dotazone.model.service.ItemAsync
import br.com.dotazone.model.util.UrlUtils
import br.com.dotazone.view.activity.BaseActivity
import br.com.dotazone.view.activity.BuildHeroActivity
import br.com.dotazone.view.adapter.ItemGridAdapter
import br.com.dotazone.view.fragment.DialogError.TypeError
import kotlinx.android.synthetic.main.item_dialog_view.*
import kotlinx.android.synthetic.main.tab_grid_item_view.*
import java.util.*


class ItemFragment : Fragment(), AdapterAction, OnItemClickListener {
	private var mContent: String? = "???"
	private var mActivity: BuildHeroActivity? = null
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		if (savedInstanceState != null && savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getString(KEY_CONTENT)
		}
		if (DotaZoneBrain.items.isEmpty()) ItemAsync(this).execute() else initList()
		return inflater.inflate(R.layout.tab_grid_item_view, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		gridViewItem.onItemClickListener = this
	}

	override fun initList() {
		val itemsForTab: MutableList<Item> = ArrayList()

		// Valida se existe item a ser tratado
		if (DotaZoneBrain.items != null && DotaZoneBrain.items.isNotEmpty()) {
			when (mContent) {
				getString(R.string.item_secret_store) -> {
					for (item in DotaZoneBrain.items) {
						if (item.qual == "secret_shop") {
							itemsForTab.add(item)
						}
					}
				}
				getString(R.string.item_improvements) -> {
					for (item in DotaZoneBrain.items) {
						if (item.components.isNotEmpty()) {
							itemsForTab.add(item)
						}
					}
				}
				getString(R.string.item_basic) -> {
					for (item in DotaZoneBrain.items) {
						if (item.qual == "common" || item.qual == "component") {
							itemsForTab.add(item)
						}
					}
				}
			}
			gridViewItem.adapter = ItemGridAdapter(activity, itemsForTab)
			if (mActivity != null) {
				val buildAction: BuildHeroAction = mActivity as BuildHeroActivity
				buildAction.verifyPayment()
			}
		} else {
			val fragmentError = DialogError.newFragmentDialog(activity!!.resources.getString(R.string.error_json_item),
					activity as BaseActivity, TypeError.ONE_OPTIONS)
			fragmentError.show(activity!!.supportFragmentManager, "Item")
		}
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.putString(KEY_CONTENT, mContent)
	}

	override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
		if (mActivity != null) {
			val buildAction: BuildHeroAction = mActivity as BuildHeroActivity
			buildAction.addingItemSlot(parent.adapter.getItem(position) as Item)
		} else {
			// custom dialog
			val dialog = Dialog(activity!!, R.style.FullHeightDialog)
			dialog.setContentView(R.layout.item_dialog_view)
			val itemName = dialog.textItemName
			val itemAttribute = dialog.textAttribute
			val itemCd = dialog.textCdTimer
			val itemCost = dialog.textCost
			val itemDescription = dialog.textDescription
			val itemMana = dialog.textMana
			val itemIcon = dialog.imageItem
			val itemLayoutComponents = dialog.itemLayoutComponents
			val item = parent.adapter.getItem(position) as Item
			itemName.text = item.name
			itemAttribute.text = Html.fromHtml(item.attribute)
			val cd = if (item.cloudown == "false") requireActivity().resources.getString(R.string.item_no_cd) else item.cloudown
			val mana = if (item.mc == "false") requireActivity().resources.getString(R.string.item_no_cd) else item.mc
			itemCd.text = cd
			itemCost.text = item.cost.toString()
			itemDescription.text = Html.fromHtml(item.description)
			itemMana.text = mana
			val pos = item.imageName.lastIndexOf('.')
			val idImage = activity!!.resources.getIdentifier(item.imageName.substring(0, pos), "drawable",
					activity!!.packageName)
			itemIcon.setImageResource(idImage)
			setImageComponents(item, itemLayoutComponents)
			dialog.show()
		}
	}

	private fun setImageComponents(item: Item, linearLayout: LinearLayout) {
		if (item.components.isNotEmpty()) {
			for (itemName in item.components) {
				val idImage = activity!!.resources.getIdentifier(itemName + "_lg", "drawable", activity!!.packageName)
				if (idImage != 0) {
					val imageView = ImageView(activity)
					imageView.setImageResource(idImage)
					linearLayout.addView(imageView)
					imageView.layoutParams = LinearLayout.LayoutParams(UrlUtils.convertDpToPixel(50f,
							resources), UrlUtils.convertDpToPixel(32f, resources))
				}
			}
		}
	}

	override fun initListItem(items: List<Item>) {}
	override fun initListIHero(heroes: List<Hero>) {}
	override fun initRating() {}

	companion object {
		private const val KEY_CONTENT = "TestFragment:Content"
		fun newInstance(content: String?): ItemFragment {
			val f = ItemFragment()
			f.mContent = content
			return f
		}

		fun newInstance(content: String?, activity: BuildHeroActivity?): ItemFragment {
			val f = ItemFragment()
			f.mContent = content
			f.mActivity = activity
			return f
		}
	}
}
