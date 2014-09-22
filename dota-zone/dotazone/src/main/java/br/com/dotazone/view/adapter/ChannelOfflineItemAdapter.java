package br.com.dotazone.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import java.text.ParseException;

import br.com.dotazone.R;
import br.com.dotazone.model.entity.youtube.Example;
import br.com.dotazone.model.service.VolleySingleton;
import br.com.dotazone.model.util.StringUtil;

/**
 * Created by Douglas on 24/08/2014.
 */
public class ChannelOfflineItemAdapter extends BaseAdapter {

    static final int LAYOUT = R.layout.feed_video_item_view;
    private final Example mVideoList;

    public ChannelOfflineItemAdapter(Example videoList) {

        this.mVideoList = videoList;
    }

    @Override
    public int getCount() {
        return mVideoList.items.size();
    }

    @Override
    public Object getItem(int i) {
        return mVideoList.items.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context ctx = parent.getContext();
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(LAYOUT, null);
        }
        NetworkImageView img = (NetworkImageView) convertView.findViewById(R.id.banner_channel);


        //LinearLayout.LayoutParams param =  new LinearLayout.LayoutParams(UrlUtils.convertDpToPixel(120,
        //      ctx.getResources()), UrlUtils.convertDpToPixel(90, ctx.getResources()));


        TextView title = (TextView) convertView.findViewById(R.id.title_video_on);
        img.setImageUrl(mVideoList.items.get(position).snippet.thumbnails.high.url, VolleySingleton.getInstance(
                ctx).getImageLoader());
        img.setDefaultImageResId(R.drawable.channel_thumb_default);
        //img.setLayoutParams(param);
        title.setText(mVideoList.items.get(position).snippet.title);
        TextView subTitle = (TextView) convertView.findViewById(R.id.channel_name_on);
        subTitle.setText(mVideoList.items.get(position).snippet.channelTitle);
        TextView date = (TextView) convertView.findViewById(R.id.date_post_channel_on);

        Typeface font = Typeface.createFromAsset(ctx.getAssets(), "Roboto-Light.ttf");
        title.setTypeface(font);

        try {
            date.setText(StringUtil.convertDate(mVideoList.items.get(position).snippet.publishedAt));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertView;
    }

}
