package br.com.dotazone.view.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.GoogleAnalytics;

import java.util.ArrayList;
import java.util.List;

import br.com.dotazone.DotaZoneApplication;
import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.DotazoneMenu;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.Hero;
import br.com.dotazone.model.entity.Item;
import br.com.dotazone.model.util.UrlUtils;
import nl.matshofman.saxrssreader.RssItem;

public class LanguageActivity extends BaseActivity implements OnClickListener {

    public static final String LANGUAGE_KEY = "language_value";
    public static final String IS_LANGUAGE_SELECTED = "language_selected";
    private static TextView mLanguageText;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerList;
    private DotazoneMenu mMenu;
    private LinearLayout mLinearMenu;
    private List<String> mStringLanguages;
    private LinearLayout mLayoutLanguage;

    @Override
    protected void onCreate(Bundle bundleState) {
        super.onCreate(bundleState);

        setContentView(R.layout.language_view);
        ((DotaZoneApplication) getApplication()).getTracker(DotaZoneApplication.TrackerName.APP_TRACKER);

        initComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMenu.checkLanguageMenu();
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

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RelativeLayout) findViewById(R.id.list_slidermenu);
        mLinearMenu = (LinearLayout) findViewById(R.id.linearLayout1);
        mMenu = new DotazoneMenu(this, mDrawerLayout, mDrawerList);
        mMenu.checkLanguageMenu();
        getDefaultSelected();
        addLanguageInArray();
        mLanguageText = (TextView) findViewById(R.id.language_text);
        mLanguageText.setText(getStringSelected());
        mLayoutLanguage = (LinearLayout) findViewById(R.id.layout_language);
        mLayoutLanguage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                LanguageDialogFragment.newFragmentDialog(mStringLanguages, getDefaultSelected()).show(getSupportFragmentManager(), "language");

            }
        });

        mLinearMenu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        });
    }

    protected void addLanguageInArray() {
        String[] mLanguageArray = getResources().getStringArray(R.array.language_array);
        mStringLanguages = new ArrayList<String>();
        for (int i = 0; i < mLanguageArray.length; i++) {

            mStringLanguages.add(mLanguageArray[i]);
        }


    }

    private int getDefaultSelected() {

        final SharedPreferences settings = getSharedPreferences(UrlUtils.PREFS_NAME, 0);
        String defaultSelected = settings.getString(LANGUAGE_KEY, "english");
        String[] mLanguageArray = getResources().getStringArray(R.array.language_array);

        for (int i = 0; i < mLanguageArray.length; i++) {

            if (defaultSelected.equalsIgnoreCase(mLanguageArray[i])) {
                return i;
            }

        }
        return 0;
    }

    private String getStringSelected() {

        final SharedPreferences settings = getSharedPreferences(UrlUtils.PREFS_NAME, 0);
        String defaultSelected = settings.getString(LANGUAGE_KEY, "english");
        String[] mLanguageArray = getResources().getStringArray(R.array.language_array);

        for (int i = 0; i < mLanguageArray.length; i++) {

            if (defaultSelected.equals(mLanguageArray[i].toLowerCase())) {
                return mLanguageArray[i];
            }

        }
        return defaultSelected;
    }

    @Override
    public void setActionErrorOk() {

    }

    @Override
    public void setActionErrorCancel() {

    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void finish() {
        super.finish();

        mMenu.defaultMenu();

    }

    public static class LanguageDialogFragment extends DialogFragment {

        private List<String> languages;
        private int position;

        public static LanguageDialogFragment newFragmentDialog(List<String> languages, int position) {
            LanguageDialogFragment f = new LanguageDialogFragment();
            f.languages = languages;
            f.position = position;
            return f;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Set the dialog title
            builder.setTitle(R.string.settings_menu)
                    // Specify the list array, the items to be selected by default (null for none),
                    // and the listener through which to receive callbacks when items are selected
                    .setSingleChoiceItems(R.array.language_array, position, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int id) {
                            SharedPreferences settings = getActivity().getSharedPreferences(UrlUtils.PREFS_NAME, 0);
                            SharedPreferences.Editor editor = settings.edit();
                            editor.putBoolean(IS_LANGUAGE_SELECTED, true);
                            editor.putString(LANGUAGE_KEY, languages.get(id));
                            DotaZoneBrain.rssItems = new ArrayList<RssItem>();
                            DotaZoneBrain.items = new ArrayList<Item>();
                            DotaZoneBrain.heroes = new ArrayList<Hero>();
                            // Commit the edits!
                            editor.commit();

                            mLanguageText.setText(languages.get(id));

                        }
                    })
                            // Set the action buttons
                    .setPositiveButton(R.string.option_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton(R.string.option_no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            return builder.create();
        }
    }

}
