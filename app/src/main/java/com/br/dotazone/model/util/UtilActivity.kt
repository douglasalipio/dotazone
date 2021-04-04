package com.br.dotazone.model.util

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.br.dotazone.DotaZoneBrain
import com.br.dotazone.R
import com.br.dotazone.databinding.AlertRatingViewBinding
import com.br.dotazone.databinding.HelpBuildHeroBinding
import com.br.dotazone.databinding.ItemDialogViewBinding
import com.br.dotazone.domain.heroes.prov.Item
import com.br.dotazone.model.util.UrlUtils.Companion.convertDpToPixel


object UtilActivity {
    private var isBuy = false

    fun ratingDotaZoneDialog(context: Context) {
        val settings = context.getSharedPreferences(UrlUtils.PREFS_NAME, 0)
        val rating = settings.getInt(DotaZoneBrain.RATING_DOTA_ZONE, 0)

        val inflater = LayoutInflater.from(context)
        val binding = AlertRatingViewBinding.inflate(inflater)

        if (rating == 3) {
            val dialog = Dialog(context, R.style.FullHeightDialog)
            dialog.show()
            dialog.setContentView(R.layout.alert_rating_view)
            dialog.setCancelable(false)
            binding.ratingClose.setOnClickListener { dialog.dismiss() }
            binding.ratingStart.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("market://details?id=" + context.applicationContext.packageName)
                context.startActivity(intent)
            }
        }
    }

    fun showAlertBuyProVersion(context: Context?): Boolean {
        val dialog = Dialog(context!!, R.style.FullHeightDialog)
        dialog.show()
        dialog.setContentView(R.layout.alert_buy_view)
        dialog.setCancelable(false)
        val buy = dialog.findViewById<View>(R.id.buy_pro_version) as Button
        val close = dialog.findViewById<View>(R.id.buy_close) as Button
        close.setOnClickListener { dialog.dismiss() }
        buy.setOnClickListener {
            isBuy = true
            dialog.dismiss()
        }
        return isBuy
    }

    fun showHelpBuildHero(context: Context?) {
        // custom dialog
        val dialog = Dialog(context!!, R.style.FullHeightDialogTransparent)
        val inflater = LayoutInflater.from(context)
        val binding = HelpBuildHeroBinding.inflate(inflater)
        dialog.setContentView(R.layout.help_build_hero)
        dialog.window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        binding.layoutHelpBuildHero.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    fun showDescriptionItem(item: Item?, context: Context) {

        val inflater = LayoutInflater.from(context)
        val binding = ItemDialogViewBinding.inflate(inflater)

        val dialog = Dialog(context, R.style.FullHeightDialog)
        dialog.setContentView(R.layout.item_dialog_view)
        binding.textItemName.text = item?.name
        binding.textAttribute.text = Html.fromHtml(item?.attribute)
        val cd = if (item?.cloudown == "false") context.getString(R.string.item_no_cd) else item?.cloudown
        val mana = if (item?.mc == "false") context.getString(R.string.item_no_cd) else item?.mc
        binding.textCdTimer.text = cd
        binding.textCost.text = item?.cost.toString()
        binding.textDescription.text = Html.fromHtml(item?.description)
        binding.textMana.text = mana
        val pos = item?.imageName?.lastIndexOf('.') ?: 0
        val idImage = context.resources.getIdentifier(item?.imageName?.substring(0, pos), "drawable",
                context.packageName)
        binding.imageItem.setImageResource(idImage)
        setImageComponents(item, binding.itemLayoutComponents, context)
        dialog.show()
    }

    private fun setImageComponents(item: Item?, linearLayout: LinearLayout, context: Context) {
        if (item != null && item.components.isNotEmpty()) {
            for (itemName in item.components) {
                val idImage = context.resources.getIdentifier(itemName + "_lg", "drawable", context.packageName)
                if (idImage != 0) {
                    val imageView = ImageView(context)
                    imageView.setImageResource(idImage)
                    linearLayout.addView(imageView)
                    val param = LinearLayout.LayoutParams(convertDpToPixel(50f,
                            context.resources), convertDpToPixel(32f, context.resources))
                    imageView.layoutParams = param
                }
            }
        }
    }
}
