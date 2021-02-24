package br.com.dotazone.view.fragment

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import br.com.dotazone.R
import br.com.dotazone.model.entity.Skill
import br.com.dotazone.model.util.UrlUtils
import br.com.dotazone.view.components.ImageGetter
import kotlinx.android.synthetic.main.hero_descripition_skill_view.*


class HeroSkillFragment : BaseFragment() {
	private var mView: View? = null
	private lateinit var mSkill: Skill
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.hero_descripition_skill_view, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		initComponents()
	}

	override fun initComponents() {
		heroTextSkillName.text = mSkill.getdName()
		val affects = if (mSkill.affectes == "") null else mSkill.affectes
		val desc = if (mSkill.desc == "") null else mSkill.desc
		val notes = if (mSkill.notes == "") null else mSkill.notes
		val dmg = if (mSkill.dmg == "") null else mSkill.dmg
		val attribs = if (mSkill.attrib == "") null else mSkill.attrib
		val cmb = if (mSkill.cmb == "") null else mSkill.cmb
		val lore = if (mSkill.lore == "") null else mSkill.lore
		createAttributsSkill(cmb, true, 3)
		createAttributsSkill(affects, false, 20)
		createAttributsSkill(attribs, false, 20)
		createAttributsSkill(desc, false, 20)
		createAttributsSkill(notes, false, 20)
		createAttributsSkill(dmg, false, 20)
		createAttributsSkill(lore, false, 20)
	}

	private fun createAttributsSkill(attribute: String?, imageInHTML: Boolean, countLine: Int) {
		if (attribute != null) {
			val textView = TextView(activity)
			textView.textSize = 13f
			textView.setTextColor(resources.getColor(R.color.color_text_list))
			textView.maxLines = countLine
			textView.includeFontPadding = false
			if (imageInHTML) {
				textView.text = Html.fromHtml(attribute, ImageGetter(activity), null)
			} else {
				textView.text = Html.fromHtml(attribute)
			}
			linearSkillDescription.addView(textView)
			val paramSkill = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT)
			paramSkill.topMargin = UrlUtils.convertDpToPixel(10f, resources)
			paramSkill.leftMargin = UrlUtils.convertDpToPixel(10f, resources)
			paramSkill.rightMargin = UrlUtils.convertDpToPixel(10f, resources)
			textView.layoutParams = paramSkill
		}
	}

	companion object {
		fun newInstance(skill: Skill): HeroSkillFragment {
			val f = HeroSkillFragment()
			f.mSkill = skill
			return f
		}
	}
}

