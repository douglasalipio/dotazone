package br.com.dotazone.view.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.text.BreakIterator;
import java.text.ParseException;

import br.com.dotazone.R;
import br.com.dotazone.model.entity.youtube.Example;
import br.com.dotazone.model.entity.youtube.Item;
import br.com.dotazone.model.util.StringUtil;
import br.com.dotazone.view.adapter.ChannelItemAdapter;
import br.com.dotazone.view.fragment.YouTubePlayerSupportCustomFragment;

public class YoutubePlayVideo extends BaseActivity implements AdapterView.OnItemClickListener {

    public static final String VIDEO_JSON = "VIDEO_JSON";
    public static final String VIDEO_ID = "VIDEO_ID";
    public static final String POSITION_ITEM = "POSITION_ITEM";
    private ListView mList;
    private String mJsonList;
    private TextView mMainTitle;
    private TextView mMainDate;
    private int mPositionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_play_video);

        initComponents();
    }

    @Override
    public void setActionErrorOk() {

    }

    @Override
    public void setActionErrorCancel() {

    }

    @Override
    public void initComponents() {

        mMainTitle = (TextView) findViewById(R.id.first_video_title);
        mMainDate = (TextView) findViewById(R.id.first_video_date);
        mList = (ListView) findViewById(R.id.youtube_play_list);
        mList.setOnItemClickListener(this);
        String videoId = getIntent().getExtras().getString(VIDEO_ID);
        mJsonList = getIntent().getExtras().getString(VIDEO_JSON);
        mPositionId = getIntent().getExtras().getInt(POSITION_ITEM);
        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        mMainTitle.setTypeface(font);
        addVideo(videoId);
        loadList();

    }

    private void loadList() {
        Gson gson = new Gson();
        Example example = gson.fromJson(mJsonList, Example.class);
        ChannelItemAdapter channelItemAdapter = new ChannelItemAdapter(example);
        mList.setAdapter(channelItemAdapter);

        mMainTitle.setText(example.items.get(mPositionId).snippet.title);
        try {
            mMainDate.setText(StringUtil.convertDate(example.items.get(mPositionId).snippet.publishedAt));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void addVideo(String videoId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        YouTubePlayerSupportCustomFragment fragment = new YouTubePlayerSupportCustomFragment();
        Bundle bundle = new Bundle();
        bundle.putString("VIDEO_ID", videoId);
        fragment.setArguments(bundle);
        ft.add(findViewById(R.id.youtube_play_frame).getId(), fragment).commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Item item = (Item) parent.getItemAtPosition(position);
        addVideo(item.id.videoId);

        mMainTitle.setText(item.snippet.title);
        try {
            mMainDate.setText(StringUtil.convertDate(item.snippet.publishedAt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
