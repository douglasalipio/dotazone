package com.br.dotazone.view.activity

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import com.br.dotazone.DotaZoneApplication
import com.br.dotazone.DotaZoneBrain.heroes
import com.br.dotazone.DotaZoneBrain.items
import com.br.dotazone.R
import com.br.dotazone.databinding.ActivityMainBinding
import com.br.dotazone.databinding.LanguageViewBinding
import com.br.dotazone.model.util.UrlUtils
import com.com.dotazone.DotazoneMenu
import com.google.android.gms.analytics.GoogleAnalytics
import java.util.*


class LanguageActivity : BaseActivity(), View.OnClickListener {

    private var mMenu: DotazoneMenu? = null
    private var mStringLanguages: MutableList<String> = mutableListOf()

    private lateinit var binding: LanguageViewBinding

    override fun onCreate(bundleState: Bundle?) {
        super.onCreate(bundleState)

        binding = LanguageViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    override fun onResume() {
        super.onResume()
        mMenu!!.checkLanguageMenu()
    }

    override fun onStart() {
        super.onStart()
        GoogleAnalytics.getInstance(this).reportActivityStart(this)
    }

    override fun onStop() {
        super.onStop()

        // Stop the analytics tracking
        GoogleAnalytics.getInstance(this).reportActivityStop(this)
    }

    override fun initComponents() {
        mMenu = DotazoneMenu(this, binding.drawerLayout, binding.listSliderMenu.root)
        mMenu!!.checkLanguageMenu()
        defaultSelected
        addLanguageInArray()
        mLanguageText = findViewById<View>(R.id.language_text) as TextView
        mLanguageText!!.text = stringSelected
        binding.layoutLanguage.setOnClickListener { LanguageDialogFragment.newFragmentDialog(mStringLanguages, defaultSelected).show(supportFragmentManager, "language") }
        binding.linearLayout1.setOnClickListener { binding.drawerLayout.openDrawer(binding.listSliderMenu.root) }
    }

    protected fun addLanguageInArray() {
        val mLanguageArray = resources.getStringArray(R.array.language_array)
        mStringLanguages = mutableListOf()
        for (i in mLanguageArray.indices) {
            mStringLanguages.add(mLanguageArray[i])
        }
    }

    private val defaultSelected: Int
        private get() {
            val settings = getSharedPreferences(UrlUtils.PREFS_NAME, 0)
            val defaultSelected = settings.getString(LANGUAGE_KEY, "english")
            val mLanguageArray = resources.getStringArray(R.array.language_array)
            for (i in mLanguageArray.indices) {
                if (defaultSelected.equals(mLanguageArray[i], ignoreCase = true)) {
                    return i
                }
            }
            return 0
        }
    private val stringSelected: String?
        private get() {
            val settings = getSharedPreferences(UrlUtils.PREFS_NAME, 0)
            val defaultSelected = settings.getString(LANGUAGE_KEY, "english")
            val mLanguageArray = resources.getStringArray(R.array.language_array)
            for (i in mLanguageArray.indices) {
                if (defaultSelected == mLanguageArray[i].toLowerCase()) {
                    return mLanguageArray[i]
                }
            }
            return defaultSelected
        }

    override fun setActionErrorOk() {}
    override fun setActionErrorCancel() {}
    override fun onClick(v: View) {}
    override fun finish() {
        super.finish()
        mMenu!!.defaultMenu()
    }

    class LanguageDialogFragment : DialogFragment() {
        private var languages: List<String?>? = null
        private var position = 0
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val builder = AlertDialog.Builder(activity)
            // Set the dialog title
            builder.setTitle(R.string.settings_menu) // Specify the list array, the items to be selected by default (null for none),
                    // and the listener through which to receive callbacks when items are selected
                    .setSingleChoiceItems(R.array.language_array, position, DialogInterface.OnClickListener { dialogInterface, id ->
                        val settings = requireActivity().getSharedPreferences(UrlUtils.PREFS_NAME, 0)
                        val editor = settings.edit()
                        editor.putBoolean(IS_LANGUAGE_SELECTED, true)
                        editor.putString(LANGUAGE_KEY, languages!![id])
                        //DotaZoneBrain.rssItems = new ArrayList<RssItem>();
                        items = ArrayList()
                        heroes = ArrayList()
                        // Commit the edits!
                        editor.apply()
                        mLanguageText!!.text = languages!![id]
                    }) // Set the action buttons
                    .setPositiveButton(R.string.option_yes, DialogInterface.OnClickListener { dialog, id -> dialog.dismiss() })
                    .setNegativeButton(R.string.option_no, DialogInterface.OnClickListener { dialog, id -> dialog.dismiss() })
            return builder.create()
        }

        companion object {
            fun newFragmentDialog(languages: List<String?>?, position: Int): LanguageDialogFragment {
                val f = LanguageDialogFragment()
                f.languages = languages
                f.position = position
                return f
            }
        }
    }

    companion object {
        const val LANGUAGE_KEY = "language_value"
        const val IS_LANGUAGE_SELECTED = "language_selected"
        private var mLanguageText: TextView? = null
    }
}
