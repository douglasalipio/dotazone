package com.br.dotazone.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import com.br.dotazone.DotaZoneBrain
import com.br.dotazone.R
import com.br.dotazone.databinding.ItemDialogViewBinding
import com.br.dotazone.databinding.TabGridItemViewBinding
import com.br.dotazone.domain.heroes.prov.Hero
import com.br.dotazone.domain.heroes.prov.Item
import com.br.dotazone.model.listeners.BuildHeroAction
import com.br.dotazone.model.service.AdapterAction
import com.br.dotazone.model.util.UrlUtils
import com.br.dotazone.view.activity.BaseActivity
import com.br.dotazone.view.activity.BuildHeroActivity
import com.br.dotazone.view.adapter.ItemGridAdapter
import com.br.dotazone.view.fragment.DialogError.TypeError
import java.util.*


class ItemFragment : Fragment(), AdapterAction, OnItemClickListener {
	private var mContent: String? = "???"
	private var mActivity: BuildHeroActivity? = null
	private var binding: TabGridItemViewBinding? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		if (savedInstanceState != null && savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getString(KEY_CONTENT)
		}
		val view = inflater.inflate(R.layout.tab_grid_item_view, container, false)
		//if (DotaZoneBrain.items.isEmpty()) ItemAsync(this).execute() else initList(view)
		return view
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val binding = TabGridItemViewBinding.bind(view)
		this.binding = binding
		binding.gridViewItem.onItemClickListener = this
	}

	override fun initList(view: View?) {
		val itemsForTab: MutableList<Item> = ArrayList()

		// Valida se existe item a ser tratado
		if (DotaZoneBrain.items.isNotEmpty()) {
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
			binding?.gridViewItem?.adapter = ItemGridAdapter(requireActivity(), itemsForTab)
			if (mActivity != null) {
				val buildAction: BuildHeroAction = mActivity as BuildHeroActivity
				buildAction.verifyPayment()
			}
		} else {
			val fragmentError = DialogError.newFragmentDialog(requireActivity().resources.getString(R.string.error_json_item),
					activity as BaseActivity, TypeError.ONE_OPTIONS)
			fragmentError.show(requireActivity().supportFragmentManager, "Item")
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
			val inflater = LayoutInflater.from(context)
			val binding = ItemDialogViewBinding.inflate(inflater)

			val dialog = Dialog(requireActivity(), R.style.FullHeightDialog)
			dialog.setContentView(R.layout.item_dialog_view)
			val item = parent.adapter.getItem(position) as Item
			binding.textItemName.text = item.name
			binding.textAttribute.text = Html.fromHtml(item.attribute)
			val cd = if (item.cloudown == "false") requireActivity().resources.getString(R.string.item_no_cd) else item.cloudown
			val mana = if (item.mc == "false") requireActivity().resources.getString(R.string.item_no_cd) else item.mc
			binding.textCdTimer.text = cd
			binding.textCost.text = item.cost.toString()
			binding.textDescription.text = Html.fromHtml(item.description)
			binding.textMana.text = mana
			val pos = item.imageName.lastIndexOf('.')
			val idImage = requireActivity().resources.getIdentifier(item.imageName.substring(0, pos), "drawable",
					requireActivity().packageName)
			binding.imageItem.setImageResource(idImage)
			setImageComponents(item, binding.itemLayoutComponents)
			dialog.show()
		}
	}

	private fun setImageComponents(item: Item, linearLayout: LinearLayout) {
		if (item.components.isNotEmpty()) {
			for (itemName in item.components) {
				val idImage = requireActivity().resources.getIdentifier(itemName + "_lg", "drawable", requireActivity().packageName)
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

	override fun onDestroy() {
		super.onDestroy()
		binding = null
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
