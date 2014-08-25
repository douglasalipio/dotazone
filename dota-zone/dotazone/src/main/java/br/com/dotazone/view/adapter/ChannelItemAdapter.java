package br.com.dotazone.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import br.com.dotazone.R;
import br.com.dotazone.model.entity.Channel;

/**
 * Created by Douglas on 24/08/2014.
 */
public class ChannelItemAdapter extends ArrayAdapter<Channel> {

    static final int LAYOUT = R.layout.feed_video_item_virew;

    public ChannelItemAdapter(Context context, List<Channel> objects) {
        super(context, LAYOUT, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context ctx = parent.getContext();
        if (convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(LAYOUT, null);
        }
        NetworkImageView img = (NetworkImageView) convertView.findViewById(R.id.banner_channel);

        //img.setImageUrl(carro.imageUrl,VolleySingleton.getInstance(getContext()).getImageLoader());

        return convertView;
    }
}
