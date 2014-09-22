package br.com.dotazone.model.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Douglas on 28/08/2014.
 */
public class StringUtil {

    public static String convertDate(String stringDate) throws ParseException {
        SimpleDateFormat formatter, FORMATTER;
        formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = null;
        date = formatter.parse(stringDate.substring(0, 24));
        return new SimpleDateFormat("dd-MMM-yyyy").format(date);
    }


    public static boolean verifyIfExistVideo(Context context, String fileName, String extesion) {

        File sdDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/dotazone/video/" + fileName + "." + extesion);

        if (sdDir.exists())
            return true;
        else
            return false;
    }
}
