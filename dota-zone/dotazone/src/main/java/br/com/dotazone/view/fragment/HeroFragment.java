package br.com.dotazone.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.Ability.AbilityElementy;
import br.com.dotazone.model.entity.Hero;
import br.com.dotazone.model.entity.Item;
import br.com.dotazone.model.service.AdapterAction;
import br.com.dotazone.model.service.HeroAsync;
import br.com.dotazone.view.activity.BuildHeroActivity;
import br.com.dotazone.view.activity.HeroProfileActivity;
import br.com.dotazone.view.activity.TabActivity;
import br.com.dotazone.view.adapter.HeroGridAdapter;

public class HeroFragment extends Fragment implements AdapterAction, OnItemClickListener {

    private static final String KEY_CONTENT = "TestFragment:Content";
    private View mView;
    private GridView mGridViewItem;
    private String mContent = "???";

    public static HeroFragment newInstance(String content) {
        HeroFragment f = new HeroFragment();
        f.mContent = content;
        return f;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }

        mView = inflater.inflate(R.layout.tab_grid_hero_view, container, false);
        mGridViewItem = (GridView) mView.findViewById(R.id.gridview_item);
        mGridViewItem.setOnItemClickListener(this);

        if (DotaZoneBrain.heroes.isEmpty())
            new HeroAsync(this).execute();
        else
            initList();

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void initListIHero(List<Hero> heroes) {


    }

    @Override
    public void initList() {

        List<Hero> itemsForTab = new ArrayList<Hero>();

        if (getString(R.string.hero_agility).equals(mContent)) {

            for (Hero hero : DotaZoneBrain.heroes) {

                if (hero.getAbilites() != null && hero.getAbilites().getPa().equals(AbilityElementy.AGI.getValue())) {

                    itemsForTab.add(hero);
                }
            }
        } else if (getString(R.string.hero_strength).equals(mContent)) {

            for (Hero hero : DotaZoneBrain.heroes) {

                if (hero.getAbilites() != null && hero.getAbilites().getPa().equals(AbilityElementy.STR.getValue())) {

                    itemsForTab.add(hero);
                }
            }
        } else

            for (Hero hero : DotaZoneBrain.heroes) {

                if (hero.getAbilites() != null && hero.getAbilites().getPa().equals(AbilityElementy.INT.getValue())) {

                    itemsForTab.add(hero);
                }
            }

        mGridViewItem.setAdapter(new HeroGridAdapter(getActivity(), itemsForTab));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        DotaZoneBrain.hero = (Hero) parent.getAdapter().getItem(position);

        if (TabActivity.isBuild) {

            startActivity(new Intent(getActivity(), BuildHeroActivity.class));

        } else {

            startActivity(new Intent(getActivity(), HeroProfileActivity.class));
        }
    }

    @Override
    public void initListItem(List<Item> items) {
        // TODO Auto-generated method stub

    }

    @Override
    public void initRating() {
        // TODO Auto-generated method stub

    }

}
