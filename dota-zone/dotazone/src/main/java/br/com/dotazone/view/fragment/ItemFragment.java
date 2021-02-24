package br.com.dotazone.view.fragment;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.Hero;
import br.com.dotazone.model.entity.Item;
import br.com.dotazone.model.listeners.BuildHeroAction;
import br.com.dotazone.model.service.ItemAsync;
import br.com.dotazone.model.util.UrlUtils;
import br.com.dotazone.view.activity.BaseActivity;
import br.com.dotazone.view.activity.BuildHeroActivity;
import br.com.dotazone.view.adapter.ItemGridAdapter;
import br.com.dotazone.view.fragment.DialogError.TypeError;

public class ItemFragment extends Fragment implements AdapterAction, OnItemClickListener {

    private static final String KEY_CONTENT = "TestFragment:Content";
    private View mView;
    private GridView mGridViewItem;
    private String mContent = "???";
    private BuildHeroActivity mActivity;


    public static ItemFragment newInstance(String content) {
        ItemFragment f = new ItemFragment();
        f.mContent = content;
        return f;

    }

    public static ItemFragment newInstance(String content, BuildHeroActivity activity) {
        ItemFragment f = new ItemFragment();
        f.mContent = content;
        f.mActivity = activity;
        return f;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }

        mView = inflater.inflate(R.layout.tab_grid_item_view, container, false);
        mGridViewItem = (GridView) mView.findViewById(R.id.gridview_item);
        mGridViewItem.setOnItemClickListener(this);

        if (DotaZoneBrain.items.isEmpty())
            new ItemAsync(this).execute();
        else
            initList();

        return mView;
    }


    @Override
    public void initListItem(List<Item> items) {

    }

    @Override
    public void initList() {
        List<Item> itemsForTab = new ArrayList<Item>();

        // Valida se existe item a ser tratado
        if (DotaZoneBrain.items != null && (!DotaZoneBrain.items.isEmpty())) {

            if (getString(R.string.item_secret_store).equals(mContent)) {

                for (Item item : DotaZoneBrain.items) {

                    if (item.getQual().equals("secret_shop")) {
                        itemsForTab.add(item);
                    }
                }
            } else if (getString(R.string.item_improvements).equals(mContent)) {

                for (Item item : DotaZoneBrain.items) {

                    if (!item.getComponents().isEmpty()) {
                        itemsForTab.add(item);
                    }
                }

            } else if (getString(R.string.item_basic).equals(mContent)) {

                for (Item item : DotaZoneBrain.items) {

                    if (item.getQual().equals("common") || item.getQual().equals("component")) {
                        itemsForTab.add(item);
                    }
                }
            }
            mGridViewItem.setAdapter(new ItemGridAdapter(getActivity(), itemsForTab));

            if (mActivity != null) {
                BuildHeroAction buildAction = (BuildHeroActivity) mActivity;
                buildAction.verifyPayment();
            }
        } else {

            DialogError fragmentError = DialogError.newFragmentDialog(getActivity().getResources().getString(R.string.error_json_item),
                    (BaseActivity) getActivity(), TypeError.ONE_OPTIONS);
            fragmentError.show(getActivity().getSupportFragmentManager(), "Item");
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (mActivity != null) {

            BuildHeroAction buildAction = (BuildHeroActivity) mActivity;
            buildAction.addingItemSlot((Item) parent.getAdapter().getItem(position));


        } else {
            // custom dialog
            final Dialog dialog = new Dialog(getActivity(), R.style.FullHeightDialog);
            dialog.setContentView(R.layout.item_dialog_view);

            TextView itemName = (TextView) dialog.findViewById(R.id.text_Item_name);
            TextView itemAttribute = (TextView) dialog.findViewById(R.id.text_attribute);
            TextView itemCd = (TextView) dialog.findViewById(R.id.text_cd_timer);
            TextView itemCost = (TextView) dialog.findViewById(R.id.text_Cost);
            TextView itemDescription = (TextView) dialog.findViewById(R.id.text_descripion);
            TextView itemMana = (TextView) dialog.findViewById(R.id.text_mana);
            ImageView itemIcon = (ImageView) dialog.findViewById(R.id.imageItem);
            LinearLayout itemLayoutComponents = (LinearLayout) dialog.findViewById(R.id.item_layout_components);

            Item item = (Item) parent.getAdapter().getItem(position);

            itemName.setText(item.getName());
            itemAttribute.setText(Html.fromHtml(item.getAttribute()));
            String cd = item.getCloudown().equals("false") ? getActivity().getResources().getString(R.string.item_no_cd) : item.getCloudown();

            String mana = item.getMc().equals("false") ? getActivity().getResources().getString(R.string.item_no_cd) : item.getMc();
            itemCd.setText(cd);
            itemCost.setText(item.getCost().toString());
            itemDescription.setText(Html.fromHtml(item.getDescription()));
            itemMana.setText(mana);
            int pos = item.getImageName().lastIndexOf('.');
            int idImage = getActivity().getResources().getIdentifier(item.getImageName().substring(0, pos), "drawable",
                    getActivity().getPackageName());

            itemIcon.setImageResource(idImage);
            setImageComponents(item, itemLayoutComponents);

            dialog.show();
        }

    }

    private void setImageComponents(Item item, LinearLayout linearLayout) {

        if (!item.getComponents().isEmpty()) {

            for (String itemName : item.getComponents()) {

                int idImage = getActivity().getResources().getIdentifier(itemName + "_lg", "drawable", getActivity().getPackageName());
                if (idImage != 0) {
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setImageResource(idImage);
                    linearLayout.addView(imageView);
                    LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) new LinearLayout.LayoutParams(UrlUtils.convertDpToPixel(50,
                            getResources()), UrlUtils.convertDpToPixel(32, getResources()));
                    imageView.setLayoutParams(param);
                }

            }
        }

    }

    @Override
    public void initListIHero(List<Hero> heroes) {
        // TODO Auto-generated method stub

    }

    @Override
    public void initRating() {
        // TODO Auto-generated method stub

    }

}
