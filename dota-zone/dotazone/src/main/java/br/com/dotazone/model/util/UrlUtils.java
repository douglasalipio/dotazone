package br.com.dotazone.model.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import br.com.dotazone.view.activity.LanguageActivity;

public class UrlUtils {

    public static final String URL_IMAGE_ITEM = "http://media.steampowered.com/apps/dota2/images/items/";
    public static final String PREFS_NAME = "DotaZone";

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
}
