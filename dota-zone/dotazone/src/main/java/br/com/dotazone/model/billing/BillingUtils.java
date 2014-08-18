package br.com.dotazone.model.billing;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.android.trivialdrivesample.util.Purchase;

import java.security.SecureRandom;

public class BillingUtils {

    public static String PAY_LOAD_KEY = "payload";
    public static String PREMIUM = "pro_account";

    @SuppressLint("TrulyRandom")
    public static int generatePayLoad(Context context) {
        SecureRandom sr = new SecureRandom();
        byte[] output = new byte[16];
        sr.nextBytes(output);
        return output[0];
    }

    public static boolean verifyDeveloperPayload(Purchase purchase) {

        if (purchase.getSku().equals(PREMIUM))
            return true;

        return false;
    }


}
