package br.com.dotazone.view.activity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import android.view.Window;

import br.com.dotazone.R;
import br.com.dotazone.model.listeners.ErrorListener;
import br.com.dotazone.model.listeners.Initializable;

abstract public class BaseActivity extends FragmentActivity implements Initializable, ErrorListener {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * Start fragment in content activity.
     *
     * @param fragment
     */
    protected void startFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_content, fragment);
        fragmentTransaction.commit();
    }
}
