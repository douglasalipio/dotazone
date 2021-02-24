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
import br.com.dotazone.model.entity.Hero;

public class HeroGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<Hero> mHeroes;

    public HeroGridAdapter(Context context, List<Hero> items) {

        mContext = context;
        mHeroes = items;

    }

    @Override
    public int getCount() {
        return mHeroes.size();
    }

    @Override
    public Object getItem(int position) {
        return mHeroes.get(position);
    }

    @Override
    public long getItemId(int positionId) {
        return positionId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = convertView;

        Log.i(DotaZoneBrain.INSTANCE.getTAG(), "name image [" + mHeroes.get(position).getName() + "]");

        int id = mContext.getResources().getIdentifier(mHeroes.get(position).getIdString(), "drawable", mContext.getPackageName());

        ImageView imageView;

        if (convertView == null) {

            // get layout from mobile.xml
            view = inflater.inflate(R.layout.hero_item_grindview, parent, false);
            imageView = (ImageView) view.findViewById(R.id.imageView1);

        } else {
            // recycle objecte.
            imageView = (ImageView) view.findViewById(R.id.imageView1);
        }

        if (id != 0) {
            imageView.setImageResource(id);

        }

        return view;

    }

}
