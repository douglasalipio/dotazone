package br.com.dotazone.view.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import br.com.dotazone.R;
import br.com.dotazone.view.fragment.HeroFragment;

public class HeroAdapter extends FragmentPagerAdapter {

    private static String[] CONTENT;
    private Context mContext;

    public HeroAdapter(FragmentManager fm, Context context) {
        super(fm);

        this.mContext = context;
        CONTENT = new String[]{mContext.getString(R.string.hero_agility), mContext.getString(R.string.hero_strength),
                mContext.getString(R.string.hero_intelligence)};
    }

    @Override
    public Fragment getItem(int position) {

        return HeroFragment.newInstance(CONTENT[position % CONTENT.length]);
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
