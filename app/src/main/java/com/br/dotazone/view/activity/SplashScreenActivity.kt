package com.br.dotazone.view.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.br.dotazone.DotaZoneBrain
import com.br.dotazone.R
import com.br.dotazone.databinding.SplashScreenViewBinding
import com.br.dotazone.model.util.UrlUtils
import com.br.dotazone.view.fragment.DialogError
import com.br.dotazone.view.fragment.DialogError.TypeError

class SplashScreenActivity : BaseActivity(), View.OnClickListener, OnItemSelectedListener {

    private lateinit var binding: SplashScreenViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    override fun onResume() {
        super.onResume()
        if (DialogError.isOnline(this)) {
            val settings = getSharedPreferences(UrlUtils.PREFS_NAME, 0)
            val isLanguageSelected = settings.getBoolean(LanguageActivity.IS_LANGUAGE_SELECTED, false)
            if (isLanguageSelected) {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            } else {
                binding.splashSpinnerLanguage.visibility = View.VISIBLE
            }
        } else {
            val fragmentError = DialogError.newFragmentDialog(this.resources.getString(R.string.error_conection), this,
                    TypeError.TWO_OPTIONS)
            fragmentError.show(supportFragmentManager, "Splash")
        }
    }

    override fun initComponents() {
        binding.progressBarSplash.visibility = View.GONE
        binding.splashButtonLanguage.setOnClickListener(this)
        val adapter = ArrayAdapter.createFromResource(this, R.array.language_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.splashSpinnerLanguage.adapter = adapter
        binding.splashSpinnerLanguage.onItemSelectedListener = this
        dotaZoneRateAppCount()
    }

    override fun onClick(v: View) {
        val languageSelected = binding.splashSpinnerLanguage.selectedItem.toString().toLowerCase()
        if (binding.splashSpinnerLanguage.selectedItemPosition == 0) {
            Toast.makeText(this, resources.getString(R.string.selected_fail), Toast.LENGTH_SHORT).show()
        } else {
            val settings = getSharedPreferences(UrlUtils.PREFS_NAME, 0)
            val editor = settings.edit()
            editor.putBoolean(LanguageActivity.IS_LANGUAGE_SELECTED, true)
            editor.putString(LanguageActivity.LANGUAGE_KEY, languageSelected)
            editor.apply()
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        // TODO Auto-generated method stub
    }

    override fun setActionErrorOk() {
        startActivity(Intent(Settings.ACTION_SETTINGS))
        finish()
    }

    override fun setActionErrorCancel() {
        finish()
    }

    private fun dotaZoneRateAppCount() {
        val settings = getSharedPreferences(UrlUtils.PREFS_NAME, 0)
        val rating = settings.getInt(DotaZoneBrain.RATING_DOTA_ZONE, 0)
        val editor = settings.edit()
        editor.putInt(DotaZoneBrain.RATING_DOTA_ZONE, rating + 1)
        editor.apply()
    }
}
