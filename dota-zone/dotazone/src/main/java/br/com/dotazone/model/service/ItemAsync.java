package br.com.dotazone.model.service;

import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.Item;
import br.com.dotazone.model.entity.Item.ItemElementy;
import br.com.dotazone.model.entity.ItemAtrrib;
import br.com.dotazone.model.entity.ItemAtrrib.ItemAttribElementy;
import br.com.dotazone.model.util.UrlUtils;
import br.com.dotazone.view.activity.BaseActivity;
import br.com.dotazone.view.fragment.DialogError;
import br.com.dotazone.view.fragment.DialogError.TypeError;
import br.com.dotazone.view.fragment.ItemFragment;

public class ItemAsync extends AsyncTask<Void, Void, String> {

    private ProgressDialog mProgressDialog;
    private Fragment mFragment;
    private RequestREST mRequest;
    private AdapterAction mAdapterAction;
    private List<ItemAtrrib> mItemAttribs;
    private List<Item> mItems = new ArrayList<Item>();

    public ItemAsync(Fragment fragment) {

        this.mFragment = fragment;
        this.mRequest = new RequestREST();
        this.mProgressDialog = new ProgressDialog(fragment.getActivity());
        this.mAdapterAction = (ItemFragment) fragment;
    }

    @Override
    protected String doInBackground(Void... params) {
        String result = null;
        String resultAttrib = null;

        try {

            AssetManager assetManager = mFragment.getActivity().getAssets();
            InputStream inputStreamItemAttrib = assetManager.open("attribs_item.txt");
            resultAttrib = UrlUtils.convertStreamToString(inputStreamItemAttrib);
            result = mRequest.getHttpRequest(UrlUtils.getUrlItem(mFragment.getActivity()));
            if (DotaZoneBrain.items.isEmpty()) {
                createItemAttrib(resultAttrib);
                createItem(result);
            }

        } catch (Exception e) {

            Log.e(DotaZoneBrain.TAG, "Erro ao tentar consultar o arquivo de Item.--------");
            DialogError fragmentError = DialogError.newFragmentDialog(mFragment.getActivity().getResources()
                    .getString(R.string.error_item_connection), (BaseActivity) mFragment.getActivity(), TypeError.ONE_OPTIONS);
            // DotaZoneBrain.mItems = new ArrayList<Item>();
            fragmentError.show(mFragment.getActivity().getSupportFragmentManager(), "Item");
        }

        return result;
    }

    private void createItemAttrib(String result) {

        try {

            JSONObject jsonResponse = new JSONObject(result);
            JSONObject mainNode = (JSONObject) jsonResponse.get("itemdata");
            Iterator<?> keys = mainNode.keys();
            mItemAttribs = new ArrayList<ItemAtrrib>();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (mainNode.get(key) instanceof JSONObject) {

                    ItemAtrrib itemAttrib = new ItemAtrrib();
                    JSONObject jsonChild = mainNode.getJSONObject(key);
                    try {
                        itemAttrib.setId(key);
                        itemAttrib.setDamage(jsonChild.getInt(ItemAttribElementy.DAMAGE.toString().toLowerCase()));
                        itemAttrib.setAgility(jsonChild.getInt(ItemAttribElementy.AGILITY.toString().toLowerCase()));
                        itemAttrib.setStrength(jsonChild.getInt(ItemAttribElementy.STRENGTH.toString().toLowerCase()));
                        itemAttrib.setInteligence(jsonChild.getInt(ItemAttribElementy.INTELIGENCE.toString().toLowerCase()));
                        itemAttrib.setArmor(jsonChild.getInt(ItemAttribElementy.ARMOR.toString().toLowerCase()));
                        itemAttrib.setMs(jsonChild.getString(ItemAttribElementy.MS.toString().toLowerCase()));

                        mItemAttribs.add(itemAttrib);
                    } catch (JSONException e) {

                        Log.e(DotaZoneBrain.TAG, "No item [" + itemAttrib.getId() + "  -]" + e);

                    }
                }
            }

        } catch (JSONException e) {
            DialogError fragmentError = DialogError.newFragmentDialog(mFragment.getActivity().getResources().getString(R.string.error_json_item),
                    (BaseActivity) mFragment.getActivity(), TypeError.ONE_OPTIONS);
            // DotaZoneBrain.mItems = new ArrayList<Item>();
            fragmentError.show(mFragment.getActivity().getSupportFragmentManager(), "Item");
        }

    }

    public void createItem(String result) {

        try {

            JSONObject jsonResponse = new JSONObject(result);
            JSONObject mainNode = (JSONObject) jsonResponse.get("itemdata");
            Iterator<?> keys = mainNode.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (mainNode.get(key) instanceof JSONObject) {

                    Item item = new Item();
                    JSONObject jsonChild = mainNode.getJSONObject(key);
                    JSONArray componentsList = null;
                    try {

                        if (jsonChild.getString(ItemElementy.DNAME.toString().toLowerCase()).equals("Dagon")) {
                            System.out.println();
                        }

                        item.setId(jsonChild.getInt(ItemElementy.ID.toString().toLowerCase()));
                        item.setDescription(jsonChild.getString(ItemElementy.DESC.toString().toLowerCase()));
                        item.setMc(jsonChild.getString(ItemElementy.MC.toString().toLowerCase()));
                        item.setCreated(jsonChild.getBoolean(ItemElementy.CREATED.toString().toLowerCase()));
                        item.setImageName(jsonChild.getString(ItemElementy.IMG.toString().toLowerCase()));
                        item.setQual(jsonChild.getString(ItemElementy.QUAL.toString().toLowerCase()));
                        item.setName(jsonChild.getString(ItemElementy.DNAME.toString().toLowerCase()));
                        item.setLore(jsonChild.getString(ItemElementy.LORE.toString().toLowerCase()));
                        item.setCreated(jsonChild.getBoolean(ItemElementy.CREATED.toString().toLowerCase()));
                        item.setCost(jsonChild.getInt(ItemElementy.COST.toString().toLowerCase()));
                        item.setNotes(jsonChild.getString(ItemElementy.NOTES.toString().toLowerCase()));
                        item.setAttribute(jsonChild.getString(ItemElementy.ATTRIB.toString().toLowerCase()));
                        item.setCloudown(jsonChild.getString(ItemElementy.CD.toString().toLowerCase()));

                        try {
                            componentsList = jsonChild.getJSONArray(ItemElementy.COMPONENTS.toString().toLowerCase());
                        } catch (Exception e) {

                            // Trata os itens que não tem um componente.
                            Log.i(DotaZoneBrain.TAG, "no improvement items[" + item.getName() + "  -]");
                        }

                        if (componentsList != null) {
                            for (int i = 0; i < componentsList.length(); i++) {
                                item.getComponents().add(componentsList.getString(i));
                                Log.i(DotaZoneBrain.TAG, "Improvement items[" + item.getName() + "  -]");

                            }
                        }

                        identifyItemCategoryAndAdd(item);

                    } catch (JSONException e) {

                        Log.e(DotaZoneBrain.TAG, "No item [" + item.getName() + "  -]" + e);

                    }

                }

            }

        } catch (JSONException e) {
            DialogError fragmentError = DialogError.newFragmentDialog(mFragment.getActivity().getResources().getString(R.string.error_json_item),
                    (BaseActivity) mFragment.getActivity(), TypeError.ONE_OPTIONS);
            // DotaZoneBrain.mItems = new ArrayList<Item>();
            fragmentError.show(mFragment.getActivity().getSupportFragmentManager(), "Item");
        }

    }

    private void identifyItemCategoryAndAdd(Item item) {

        int pos = item.getImageName().lastIndexOf('.');
        int id = mFragment.getActivity().getResources()
                .getIdentifier(item.getImageName().substring(0, pos), "drawable", mFragment.getActivity().getPackageName());

        for (ItemAtrrib itemAttrib : mItemAttribs) {
            if (item.getImageName().equals((itemAttrib.getId() + ".png"))) {

                item.setmItemAttrib(itemAttrib);
                Log.i(DotaZoneBrain.TAG, "attribute item add-" + itemAttrib.getId());
                break;
            }
        }

        // Verifica se existem a imagem cadastrada no json.
        if (id > 0) {

            DotaZoneBrain.items.add(item);
            //mItems.add(item);
            Log.i(DotaZoneBrain.TAG, "adding Item [" + item.getImageName() + "]");

        }

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        mProgressDialog.dismiss();
        mAdapterAction.initList();
    }

    @Override
    protected void onPreExecute() {

        mProgressDialog = new ProgressDialog(mFragment.getActivity());
        mProgressDialog.setMessage("Carregando...");
        mProgressDialog.show();
        mProgressDialog.setCancelable(false);
    }
}
