package br.com.dotazone;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.dotazone.model.billing.FeaturePremium;
import br.com.dotazone.model.listeners.Controllable;
import br.com.dotazone.view.activity.AboutActivity;
import br.com.dotazone.view.activity.LanguageActivity;
import br.com.dotazone.view.activity.MainActivity;
import br.com.dotazone.view.activity.PaymentActivity;
import br.com.dotazone.view.activity.TabActivity;

public class DotazoneMenu implements Controllable {

    public static final int MENU_HERO = 0;
    public static final int MENU_ITEM = 1;
    public static final int MENU_LANGUAGE = 2;
    public static final int MENU_BUILD = 3;
    public static final int MENU_NEWS = 4;
    public DrawerLayout drawerLayout;
    public RelativeLayout drawerList;
    private TextView mTextMenuHero;
    private TextView mTextMenuItems;
    private TextView mTextMenuNews;
    private TextView mTextMenuInvite;
    private TextView mTextMenuLanguage;
    private TextView mTextMenuBuild;
    private FragmentActivity mActivity;
    private TextView mtextMenuAbout;
    private Button mPaymentButton;
    private FrameLayout mFrameAdPro;

    public DotazoneMenu(FragmentActivity activity, DrawerLayout drawerLayout, RelativeLayout drawerList) {

        this.mActivity = activity;

        initComponents();

    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    private void displayView(int position) {

        Intent intent = new Intent(mActivity, TabActivity.class);

        switch (position) {
            case 0:
                Intent intentNews = new Intent(mActivity, MainActivity.class);
                intentNews.putExtra(MainActivity.class.getName(), MENU_NEWS);
                mActivity.startActivity(intentNews);
                break;
            case 1:
                intent.putExtra(MainActivity.class.getName(), MENU_HERO);
                mActivity.startActivity(intent);
                TabActivity.isBuild = false;

                break;
            case 2:
                intent.putExtra(MainActivity.class.getName(), MENU_ITEM);
                mActivity.startActivity(intent);
                break;

            case 5:
                mActivity.startActivity(new Intent(mActivity, LanguageActivity.class));
                break;

            case 6:
                intent.putExtra(MainActivity.class.getName(), MENU_BUILD);
                mActivity.startActivity(intent);
                TabActivity.isBuild = true;
                break;
            case 7:

                mActivity.startActivity(new Intent(mActivity, AboutActivity.class));
                break;

            default:
                break;
        }

        drawerLayout.closeDrawer(drawerList);
    }

    public void defaultItemSelected() {
        mTextMenuNews.setTextColor(Color.RED);
    }

    public void checkHeroMenu() {

        mTextMenuItems.setTextColor(Color.WHITE);
        mTextMenuHero.setTextColor(Color.RED);
        mTextMenuNews.setTextColor(Color.WHITE);
        mTextMenuLanguage.setTextColor(Color.WHITE);
        mTextMenuInvite.setTextColor(Color.WHITE);
        mtextMenuAbout.setTextColor(Color.WHITE);
    }

    public void checkItemMenu() {

        mTextMenuItems.setTextColor(Color.RED);
        mTextMenuHero.setTextColor(Color.WHITE);
        mTextMenuNews.setTextColor(Color.WHITE);
        mTextMenuLanguage.setTextColor(Color.WHITE);
        mTextMenuInvite.setTextColor(Color.WHITE);
        mtextMenuAbout.setTextColor(Color.WHITE);
    }

    public void checkLanguageMenu() {

        mTextMenuItems.setTextColor(Color.WHITE);
        mTextMenuHero.setTextColor(Color.WHITE);
        mTextMenuNews.setTextColor(Color.WHITE);
        mTextMenuInvite.setTextColor(Color.WHITE);
        mTextMenuLanguage.setTextColor(Color.RED);
        mtextMenuAbout.setTextColor(Color.WHITE);

    }

    public void checkInviteMenu() {

        mTextMenuItems.setTextColor(Color.WHITE);
        mTextMenuHero.setTextColor(Color.WHITE);
        mTextMenuNews.setTextColor(Color.WHITE);
        mTextMenuLanguage.setTextColor(Color.WHITE);
        mTextMenuInvite.setTextColor(Color.RED);
        mtextMenuAbout.setTextColor(Color.WHITE);

    }

    public void checkNewsMenu() {

        mTextMenuItems.setTextColor(Color.WHITE);
        mTextMenuHero.setTextColor(Color.WHITE);
        mTextMenuLanguage.setTextColor(Color.WHITE);
        mTextMenuNews.setTextColor(Color.RED);
        mTextMenuInvite.setTextColor(Color.WHITE);
        mtextMenuAbout.setTextColor(Color.WHITE);
    }

    public void checkBuildMenu() {

        mTextMenuItems.setTextColor(Color.WHITE);
        mTextMenuHero.setTextColor(Color.WHITE);
        mTextMenuNews.setTextColor(Color.WHITE);
        mTextMenuInvite.setTextColor(Color.WHITE);
        mTextMenuLanguage.setTextColor(Color.WHITE);
        mTextMenuBuild.setTextColor(Color.RED);
        mtextMenuAbout.setTextColor(Color.WHITE);
    }

    public void checkAboutMenu() {

        mTextMenuItems.setTextColor(Color.WHITE);
        mTextMenuHero.setTextColor(Color.WHITE);
        mTextMenuNews.setTextColor(Color.WHITE);
        mTextMenuInvite.setTextColor(Color.WHITE);
        mTextMenuLanguage.setTextColor(Color.WHITE);
        mTextMenuBuild.setTextColor(Color.WHITE);
        mtextMenuAbout.setTextColor(Color.RED);
    }

    public void defaultMenu() {

        mTextMenuItems.setTextColor(Color.WHITE);
        mTextMenuHero.setTextColor(Color.WHITE);
        mTextMenuNews.setTextColor(Color.WHITE);
        mTextMenuInvite.setTextColor(Color.WHITE);
        mTextMenuLanguage.setTextColor(Color.WHITE);
        mTextMenuBuild.setTextColor(Color.WHITE);
        mtextMenuAbout.setTextColor(Color.WHITE);
    }

    public void initComponents() {

        Typeface font = Typeface.createFromAsset(mActivity.getAssets(), "Roboto-Thin.ttf");

        drawerLayout = (DrawerLayout) mActivity.findViewById(R.id.drawer_layout);
        drawerList = (RelativeLayout) mActivity.findViewById(R.id.list_slidermenu);
        mTextMenuHero = (TextView) mActivity.findViewById(R.id.textMenuHero);
        mFrameAdPro = (FrameLayout) mActivity.findViewById(R.id.content_drawer_list_account_premium);
        mTextMenuHero.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                checkHeroMenu();
                displayView(1);

            }
        });
        mTextMenuItems = (TextView) mActivity.findViewById(R.id.textMenuItems);
        mTextMenuItems.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                checkItemMenu();
                displayView(2);
            }
        });
        mTextMenuNews = (TextView) mActivity.findViewById(R.id.textMenuNews);
        mTextMenuNews.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                checkNewsMenu();
                displayView(0);

            }
        });
        mTextMenuInvite = (TextView) mActivity.findViewById(R.id.textMenuInvite);
        mTextMenuInvite.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                checkInviteMenu();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = mActivity.getString(R.string.share_app_text);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                mActivity.startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

        mTextMenuLanguage = (TextView) mActivity.findViewById(R.id.textMenuLanguage);
        mTextMenuLanguage.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                checkLanguageMenu();
                displayView(5);

            }
        });

        mTextMenuBuild = (TextView) mActivity.findViewById(R.id.textMenuBuild);
        mTextMenuBuild.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                checkBuildMenu();
                displayView(6);

            }
        });

        mtextMenuAbout = (TextView) mActivity.findViewById(R.id.textMenuAbout);
        mtextMenuAbout.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                checkAboutMenu();
                displayView(7);

            }
        });

        mtextMenuAbout.setTypeface(font);
        mTextMenuHero.setTypeface(font);
        mTextMenuItems.setTypeface(font);
        mTextMenuNews.setTypeface(font);
        mTextMenuInvite.setTypeface(font);
        mTextMenuLanguage.setTypeface(font);
        mTextMenuBuild.setTypeface(font);

        mPaymentButton = (Button) mActivity.findViewById(R.id.content_drawer_buy_button);
        mPaymentButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                mActivity.startActivity(new Intent(mActivity, PaymentActivity.class));

            }
        });

        defaultItemSelected();

        FeaturePremium featurePremium = new FeaturePremium(mActivity);
        featurePremium.unlockOrLockAdPro(mPaymentButton, mFrameAdPro, DotaZoneBrain.isPremium);
    }

    @Override
    public void menu(boolean isOpen) {

        drawerLayout.openDrawer(drawerList);

    }


}
