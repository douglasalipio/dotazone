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
import br.com.dotazone.model.entity.VideoOffline;
import br.com.dotazone.model.entity.youtube.Example;
import br.com.dotazone.model.service.VolleySingleton;
import br.com.dotazone.model.util.StringUtil;

/**
 * Created by Douglas on 24/08/2014.
 */
public class ChannelItemAdapter extends BaseAdapter {

    static final int LAYOUT = R.layout.feed_video_item_view;
    private Example mVideoList;
    private VideoOffline mVideosOffline;

    public ChannelItemAdapter(Example videoList) {

        this.mVideoList = videoList;
    }

    public ChannelItemAdapter(VideoOffline videoList) {

        this.mVideosOffline = videoList;
    }


    @Override
    public int getCount() {
        return mVideoList == null ? mVideosOffline.channels.get(0).championship.get(0).mVideos.size() : mVideoList.items.size();
    }

    @Override
    public Object getItem(int i) {
        return mVideoList == null ? mVideosOffline.channels.get(0).championship.get(0).mVideos.get(i) : mVideoList.items.get(i);
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
        img.setDefaultImageResId(R.drawable.channel_thumb_default);

        //Verifica qual conteúdo carregar, videos online ou videos offline;

        if (mVideoList == null) {
            fillView(mVideosOffline.channels.get(0).championship.get(0).mVideos.get(position).mTitleVideo, mVideosOffline.channels.get(0).mChannel,
                    mVideosOffline.channels.get(0).championship.get(0).mVideos.get(position).mDateVideo, ctx, convertView, false);
            img.setImageUrl(mVideosOffline.channels.get(0).championship.get(0).mVideos.get(position).thumbnail, VolleySingleton.getInstance(
                    ctx).getImageLoader());

        } else {

            fillView(mVideoList.items.get(position).snippet.title, mVideoList.items.get(position).snippet.channelTitle,
                    mVideoList.items.get(position).snippet.publishedAt, ctx, convertView, true);
            img.setImageUrl(mVideoList.items.get(position).snippet.thumbnails.high.url, VolleySingleton.getInstance(
                    ctx).getImageLoader());

        }
        return convertView;
    }

    private void fillView(String titleParam, String subTitleParam, String dateParam, Context context, View convertView, boolean convertDate) {

        TextView title = (TextView) convertView.findViewById(R.id.title_video_on);
        title.setText(titleParam);

        TextView subTitle = (TextView) convertView.findViewById(R.id.channel_name_on);
        subTitle.setText(subTitleParam);

        TextView date = (TextView) convertView.findViewById(R.id.date_post_channel_on);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "Roboto-Light.ttf");
        title.setTypeface(font);

        if (convertDate)
            try {
                date.setText(StringUtil.convertDate(dateParam));
            } catch (ParseException e) {
                e.printStackTrace();
                date.setText(dateParam);
            }
        else
            date.setText(dateParam);

    }


}
