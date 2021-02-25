//package br.com.dotazone.model.entity;
//
//import android.content.Context;
//import android.telephony.TelephonyManager;
//import android.view.View;
//
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//
//public class AdMobBanner {
//
//    private final static String MY_AD_UNIT_ID = "5296490904174793";
//
//    public AdView createBanner(Context context, AdView adView, boolean isPremium) {
//
//        return createBannerProduction(context, adView, isPremium);
//    }
//
//    private AdView createBannerProduction(Context context, AdView adView, boolean isPremium) {
//
//        if (isPremium) {
//
//            adView.setVisibility(View.GONE);
//
//        } else {
//
//            AdRequest adRequest = new AdRequest.Builder().build();
//            adView.loadAd(adRequest);
//        }
//
//        return adView;
//    }
//
//    private AdView createBannerTests(Context context, AdView adView, boolean isPremium) {
//
//        if (isPremium) {
//
//            adView.setVisibility(View.GONE);
//
//        } else {
//
//            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).addTestDevice(telephonyManager.getDeviceId())
//                    .build();
//            adView.loadAd(adRequest);
//        }
//
//        return adView;
//    }
//}
