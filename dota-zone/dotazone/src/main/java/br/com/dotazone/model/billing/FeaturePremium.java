package br.com.dotazone.model.billing;

import android.content.Context;
import android.view.View;

public class FeaturePremium {

    private Context mContext;

    public FeaturePremium(Context context) {

        this.mContext = context;
    }


    public void unlockOrLockAdPro(View payButton, View viewAd, boolean isPremium) {

        if (isPremium) {

            viewAd.setVisibility(View.VISIBLE);
            payButton.setVisibility(View.GONE);

        } else {
            payButton.setVisibility(View.VISIBLE);
            viewAd.setVisibility(View.GONE);
        }
    }

}
