package br.com.dotazone.view.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import br.com.dotazone.R;
import br.com.dotazone.view.activity.BuildHeroActivity;
import br.com.dotazone.view.fragment.ItemFragment;

public class ItemAdapter extends FragmentPagerAdapter {

	private static String[] CONTENT;
	private BuildHeroActivity mActivity;
	private Context mContext;

	public ItemAdapter(FragmentManager fm, Context context) {
		super(fm);

		this.mContext = context;
		CONTENT = new String[]{context.getString(R.string.item_basic), context.getString(R.string.item_improvements),
				context.getString(R.string.item_secret_store)};
	}

	public ItemAdapter(FragmentManager fm, BuildHeroActivity activity) {
		super(fm);

		this.mActivity = activity;
		CONTENT = new String[]{activity.getString(R.string.item_basic), activity.getString(R.string.item_improvements),
				activity.getString(R.string.item_secret_store)};
	}

	@Override
	public Fragment getItem(int position) {

		if (mActivity != null) {
			return ItemFragment.Companion.newInstance(CONTENT[position % CONTENT.length], mActivity);
		}

		return ItemFragment.Companion.newInstance(CONTENT[position % CONTENT.length]);
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
