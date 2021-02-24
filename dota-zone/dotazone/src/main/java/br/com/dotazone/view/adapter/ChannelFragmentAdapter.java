package br.com.dotazone.view.adapter;


import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import br.com.dotazone.R;
import br.com.dotazone.view.fragment.ChannelOfflineFragment;
import br.com.dotazone.view.fragment.ChannelYoutubeFragment;

/**
 * Created by Douglas on 24/08/2014.
 */
public class ChannelFragmentAdapter extends FragmentPagerAdapter {

    private static String[] CONTENT;
    private Context mContext;

    public ChannelFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.mContext = context;
        CONTENT = new String[]{mContext.getString(R.string.online), mContext.getString(R.string.offline)};
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0)
            return ChannelYoutubeFragment.newInstance("", "");
        else
            return ChannelOfflineFragment.newInstance("", "");

    }

    @Override
    public CharSequence getPageTitle(int position) {

        return CONTENT[position % CONTENT.length].toUpperCase();
    }

    @Override
    public int getCount() {
        return CONTENT.length;
    }
}
