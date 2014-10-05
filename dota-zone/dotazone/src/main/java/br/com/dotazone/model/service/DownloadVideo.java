package br.com.dotazone.model.service;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;

import br.com.dotazone.R;
import br.com.dotazone.model.util.UrlUtils;


/**
 * Created by Douglas on 09/09/2014.
 */
public class DownloadVideo {

    private final Context mContext;
    private long enqueue;
    private DownloadManager dm;

    public DownloadVideo(Context context, final String keyReferenceDownload) {

        mContext = context;
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(enqueue);
                    Cursor cursor = dm.query(query);

                    if (cursor.moveToFirst()) {

                        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(columnIndex)) {
                            Toast.makeText(mContext, context.getString(R.string.download_video), Toast.LENGTH_SHORT).show();
                            saveReferenceProgressDownload(keyReferenceDownload, false);
                        }
                    }
                }
            }
        };
        mContext.registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    public void init(String descriptionVideo, String fileName, String extensionFile, String urlVideo, int position) {

        saveReferenceProgressDownload(String.valueOf(position), true);

        //execute download
        dm = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(urlVideo));

        String path = createADir();
        request.setDestinationInExternalPublicDir(path, fileName + "." + extensionFile);
        request.setTitle(descriptionVideo);
        enqueue = dm.enqueue(request);

    }

    private void saveReferenceProgressDownload(String key, boolean value) {

        //saves the reference in the preference list item
        SharedPreferences settings = mContext.getSharedPreferences(UrlUtils.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private String createADir() {
        File mydir = new File("/dotazone/video/");
        if (!mydir.exists())
            mydir.mkdirs();

        return mydir.getPath();
    }
}
