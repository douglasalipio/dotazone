package br.com.dotazone.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.trivialdrivesample.util.IabHelper;
import com.example.android.trivialdrivesample.util.IabHelper.QueryInventoryFinishedListener;
import com.example.android.trivialdrivesample.util.IabResult;
import com.example.android.trivialdrivesample.util.Inventory;
import com.example.android.trivialdrivesample.util.Purchase;

import java.util.List;

import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.PaymentModel;
import br.com.dotazone.model.billing.BillingUtils;
import br.com.dotazone.model.util.Utils;
import br.com.dotazone.view.fragment.DialogError;
import br.com.dotazone.view.fragment.DialogError.TypeError;

public class SplashScreenActivity extends BaseActivity implements OnClickListener, OnItemSelectedListener, QueryInventoryFinishedListener {

    private Button mButtonLanguage;
    private Spinner mSpinnerLanguage;
    private ProgressBar mProgressBar;
    private IabHelper mHelper;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen_view);
        initComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (DialogError.isOnline(this)) {

            final SharedPreferences settings = getSharedPreferences(Utils.PREFS_NAME, 0);
            boolean isLanguageSelected = settings.getBoolean(LanguageActivity.IS_LANGUAGE_SELECTED, false);

            if (isLanguageSelected) {

                verifyPremiunUser();

            } else {

                mSpinnerLanguage.setVisibility(View.VISIBLE);
                mButtonLanguage.setVisibility(View.VISIBLE);
            }

        } else {

            DialogError fragmentError = DialogError.newFragmentDialog(this.getResources().getString(R.string.error_conection), this,
                    TypeError.TWO_OPTIONS);

            fragmentError.show(getSupportFragmentManager(), "Splash");

        }

    }

    @Override
    public void initComponents() {

        mSpinnerLanguage = (Spinner) findViewById(R.id.splash_spinner_language);
        mButtonLanguage = (Button) findViewById(R.id.splash_button_languagee);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBarSplash);
        mProgressBar.setVisibility(View.GONE);
        mButtonLanguage.setOnClickListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.language_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerLanguage.setAdapter(adapter);
        mSpinnerLanguage.setOnItemSelectedListener(this);
        countDotaZoneBoot();
    }

    @Override
    public void onClick(View v) {

        String languageSelected = mSpinnerLanguage.getSelectedItem().toString().toLowerCase();

        if (mSpinnerLanguage.getSelectedItemPosition() == 0) {

            Toast.makeText(this, getResources().getString(R.string.selected_fail), Toast.LENGTH_SHORT).show();
        } else {

            SharedPreferences settings = getSharedPreferences(Utils.PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(LanguageActivity.IS_LANGUAGE_SELECTED, true);
            editor.putString(LanguageActivity.LANGUAGE_KEY, languageSelected);
            editor.commit();
            verifyPremiunUser();

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setActionErrorOk() {

        startActivity(new Intent(Settings.ACTION_SETTINGS));
        finish();

    }

    @Override
    public void setActionErrorCancel() {
        finish();

    }

    @Override
    public void onQueryInventoryFinished(IabResult result, Inventory inventory) {

        if (result.isFailure()) {
            System.out.println("Erro ao obter lista de itens: " + result);

            return;
        }

        Purchase premiumPurchase = inventory.getPurchase(BillingUtils.PREMIUM);
        boolean isPremium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));

        if (isPremium) {
            DotaZoneBrain.isPremium = true;

        } else {

            //Verify that the campaign period has expired
            List<String> emails = Utils.getEmailUser(this);

            if (!emails.isEmpty()) {

                for (String email : emails)
                    new PaymentModel(this).verifyPromotionByTimeIsValid(email);
            }

        }

        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));

    }

    private boolean verifyDeveloperPayload(Purchase premiumPurchase) {

        if (premiumPurchase.getSku().equals(BillingUtils.PREMIUM))
            return true;

        return false;
    }

    private void verifyPremiunUser() {
        // chave publica do app
        String base64EncodedPublicKey = Utils.PUBLIC_KEY;
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.getIndeterminateDrawable().setColorFilter(Color.rgb(213, 50, 43), Mode.SRC_IN);
        // cria o objeto auxiliar IabHelper
        mHelper = new IabHelper(this, base64EncodedPublicKey);

        // inicia o setup
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            // esse metodo é invocado quando o setup termina
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    Log.e(DotaZoneBrain.TAG, "Erro verify premiun user");
                    return;
                }

                // pede lista dos itens que o usuario possui
                mHelper.queryInventoryAsync(SplashScreenActivity.this);
            }
        });
    }

    private void countDotaZoneBoot() {

        final SharedPreferences settings = getSharedPreferences(Utils.PREFS_NAME, 0);
        int rating = settings.getInt(DotaZoneBrain.RATING_DOTA_ZONE, 0);

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(DotaZoneBrain.RATING_DOTA_ZONE, rating + 1);
        editor.commit();
    }
}
