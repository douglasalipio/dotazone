package br.com.dotazone.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import br.com.dotazone.R;
import br.com.dotazone.model.entity.Channel;

/**
 * Created by Douglas on 24/08/2014.
 */
public class ChannelItemAdapter extends BaseAdapter {

    static final int LAYOUT = R.layout.feed_video_item_virew;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context ctx = parent.getContext();
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(LAYOUT, null);
        }
        NetworkImageView img = (NetworkImageView) convertView.findViewById(R.id.banner_channel);

        //img.setImageUrl(carro.imageUrl,VolleySingleton.getInstance(getContext()).getImageLoader());

        return convertView;
    }
}
