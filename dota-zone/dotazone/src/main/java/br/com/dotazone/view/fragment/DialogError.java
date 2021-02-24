package br.com.dotazone.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

import br.com.dotazone.R;
import br.com.dotazone.model.listeners.ErrorListener;
import br.com.dotazone.view.activity.BaseActivity;

public class DialogError extends DialogFragment {

    private String mBodyMessenger;
    private ErrorListener mErrorListener;
    private Context mContext;
    private AlertDialog.Builder mBuilder;
    private TypeError mTypeError;

    public static DialogError newFragmentDialog(String bodyMessenger, BaseActivity activity, TypeError typeError) {
        DialogError f = new DialogError();
        f.mBodyMessenger = bodyMessenger;
        f.mErrorListener = (BaseActivity) activity;
        f.mContext = activity.getApplicationContext();
        f.mTypeError = typeError;
        return f;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        mBuilder = new AlertDialog.Builder(getActivity());

        if (mTypeError == TypeError.ONE_OPTIONS) {

            dialogOneOptions();
        } else if (mTypeError == TypeError.TWO_OPTIONS) {

            dialogTwoOptions();
        }

        // Create the AlertDialog object and return it
        return mBuilder.create();
    }

    private void dialogOneOptions() {
        mBuilder.setMessage(mBodyMessenger).setPositiveButton(R.string.option_yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mErrorListener.setActionErrorOk();
            }

        });
    }

    private void dialogTwoOptions() {

        mBuilder.setMessage(mBodyMessenger).setPositiveButton(R.string.option_yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mErrorListener.setActionErrorCancel();
            }

        }).setNegativeButton(R.string.option_no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                mErrorListener.setActionErrorCancel();
            }
        });
    }

    public enum TypeError {

        ONE_OPTIONS, TWO_OPTIONS;
    }

}
