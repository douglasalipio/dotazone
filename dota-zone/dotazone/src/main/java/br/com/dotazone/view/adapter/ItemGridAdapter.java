package br.com.dotazone.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.Item;

public class ItemGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<Item> mItems;

    public ItemGridAdapter(Context context, List<Item> items) {

        mContext = context;
        mItems = items;

    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int positionId) {
        return positionId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = convertView;

        Log.i(DotaZoneBrain.TAG, "name image [" + mItems.get(position).getImageName() + "]");

        int pos = mItems.get(position).getImageName().lastIndexOf('.');
        int id = mContext.getResources().getIdentifier(mItems.get(position).getImageName().substring(0, pos), "drawable",
                mContext.getPackageName());

        if (convertView == null) {

            // get layout from mobile.xml
            view = inflater.inflate(R.layout.item_item_grindview, parent, false);

        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
        imageView.setImageResource(id);

        return view;

    }

}