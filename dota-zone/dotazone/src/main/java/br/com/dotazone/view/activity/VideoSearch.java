package br.com.dotazone.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import br.com.dotazone.R;
import br.com.dotazone.model.entity.youtube.Example;
import br.com.dotazone.model.entity.youtube.Item;
import br.com.dotazone.model.util.UrlUtils;
import br.com.dotazone.view.adapter.ChannelItemAdapter;

public class VideoSearch extends BaseActivity implements Response.Listener<JSONObject>,
        Response.ErrorListener, AdapterView.OnItemClickListener {

    private EditText videoSearch;
    private ListView mListViewVideo;
    private ChannelItemAdapter adapter;
    private Example mVideosList;
    private String mJsonList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_search);

        initComponents();

    }


    @Override
    public void initComponents() {

        LinearLayout menu = (LinearLayout) findViewById(R.id.layout_menu_video_search);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageView searchButton = (ImageView) findViewById(R.id.icon_search_video);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = UrlUtils.getUrlVideoSearch(videoSearch.getText().toString().replace(" ", "%20"));
                RequestQueue queue = Volley.newRequestQueue(VideoSearch.this);
                JsonObjectRequest jsObjRequest =
                        new JsonObjectRequest(Request.Method.GET, url, null, VideoSearch.this, VideoSearch.this);

                queue.add(jsObjRequest);
                mListViewVideo = (ListView) findViewById(R.id.video_search_list);
                mListViewVideo.setOnItemClickListener(VideoSearch.this);
            }
        });

        videoSearch = (EditText) findViewById(R.id.search_video);
        videoSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {

                    switch (keyEvent.getKeyCode()) {

                        case KeyEvent.KEYCODE_ENTER:

                            String url = UrlUtils.getUrlVideoSearch(videoSearch.getText().toString().replace(" ", "%20"));
                            RequestQueue queue = Volley.newRequestQueue(VideoSearch.this);
                            JsonObjectRequest jsObjRequest =
                                    new JsonObjectRequest(Request.Method.GET, url, null, VideoSearch.this, VideoSearch.this);

                            queue.add(jsObjRequest);

                            return true;
                    }

                }


                return false;
            }
        });
        mListViewVideo = (ListView) findViewById(R.id.video_search_list);
        mListViewVideo.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.video_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setActionErrorOk() {

    }

    @Override
    public void setActionErrorCancel() {

    }

    @Override
    public void onErrorResponse(VolleyError volleyError) {

        Toast.makeText(VideoSearch.this, getResources().getString(R.string.payment_error_conection), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject jsonObject) {

        mJsonList = jsonObject.toString();
        mVideosList = new Gson().fromJson(mJsonList, Example.class);
        adapter = new ChannelItemAdapter(mVideosList);

        if (mVideosList.items.isEmpty()) {

            Toast.makeText(VideoSearch.this, getString(R.string.video_not_found), Toast.LENGTH_LONG).show();

        } else {

            mListViewVideo.setAdapter(adapter);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Item item  = (Item) parent.getItemAtPosition(position);

        Bundle bundle = new Bundle();
        bundle.putString(YoutubePlayVideo.VIDEO_ID,item.id.videoId);
        bundle.putInt(YoutubePlayVideo.POSITION_ITEM,position);
        bundle.putString(YoutubePlayVideo.VIDEO_JSON, mJsonList);
        Intent intent = new Intent(this, YoutubePlayVideo.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
