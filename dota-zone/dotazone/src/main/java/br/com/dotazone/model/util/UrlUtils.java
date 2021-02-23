package br.com.dotazone.model.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.util.Patterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import br.com.dotazone.BuildConfig;
import br.com.dotazone.model.entity.youtube.Example;
import br.com.dotazone.view.activity.LanguageActivity;

public class UrlUtils {

    public static final String URL_IMAGE_ITEM = "http://media.steampowered.com/apps/dota2/images/items/";
    public static final String PREFS_NAME = "DotaZone";
    private static final String CHANNEL_ID = "UCfWCxZLHj0zo_DSMKLLUD7Q";
    public static final String URL_VIDEOS_JSON = "http://storage.googleapis.com/dotazone_bucket/template_json_videos.txt";

    public static String getUrlNewVideoYoutube() {
        return "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=" + CHANNEL_ID + "&maxResults=10&order=date&key=" + BuildConfig.DOTA_ZONE_YOUTUBE_KEY + "";
    }

    public static String getUrlVideoSearch(String value) {
        return "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=" + CHANNEL_ID + "&maxResults=20&order=date&q=" + value + "&key=" + BuildConfig.DOTA_ZONE_YOUTUBE_KEY + "";
    }


    public static String getUrlItem(Context context) {

        return "http://www.dota2.com/jsfeed/itemdata/?l=" + getLanguage(context);

    }

    public static String getUrlHero(Context context) {

        return "http://www.dota2.com/jsfeed/heropickerdata/?l=" + getLanguage(context);

    }

    public static String getUrlSkill(Context context) {

        return "http://www.dota2.com/jsfeed/abilitydata?l=" + getLanguage(context);

    }

    public static String getUrlAbility(Context context) {

        return "http://www.dota2.com/jsfeed/heropediadata?feeds=herodata&l=" + getLanguage(context);

    }

    public static int convertDpToPixel(float dp, Resources res) {
        return (int) (res.getDisplayMetrics().density * dp + 0.5f);
    }

    public static float convertPixelToDp(Context context, int imagePx) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (float) (imagePx / scale);
    }

    public static String extractNumber(String string) {
        return string.replaceAll("\\D+", "");
    }

    public static String identifyZero(String string) {
        if (Integer.valueOf(string.replaceAll("\\D+", "")) == 0)
            return "";
        return string;
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private static String getLanguage(Context context) {

        final SharedPreferences settings = context.getSharedPreferences(UrlUtils.PREFS_NAME, 0);
        String isLanguageSelected = settings.getString(LanguageActivity.LANGUAGE_KEY, "english");

        return isLanguageSelected.toLowerCase();

    }

    /**
     * Return version of the application.
     *
     * @param context
     * @return
     * @throws NameNotFoundException
     */
    public static Double deviceVersion(Context context) throws NameNotFoundException {

        PackageManager manager = context.getPackageManager();
        PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
        String version = info.versionName;

        return Double.valueOf(version);
    }

    public static List<String> getEmailUser(Context context) {

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(context).getAccountsByType("com.google");
        List<String> emails = new ArrayList<String>();
        if (accounts.length > 0) {
            for (int i = 0; i < accounts.length; i++) {

                emails.add(accounts[i].name);
            }
        }


        return emails;
    }
}
