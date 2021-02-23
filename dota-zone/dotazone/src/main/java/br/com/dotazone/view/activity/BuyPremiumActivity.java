package br.com.dotazone.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.trivialdrivesample.util.IabHelper;
import com.example.android.trivialdrivesample.util.IabResult;
import com.example.android.trivialdrivesample.util.Inventory;
import com.example.android.trivialdrivesample.util.Purchase;

import br.com.dotazone.BuildConfig;
import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.billing.BillingUtils;
import br.com.dotazone.model.util.UrlUtils;

public class BuyPremiumActivity extends BaseActivity implements OnClickListener, IabHelper.OnIabPurchaseFinishedListener, IabHelper.QueryInventoryFinishedListener {

    private Button mBuyButton;
    private IabHelper mHelper;


    @Override
    protected void onCreate(Bundle bundleState) {
        super.onCreate(bundleState);

       // setContentView(R.layout.buy_premium_view);
        initComponents();

    }

    @Override
    public void onClick(View v) {

        String base64EncodedPublicKey = BuildConfig.DOTA_ZONE_STEAM_PUBLIC_KEY;
        final int requestId = 12345;
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        final String payload = "";

        // inicia o setup
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            // esse metodo é invocado quando o setup termina
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    Log.e(DotaZoneBrain.TAG, "Erro verify premiun user");
                    Toast.makeText(BuyPremiumActivity.this, getResources().getString(R.string.buy_item_error), Toast.LENGTH_SHORT).show();
                    return;
                }

                mHelper.launchPurchaseFlow(BuyPremiumActivity.this, BillingUtils.PREMIUM, requestId, BuyPremiumActivity.this, payload);
            }
        });


    }

    @Override
    public void initComponents() {

       // mBuyButton = (Button) findViewById(R.id.buy_premium_button);
       // mBuyButton.setOnClickListener(this);

    }

    @Override
    public void setActionErrorOk() {
        // TODO Auto-generated method stub

    }

    @Override
    public void setActionErrorCancel() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onIabPurchaseFinished(IabResult result, Purchase purchase) {

        if (result.isFailure()) {
            Toast.makeText(this, getResources().getString(R.string.buy_item_error), Toast.LENGTH_SHORT).show();
            return;
        }
        if (BillingUtils.verifyDeveloperPayload(purchase)) {

            Toast.makeText(this, getResources().getString(R.string.payment_error), Toast.LENGTH_SHORT).show();
            return;
        }

        if (purchase.getSku().equals(BillingUtils.PREMIUM)) {

            DotaZoneBrain.isPremium = true;
            Toast.makeText(this, getResources().getString(R.string.thank_payment), Toast.LENGTH_SHORT).show();
            final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
            dialog.show();
            //dialog.setContentView(R.layout.thank_payment_dialog_view);
            //Button mButtonThank = (Button) dialog.findViewById(R.id.thank_payment_button);
           // mButtonThank.setOnClickListener(new OnClickListener() {

               // @Override
               // public void onClick(View v) {

                //    BuyPremiumActivity.this.finish();
                 //   startActivity(new Intent(BuyPremiumActivity.this, MainActivity.class));

                //}
           // });
        }

    }


    @Override
    public void onQueryInventoryFinished(IabResult result, Inventory inv) {

    }
}
