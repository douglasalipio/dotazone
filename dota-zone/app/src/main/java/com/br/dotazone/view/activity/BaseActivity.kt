package com.br.dotazone.view.activity

import ErrorListener
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.br.dotazone.R
import com.br.dotazone.model.listeners.Initializable


abstract class BaseActivity : FragmentActivity(), Initializable, ErrorListener {
	override fun onCreate(arg0: Bundle?) {
		super.onCreate(arg0)
		requestWindowFeature(Window.FEATURE_NO_TITLE)
	}

	/**
	 * Start fragment in content activity.
	 *
	 * @param fragment
	 */
	protected fun startFragment(fragment: Fragment?) {
		val fragmentTransaction = supportFragmentManager.beginTransaction()
		fragmentTransaction.replace(R.id.main_activity_content, fragment!!)
		fragmentTransaction.commit()
	}
}
