package br.com.dotazone.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.android.trivialdrivesample.util.IabHelper;
import com.example.android.trivialdrivesample.util.IabResult;
import com.example.android.trivialdrivesample.util.Purchase;

import br.com.dotazone.BuildConfig;
import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.DotazoneMenu;
import br.com.dotazone.R;
import br.com.dotazone.model.PaymentModel;
import br.com.dotazone.model.billing.BillingUtils;
import br.com.dotazone.model.util.UrlUtils;

/**
 * Created by Douglas on 12/08/2014.
 */
public class PaymentActivity extends BaseActivity implements IabHelper.OnIabPurchaseFinishedListener {


    public DotazoneMenu mMenu;
    private IabHelper mHelper;
    private Button mBuyButton;
    private EditText mEditReemCode;
    private PaymentModel mPaymentModel;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerList;
    private LinearLayout mLayoutMenu;

    @Override
    protected void onCreate(Bundle bundleState) {
        super.onCreate(bundleState);
        setContentView(R.layout.payment_view);
        initComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMenu.defaultMenu();
    }

    @Override
    public void setActionErrorOk() {

    }

    @Override
    public void setActionErrorCancel() {

    }

    @Override
    public void initComponents() {
        mPaymentModel = new PaymentModel(this);
        mMenu = new DotazoneMenu(this, mDrawerLayout, mDrawerList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RelativeLayout) findViewById(R.id.list_slidermenu);
        mLayoutMenu = (LinearLayout) findViewById(R.id.payment_menu_layout);
        mLayoutMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDrawerLayout.openDrawer(mDrawerList);
            }
        });
        mEditReemCode = (EditText) findViewById(R.id.payment_redeem_code);
        mBuyButton = (Button) findViewById(R.id.payment_buy_button);
        mBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mEditReemCode.getText().toString().equals("")) {

                    //if (mPaymentModel.activeDiscountDotaZonePro(mEditReemCode.getText().toString())) {
                    //    paymentGooglePlay(BillingUtils.PREMIUM);

                    // } else {
                    mPaymentModel.activePromotionByTime(mEditReemCode.getText().toString(),mBuyButton);
                    //}

                } else {
                    paymentGooglePlay(BillingUtils.PREMIUM);
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onIabPurchaseFinished(IabResult result, Purchase purchase) {

        if (result.isFailure()) {
            Toast.makeText(this, getResources().getString(R.string.buy_item_error), Toast.LENGTH_SHORT).show();
            return;
        }

        if (purchase.getSku().equals(BillingUtils.PREMIUM)) {

            Toast.makeText(this, "PRO version unlocked!", Toast.LENGTH_LONG).show();
            DotaZoneBrain.isPremium = true;
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void paymentGooglePlay(final String sku) {

        String base64EncodedPublicKey = BuildConfig.DOTA_ZONE_STEAM_PUBLIC_KEY;
        final int requestId = 12345;
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        final String payload = "";

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            // esse metodo é invocado quando o setup termina
            public void onIabSetupFinished(IabResult result) {

                if (!result.isSuccess()) {
                    Log.e(DotaZoneBrain.TAG, "Erro verify premiun user");
                    Toast.makeText(PaymentActivity.this, getResources().getString(R.string.buy_item_error), Toast.LENGTH_SHORT).show();
                    return;
                }

                mHelper.launchPurchaseFlow(PaymentActivity.this, sku, requestId, PaymentActivity.this, payload);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            // not handled, so handle it ourselves (here's where you'd
            // perform any handling of activity results not related to in-app
            // billing...
            super.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.d(DotaZoneBrain.TAG, "onActivityResult handled by IABUtil.");
        }
    }
}
