package br.com.dotazone.model.service;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

    public DownloadVideo(Context context) {

        mContext = context;
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(enqueue);
                    Cursor c = dm.query(query);

                    if (c.moveToFirst()) {

                        int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                            Toast.makeText(mContext, context.getString(R.string.download_video), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        };
        mContext.registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    public void init(String descriptionVideo, String fileName, String extensionFile, String urlVideo) {

        dm = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse(urlVideo));

        String path = createADir();
        request.setDestinationInExternalPublicDir(path, fileName + "." + extensionFile);
        request.setTitle(descriptionVideo);
        enqueue = dm.enqueue(request);

    }

    private String createADir() {
        File mydir = new File("/dotazone/video/");
        if (!mydir.exists())
            mydir.mkdirs();

        return mydir.getPath();
    }
}
