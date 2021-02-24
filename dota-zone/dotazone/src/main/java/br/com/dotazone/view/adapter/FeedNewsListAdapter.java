package br.com.dotazone.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.listeners.Controllable;

public class FeedNewsListAdapter extends BaseAdapter implements Controllable {

    private Context mContext;
    private LayoutInflater mInflate;

    public FeedNewsListAdapter(Context context) {

        this.mContext = context;
        this.mInflate = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        Log.d(DotaZoneBrain.INSTANCE.getTAG(), "getCount  " + DotaZoneBrain.INSTANCE.getRssItems().size() + "");
        return DotaZoneBrain.INSTANCE.getRssItems().size();
    }

    @Override
    public Object getItem(int position) {

        Log.d(DotaZoneBrain.INSTANCE.getTAG(), "getItem  " + position + "");
        return DotaZoneBrain.INSTANCE.getRssItems().get(position);
    }

    @Override
    public long getItemId(int positionId) {

        Log.d(DotaZoneBrain.INSTANCE.getTAG(), "getItemId  " + positionId + "");
        return positionId;
    }

    @SuppressWarnings("deprecation")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        Log.d("teste", "position in view  " + position + "");

        if (view == null) {

            view = mInflate.inflate(R.layout.feed_news_item, parent, false);

        }
        Typeface font = Typeface.createFromAsset(mContext.getAssets(), "Roboto-Light.ttf");

        TextView titleNews = (TextView) view.findViewById(R.id.textItemTitle);
        titleNews.setText(DotaZoneBrain.INSTANCE.getRssItems().get(position).getTitle());

        TextView hourNews = (TextView) view.findViewById(R.id.textItemHour);
        hourNews.setText(DotaZoneBrain.INSTANCE.getRssItems().get(position).getPubDate());

        TextView descriptionNews = (TextView) view.findViewById(R.id.textItemDescription);
        descriptionNews.setText(Html.fromHtml(DotaZoneBrain.INSTANCE.getRssItems().get(position).getDescription()));

        descriptionNews.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(DotaZoneBrain.INSTANCE.getRssItems().get(position).getLink()));
                mContext.startActivity(browserIntent);

            }
        });

        titleNews.setTypeface(font);
        hourNews.setTypeface(font);
        descriptionNews.setTypeface(font);

        return view;
    }

    @Override
    public void menu(boolean isOpen) {
        // TODO Auto-generated method stub

    }

}
