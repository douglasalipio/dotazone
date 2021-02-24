package br.com.dotazone.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.viewpagerindicator.PageIndicator;

import br.com.dotazone.BuildConfig;
import br.com.dotazone.DotaZoneApplication;
import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.DotazoneMenu;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.Hero;
import br.com.dotazone.model.entity.Item;
import br.com.dotazone.model.entity.ItemAtrrib;
import br.com.dotazone.model.listeners.BuildHeroAction;
import br.com.dotazone.model.util.UrlUtils;
import br.com.dotazone.model.util.UtilActivity;
import br.com.dotazone.view.adapter.ItemAdapter;
import br.com.dotazone.view.components.RoundedImageView;

public class BuildHeroActivity extends BaseActivity implements OnClickListener, BuildHeroAction{

	public DotazoneMenu mMenu;
	private ImageView mSlot1;
	private ImageView mSlot2;
	private ImageView mSlot3;
	private ImageView mSlot4;
	private ImageView mSlot5;
	private ImageView mSlot6;
	private ImageView mBuildHelper;
	private boolean mBusySlot1;
	private boolean mBusySlot2;
	private boolean mBusySlot3;
	private boolean mBusySlot4;
	private boolean mBusySlot5;
	private boolean mBusySlot6;
	private Item mItem1;
	private Item mItem2;
	private Item mItem3;
	private Item mItem4;
	private Item mItem5;
	private Item mItem6;
	private TextView mTextInt;
	private TextView mTextAgi;
	private TextView mTextStr;
	private TextView mTextAtk;
	private TextView mTextMove;
	private TextView mTextArmor;
	private TextView mTextIncInt;
	private TextView mTextIncAgi;
	private TextView mTextIncStr;
	private TextView mTextIncAtk;
	private TextView mTextIncMove;
	private TextView mTextIncArmor;
	private TextView mTextHeroName;
	private Hero mHero = DotaZoneBrain.hero;
	private RoundedImageView mHeroIcon;
	private DrawerLayout mDrawerLayout;
	private RelativeLayout mDrawerList;
	private Item mMainBoot;
	private boolean mIsBoot;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.build_hero_view);
		initComponents();

		mTextAgi.setText(mHero.getAbilites().getAgi()[0]);
		mTextInt.setText(mHero.getAbilites().getInte()[0]);
		mTextStr.setText(mHero.getAbilites().getStr()[0]);

		mTextAtk.setText(mHero.getAbilites().getDmg()[0]);
		mTextMove.setText(mHero.getAbilites().getMs());
		mTextArmor.setText(mHero.getAbilites().getArmor());
		int idImage = getResources().getIdentifier(mHero.getIdString() + "_avatar", "drawable", getPackageName());
		mHeroIcon.setImageResource(idImage);
		Toast.makeText(this, getString(R.string.error_build_alert), Toast.LENGTH_LONG).show();
		((DotaZoneApplication) getApplication()).getTracker(DotaZoneApplication.TrackerName.APP_TRACKER);


	}

	@Override
	protected void onStart() {
		super.onStart();

		GoogleAnalytics.getInstance(this).reportActivityStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();

		// Stop the analytics tracking
		GoogleAnalytics.getInstance(this).reportActivityStop(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// Pass on the activity result to the helper for handling
	}

	@Override
	protected void onResume() {
		super.onResume();

		final SharedPreferences settings = getSharedPreferences(UrlUtils.PREFS_NAME, 0);
		boolean isHelp = settings.getBoolean("help_build_hero", true);

		if (isHelp) {

			UtilActivity.showHelpBuildHero(this);
			SharedPreferences.Editor editor = settings.edit();
			editor.putBoolean("help_build_hero", false);
			editor.commit();

		}


	}

	@Override
	public void initComponents() {


		mSlot1 = (ImageView) findViewById(R.id.build_hero_slot1);
		mSlot1.setOnClickListener(this);

		mSlot2 = (ImageView) findViewById(R.id.build_hero_slot2);
		mSlot2.setOnClickListener(this);

		mSlot3 = (ImageView) findViewById(R.id.build_hero_slot3);
		mSlot3.setOnClickListener(this);

		mSlot4 = (ImageView) findViewById(R.id.build_hero_slot4);
		mSlot4.setOnClickListener(this);

		mSlot5 = (ImageView) findViewById(R.id.build_hero_slot5);
		mSlot5.setOnClickListener(this);

		mSlot6 = (ImageView) findViewById(R.id.build_hero_slot6);
		mSlot6.setOnClickListener(this);

		mBuildHelper = (ImageView) findViewById(R.id.build_hero_help);
		mBuildHelper.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				UtilActivity.showHelpBuildHero(BuildHeroActivity.this);
			}
		});

		mTextAgi = (TextView) findViewById(R.id.hero_text_attribute_agi);
		mTextStr = (TextView) findViewById(R.id.hero_text_attribute_str);
		mTextInt = (TextView) findViewById(R.id.hero_text_attribute_int);
		mTextAtk = (TextView) findViewById(R.id.hero_text_attribute_atk);
		mTextMove = (TextView) findViewById(R.id.hero_text_attribute_move);
		mTextArmor = (TextView) findViewById(R.id.hero_text_attribute_armor);
		mTextHeroName = (TextView) findViewById(R.id.hero_text_name);

		mHeroIcon = (RoundedImageView) findViewById(R.id.build_hero_icon);

		mTextIncAgi = (TextView) findViewById(R.id.hero_text_attribute_add_agi);
		mTextIncStr = (TextView) findViewById(R.id.hero_text_attribute_add_str);
		mTextIncInt = (TextView) findViewById(R.id.hero_text_attribute_add_int);
		mTextIncAtk = (TextView) findViewById(R.id.hero_text_attribute_add_atk);
		mTextIncMove = (TextView) findViewById(R.id.hero_text_attribute_add_move);
		mTextIncArmor = (TextView) findViewById(R.id.hero_text_attribute_add_armor);

		ViewPager viewPager = (ViewPager) findViewById(R.id.build_item_pager);
		PageIndicator indicator = (PageIndicator) findViewById(R.id.indicator_build_hero);
		ItemAdapter itemAdapter = new ItemAdapter(getSupportFragmentManager(), this);
		viewPager.setAdapter(itemAdapter);
		indicator.setViewPager(viewPager);

		String nameHero = mHero.getName().replace("_", " ");
		mTextHeroName.setText(nameHero);
		Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
		mTextHeroName.setTypeface(font);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (RelativeLayout) findViewById(R.id.list_slidermenu);
		mMenu = new DotazoneMenu(this, mDrawerLayout, mDrawerList);
		mMenu.checkBuildMenu();
	}

	@Override
	public void setActionErrorOk() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActionErrorCancel() {
		// TODO Auto-generated method stub

	}


	@Override
	public void onClick(View v) {

		//TODO remover antes de subir
		if (/*DotaZoneBrain.isPremium*/true) {

			switch (v.getId()) {


				case R.id.build_hero_slot1:

					mSlot1.setBackgroundResource(R.drawable.empty);
					mBusySlot1 = false;
					if (mItem1 != null)
						decrementValuesInAttribute(mItem1.getItemAttrib());
					mItem1 = null;

					break;
				case R.id.build_hero_slot2:

					mSlot2.setBackgroundResource(R.drawable.empty);
					mBusySlot2 = false;
					if (mItem2 != null)
						decrementValuesInAttribute(mItem2.getItemAttrib());
					mItem2 = null;
					break;

				case R.id.build_hero_slot3:

					mSlot3.setBackgroundResource(R.drawable.empty);
					mBusySlot3 = false;
					if (mItem3 != null)
						decrementValuesInAttribute(mItem3.getItemAttrib());
					mItem3 = null;
					break;

				case R.id.build_hero_slot4:

					mSlot4.setBackgroundResource(R.drawable.empty);
					mBusySlot4 = false;
					if (mItem4 != null)
						decrementValuesInAttribute(mItem4.getItemAttrib());
					mItem4 = null;
					break;

				case R.id.build_hero_slot5:

					mSlot5.setBackgroundResource(R.drawable.empty);
					mBusySlot5 = false;
					if (mItem5 != null)
						decrementValuesInAttribute(mItem5.getItemAttrib());
					mItem5 = null;
					break;

				case R.id.build_hero_slot6:

					mSlot6.setBackgroundResource(R.drawable.empty);
					mBusySlot6 = false;
					if (mItem6 != null)
						decrementValuesInAttribute(mItem6.getItemAttrib());
					mItem6 = null;
					break;

				default:
					break;
			}
		} else {


		}


	}

	@Override
	public void addingItemSlot(Item item) {

		int pos = item.getImageName().lastIndexOf('.');
		int idImage = getResources().getIdentifier(item.getImageName().substring(0, pos), "drawable", getPackageName());

		if (DotaZoneBrain.isPremium) {

			if (!mBusySlot1 && item != null) {
				mSlot1.setBackgroundResource(idImage);
				incrementValuesInAttribute(item);
				mItem1 = item;
				mBusySlot1 = true;
				mSlot1.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View view) {
						if (mItem1 != null)
							UtilActivity.showDescriptionItem(mItem1, BuildHeroActivity.this);
						return false;
					}
				});

			} else if (!mBusySlot2 && item != null) {
				mSlot2.setBackgroundResource(idImage);
				mBusySlot2 = true;
				mItem2 = item;
				incrementValuesInAttribute(item);
				mSlot2.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View view) {
						if (mItem2 != null)
							UtilActivity.showDescriptionItem(mItem2, BuildHeroActivity.this);
						return false;
					}
				});

			} else if (!mBusySlot3 && item != null) {
				mSlot3.setBackgroundResource(idImage);
				mBusySlot3 = true;
				mItem3 = item;
				incrementValuesInAttribute(item);
				mSlot3.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View view) {
						if (mItem3 != null)
							UtilActivity.showDescriptionItem(mItem3, BuildHeroActivity.this);
						return false;
					}
				});

			} else if (!mBusySlot4 && item != null) {
				mSlot4.setBackgroundResource(idImage);
				mBusySlot4 = true;
				mItem4 = item;
				incrementValuesInAttribute(item);
				mSlot4.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View view) {
						if (mItem4 != null)
							UtilActivity.showDescriptionItem(mItem4, BuildHeroActivity.this);
						return false;

					}
				});

			} else if (!mBusySlot5 && item != null) {
				mSlot5.setBackgroundResource(idImage);
				mBusySlot5 = true;
				mItem5 = item;
				incrementValuesInAttribute(item);
				mSlot5.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View view) {
						if (mItem5 != null)
							UtilActivity.showDescriptionItem(mItem5, BuildHeroActivity.this);

						return false;
					}
				});

			} else if (!mBusySlot6 && item != null) {
				mSlot6.setBackgroundResource(idImage);
				mBusySlot6 = true;
				mItem6 = item;
				incrementValuesInAttribute(item);
				mSlot6.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View view) {
						if (mItem6 != null)
							UtilActivity.showDescriptionItem(mItem6, BuildHeroActivity.this);

						return false;
					}
				});

			} else {

				Toast.makeText(this, getString(R.string.full_slot), Toast.LENGTH_SHORT).show();
			}
		} else {
			showAlertBuyProVersion();
		}


	}

	@Override
	public void verifyPayment() {
	}

	private void decrementValuesInAttribute(ItemAtrrib item) {

		if (item != null) {

			int valueInt = mTextIncInt.getText().equals("") ? 0 : Integer.valueOf(UrlUtils.extractNumber(mTextIncInt.getText().toString()));
			int valueAgi = mTextIncAgi.getText().equals("") ? 0 : Integer.valueOf(UrlUtils.extractNumber(mTextIncAgi.getText().toString()));
			int valueStr = mTextIncStr.getText().equals("") ? 0 : Integer.valueOf(UrlUtils.extractNumber(mTextIncStr.getText().toString()));
			int valueAtk = mTextIncAtk.getText().equals("") ? 0 : Integer.valueOf(UrlUtils.extractNumber(mTextIncAtk.getText().toString()));
			int valueMs = mTextIncMove.getText().equals("") ? 0 : Integer.valueOf(UrlUtils.extractNumber(mTextIncMove.getText().toString()));
			int valueArmor = mTextIncArmor.getText().equals("") ? 0 : Integer.valueOf(UrlUtils.extractNumber(mTextIncArmor.getText().toString()));

			int additionalInt = item.getInteligence();
			int additionalAgi = item.getAgility();
			int additionalStr = item.getStrength();
			int additionalAtk = item.getDamage();
			int additionalMs = Integer.valueOf(item.getMs());
			int additionalArmor = item.getArmor();

			mTextIncAgi.setText(Integer.valueOf(Math.abs(valueAgi - additionalAgi)) == 0 ? String.valueOf("") : " + "
					+ Integer.valueOf(Math.abs(valueAgi - additionalAgi)).toString());
			mTextIncInt.setText(Integer.valueOf(Math.abs(valueInt - additionalInt)) == 0 ? String.valueOf("") : " + "
					+ Integer.valueOf(Math.abs(valueInt - additionalInt)).toString());
			mTextIncStr.setText(Integer.valueOf(Math.abs(valueStr - additionalStr)) == 0 ? String.valueOf("") : " + "
					+ Integer.valueOf(Math.abs(valueStr - additionalStr)).toString());

			if (mMainBoot.getItemAttrib().getId().equals(item.getId()) && additionalMs != 0 && mIsBoot) {
				mTextIncMove.setText(Integer.valueOf(Math.abs(valueMs - additionalMs)) == 0 ? String.valueOf("") : " + "
						+ Integer.valueOf(Math.abs(valueMs - additionalMs)).toString());
				mIsBoot = false;
			}

			mTextIncArmor.setText(Integer.valueOf(Math.abs(valueArmor - additionalArmor)) == 0 ? String.valueOf("") : " + "
					+ Integer.valueOf(Math.abs(valueArmor - additionalArmor)).toString());

			mTextIncAtk.setText(Integer.valueOf(Math.abs(additionalAtk - valueAtk)) == 0 ? String.valueOf("") : " + "
					+ Integer.valueOf(Math.abs(valueAtk - additionalAtk)).toString());
		}
	}

	private void incrementValuesInAttribute(Item item) {

		if (item != null && item.getItemAttrib() != null) {

			int valueInt = mTextIncInt.getText().equals("") ? 0 : Integer.valueOf(UrlUtils.extractNumber(mTextIncInt.getText().toString()));
			int valueAgi = mTextIncAgi.getText().equals("") ? 0 : Integer.valueOf(UrlUtils.extractNumber(mTextIncAgi.getText().toString()));
			int valueStr = mTextIncStr.getText().equals("") ? 0 : Integer.valueOf(UrlUtils.extractNumber(mTextIncStr.getText().toString()));
			int valueAtk = mTextIncAtk.getText().equals("") ? 0 : Integer.valueOf(UrlUtils.extractNumber(mTextIncAtk.getText().toString()));
			int valueMs = mTextIncMove.getText().equals("") ? 0 : Integer.valueOf(UrlUtils.extractNumber(mTextIncMove.getText().toString()));
			int valueArmor = mTextIncArmor.getText().equals("") ? 0 : Integer.valueOf(UrlUtils.extractNumber(mTextIncArmor.getText().toString()));

			int additionalInt = item.getItemAttrib().getInteligence();
			int additionalAgi = item.getItemAttrib().getAgility();
			int additionalStr = item.getItemAttrib().getStrength();
			int additionalAtk = item.getItemAttrib().getDamage();
			int additionalMs = Integer.valueOf(item.getItemAttrib().getMs());
			int additionalArmor = item.getItemAttrib().getArmor();

			mTextIncAgi.setText(Integer.valueOf(valueAgi + additionalAgi) == 0 ? String.valueOf("") : " + "
					+ Integer.valueOf(valueAgi + additionalAgi).toString());
			mTextIncInt.setText(Integer.valueOf(valueInt + additionalInt) == 0 ? String.valueOf("") : " + "
					+ Integer.valueOf(valueInt + additionalInt).toString());
			mTextIncStr.setText(Integer.valueOf(valueStr + additionalStr) == 0 ? String.valueOf("") : " + "
					+ Integer.valueOf(valueStr + additionalStr).toString());

			mTextIncAtk.setText(Integer.valueOf(valueAtk + additionalAtk) == 0 ? String.valueOf("") : " + "
					+ Integer.valueOf(valueAtk + additionalAtk).toString());

			if (valueMs == 0) {

				mTextIncMove.setText(Integer.valueOf(valueMs + additionalMs) == 0 ? String.valueOf("") : " + "
						+ Integer.valueOf(valueMs + additionalMs).toString());
				mMainBoot = item;
				mIsBoot = true;
			}

			mTextIncArmor.setText(Integer.valueOf(valueArmor + additionalArmor) == 0 ? String.valueOf("") : " + "
					+ Integer.valueOf(valueArmor + additionalArmor).toString());

		}
	}

	private void showAlertBuyProVersion() {


		final Dialog dialog = new Dialog(this, R.style.FullHeightDialog);
		dialog.show();
		dialog.setContentView(R.layout.alert_buy_view);
		dialog.setCancelable(false);
		Button buy = (Button) dialog.findViewById(R.id.buy_pro_version);
		Button close = (Button) dialog.findViewById(R.id.buy_close);

		close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				dialog.dismiss();
			}
		});
		buy.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
	}
}
