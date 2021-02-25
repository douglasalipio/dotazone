package br.com.dotazone.view.fragment

import ErrorListener
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import br.com.dotazone.R
import br.com.dotazone.view.activity.BaseActivity


class DialogError : DialogFragment() {

	private lateinit var mBodyMessenger: String
	private lateinit var mErrorListener: ErrorListener
	private lateinit var mContext: Context
	private lateinit var mBuilder: AlertDialog.Builder
	private lateinit var mTypeError: TypeError

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

		mBuilder = AlertDialog.Builder(activity)
		if (mTypeError == TypeError.ONE_OPTIONS) {
			dialogOneOptions()
		} else if (mTypeError == TypeError.TWO_OPTIONS) {
			dialogTwoOptions()
		}

		return mBuilder.create()
	}

	private fun dialogOneOptions() {
		mBuilder.setMessage(mBodyMessenger).setPositiveButton(R.string.option_yes) { dialog, id -> mErrorListener.setActionErrorOk() }
	}

	private fun dialogTwoOptions() {
		mBuilder.setMessage(mBodyMessenger).setPositiveButton(R.string.option_yes) { dialog, id -> mErrorListener.setActionErrorCancel() }.setNegativeButton(R.string.option_no) { dialog, id ->
			dialog.dismiss()
			mErrorListener.setActionErrorCancel()
		}
	}

	enum class TypeError {
		ONE_OPTIONS, TWO_OPTIONS
	}

	companion object {
		fun newFragmentDialog(bodyMessenger: String, activity: BaseActivity, typeError: TypeError): DialogError {
			val f = DialogError()
			f.mBodyMessenger = bodyMessenger
			f.mErrorListener = activity
			f.mContext = activity.applicationContext
			f.mTypeError = typeError
			return f
		}

		fun isOnline(context: Context): Boolean {
			val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
			val netInfo = cm.activeNetworkInfo
			return netInfo != null && netInfo.isConnected
		}
	}
}
