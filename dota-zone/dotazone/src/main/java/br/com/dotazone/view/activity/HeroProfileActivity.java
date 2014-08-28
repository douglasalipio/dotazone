package br.com.dotazone.view.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.squareup.picasso.Picasso;

import br.com.dotazone.DotaZoneApplication;
import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.DotazoneMenu;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.AdMobBanner;
import br.com.dotazone.model.entity.Hero;
import br.com.dotazone.model.entity.Skill;
import br.com.dotazone.model.util.UrlUtils;
import br.com.dotazone.view.components.FrameLayoutSkillBoard;
import br.com.dotazone.view.fragment.HeroSkillFragment;

public class HeroProfileActivity extends BaseActivity implements OnClickListener {

    private TextView mTextInt;
    private TextView mTextAgi;
    private TextView mTextStr;

    private TextView mTextAtk;
    private TextView mTextMove;
    private TextView mTextArmor;

    private ImageView mIconHero;
    private TextView mTextHeroName;
    private TextView mTextRoles;
    private Hero mHero = DotaZoneBrain.hero;
    private LinearLayout mLinearSkill;
    private ObjectAnimator mAnimator;
    private ImageView mIconHeroBio;
    private RelativeLayout mLinearBg;

    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerList;
    private DotazoneMenu mMenu;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hero_profile_view);
        initComponents();
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
    public void initComponents() {

        mTextAgi = (TextView) findViewById(R.id.hero_text_attribute_agi);
        mTextStr = (TextView) findViewById(R.id.hero_text_attribute_str);
        mTextInt = (TextView) findViewById(R.id.hero_text_attribute_int);
        mTextAtk = (TextView) findViewById(R.id.hero_text_attribute_atk);
        mTextMove = (TextView) findViewById(R.id.hero_text_attribute_move);
        mTextArmor = (TextView) findViewById(R.id.hero_text_attribute_armor);
        mIconHero = (ImageView) findViewById(R.id.hero_icon_avatar);
        mTextHeroName = (TextView) findViewById(R.id.hero_text_name);
        mTextRoles = (TextView) findViewById(R.id.hero_text_roles);
        mIconHeroBio = (ImageView) findViewById(R.id.hero_icon_bio);
        mIconHeroBio.setOnClickListener(this);
        mLinearBg = (RelativeLayout) findViewById(R.id.hero_linear_bg);

        int idImageBackground = getResources().getIdentifier(mHero.getIdString() + "_bg", "drawable", getPackageName());
        mLinearBg.setBackgroundResource(idImageBackground);

        String nameHero = mHero.getName().replace("_", " ");
        mTextHeroName.setText(nameHero);
        mTextRoles.setText(mHero.getAbilites().getDroles());
        Typeface font = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        mTextHeroName.setTypeface(font);

        mTextAgi.setText(mHero.getAbilites().getAgi()[0]);
        mTextInt.setText(mHero.getAbilites().getInte()[0]);
        mTextStr.setText(mHero.getAbilites().getStr()[0]);
        mTextStr.setText(mHero.getAbilites().getStr()[0]);

        mTextAtk.setText(mHero.getAbilites().getDmg()[0]);
        mTextMove.setText(mHero.getAbilites().getMs());
        mTextArmor.setText(mHero.getAbilites().getArmor());

        int idImage = getResources().getIdentifier(mHero.getIdString() + "_avatar", "drawable", getPackageName());

        if (idImage != 0) {

            Picasso.with(this).load(idImage).into(mIconHero);
            mIconHero.setImageResource(idImage);
        }

        mLinearSkill = (LinearLayout) findViewById(R.id.hero_skill_layout);
        loandingSkillsHero();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RelativeLayout) findViewById(R.id.list_slidermenu);
        mMenu = new DotazoneMenu(this, mDrawerLayout, mDrawerList);
        mMenu.checkHeroMenu();

        adView = (AdView) findViewById(R.id.hero_skill_admob);
        new AdMobBanner().createBanner(this, adView, DotaZoneBrain.isPremium);
    }

    @Override
    public void onPause() {
        adView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        adView.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adView.destroy();
    }

    private FrameLayoutSkillBoard createFrameLayoutBrightness(FrameLayout mainFrame, int idFrame) {

        FrameLayoutSkillBoard frameBrightness = new FrameLayoutSkillBoard(this);
        frameBrightness.setId(idFrame);
        mainFrame.addView(frameBrightness);
        FrameLayout.LayoutParams paramBright = (FrameLayout.LayoutParams) new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        paramBright.height = UrlUtils.convertDpToPixel(87, getResources());
        paramBright.width = UrlUtils.convertDpToPixel(87, getResources());
        frameBrightness.setLayoutParams(paramBright);

        return frameBrightness;
    }

    private FrameLayout createMainFrameSkill() {

        FrameLayout mainFrame = new FrameLayout(this);
        FrameLayout.LayoutParams paramMainFrame = (FrameLayout.LayoutParams) new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        paramMainFrame.height = UrlUtils.convertDpToPixel(87, getResources());
        paramMainFrame.width = UrlUtils.convertDpToPixel(87, getResources());
        paramMainFrame.gravity = Gravity.CENTER_HORIZONTAL;
        mainFrame.setLayoutParams(paramMainFrame);

        return mainFrame;
    }

    private ImageView createSkillIcon(FrameLayout mainFrame, int idImage) {

        ImageView skillImage = new ImageView(this);
        skillImage.setImageResource(idImage);
        mainFrame.addView(skillImage);
        FrameLayout.LayoutParams teste = (FrameLayout.LayoutParams) skillImage.getLayoutParams();
        teste.height = UrlUtils.convertDpToPixel(64, getResources());
        teste.width = UrlUtils.convertDpToPixel(64, getResources());
        teste.leftMargin = UrlUtils.convertDpToPixel(11, getResources());
        teste.topMargin = UrlUtils.convertDpToPixel(11, getResources());
        skillImage.setLayoutParams(teste);

        return skillImage;
    }

    private void loandingSkillsHero() {

        final int total = mHero.getSkills().size();

        for (int i = 0; i < total; i++) {

            final Skill skill = mHero.getSkills().get(i);
            FrameLayout mainFrame = createMainFrameSkill();
            final FrameLayoutSkillBoard frameBrightness = createFrameLayoutBrightness(mainFrame, i);
            final int idImage = getResources().getIdentifier(skill.getIdString(), "drawable", getPackageName());
            createSkillIcon(mainFrame, idImage);

            frameBrightness.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    frameBrightness.changeBackgroud();
                    animationFadeIn(frameBrightness, false);
                    for (int i = 0; i < total; i++) {
                        FrameLayoutSkillBoard lastFramLayout = (FrameLayoutSkillBoard) findViewById(i);

                        if (frameBrightness.getId() != lastFramLayout.getId())
                            lastFramLayout.removeBackground();
                    }

                    HeroSkillFragment fragment1 = HeroSkillFragment.newInstance(skill);
                    getSupportFragmentManager().beginTransaction().replace(R.id.hero_description_skill_layout, fragment1).commit();
                }
            });

            // default skill in zero position.
            if (i == 0) {

                frameBrightness.changeBackgroud();
                animationFadeIn(frameBrightness, false);
                HeroSkillFragment fragment1 = HeroSkillFragment.newInstance(skill);
                getSupportFragmentManager().beginTransaction().replace(R.id.hero_description_skill_layout, fragment1).commit();
            }

            mLinearSkill.addView(mainFrame);

        }

    }

    private void animationFadeIn(final FrameLayoutSkillBoard frameBrightness, final boolean cancel) {

        frameBrightness.setAlpha(0);
        PropertyValuesHolder propValueAlpha = PropertyValuesHolder.ofFloat("alpha", (float) 0.5, 1);
        mAnimator = ObjectAnimator.ofPropertyValuesHolder(frameBrightness, propValueAlpha);
        mAnimator.setDuration(500);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mAnimator.start();
        mAnimator.addListener(new AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

                if (cancel)
                    mAnimator.cancel();
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });

    }

    @Override
    public void onClick(View arg0) {

        startActivity(new Intent(this, HeroBiographActivity.class));

    }

    @Override
    public void setActionErrorOk() {
        finish();

    }

    @Override
    public void setActionErrorCancel() {
        finish();

    }

}
