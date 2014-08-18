package br.com.dotazone.model.service;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.Ability;
import br.com.dotazone.model.entity.Ability.AbilityElementy;
import br.com.dotazone.model.entity.Hero;
import br.com.dotazone.model.entity.Hero.HeroElementy;
import br.com.dotazone.model.entity.Skill;
import br.com.dotazone.model.entity.Skill.SkillElementy;
import br.com.dotazone.model.util.Utils;
import br.com.dotazone.view.activity.BaseActivity;
import br.com.dotazone.view.fragment.DialogError;
import br.com.dotazone.view.fragment.DialogError.TypeError;
import br.com.dotazone.view.fragment.HeroFragment;

public class HeroAsync extends AsyncTask<Void, Void, String> {

    private ProgressDialog mProgressDialog;
    private Fragment mFragment;
    private RequestREST mRequest;
    private AdapterAction mAdapterAction;
    private List<Hero> mHeroes;
    private List<Skill> mSkills;
    private List<Ability> mAbilities;

    public HeroAsync(Fragment fragment) {

        this.mFragment = fragment;
        this.mRequest = new RequestREST();
        this.mProgressDialog = new ProgressDialog(fragment.getActivity());
        this.mAdapterAction = (HeroFragment) fragment;
        this.mSkills = new ArrayList<Skill>();
        this.mHeroes = new ArrayList<Hero>();
        this.mAbilities = new ArrayList<Ability>();
    }

    @Override
    protected String doInBackground(Void... param) {

        String resultHero = null;
        String resultSkill = null;
        String resultAbility = null;

        try {

            resultHero = mRequest.getHttpRequest(Utils.getUrlHero(mFragment.getActivity()));
            resultSkill = mRequest.getHttpRequest(Utils.getUrlSkill(mFragment.getActivity()));
            resultAbility = mRequest.getHttpRequest(Utils.getUrlAbility(mFragment.getActivity()));

            if (DotaZoneBrain.heroes.isEmpty()) {
                createHero(resultHero);
                createSkill(resultSkill);
                createAbility(resultAbility);
                combineHeroToSkillsAndAttributes();
            }

        } catch (Exception e) {

            Log.e(DotaZoneBrain.TAG, "Erro ao tentar consultar o arquivo de Hero.--------");
            e.printStackTrace();

            DialogError fragmentError = DialogError.newFragmentDialog(mFragment.getActivity().getResources()
                    .getString(R.string.error_hero_connection), (BaseActivity) mFragment.getActivity(), TypeError.ONE_OPTIONS);
            // DotaZoneBrain.mHeroes = new ArrayList<Hero>();
            fragmentError.show(mFragment.getActivity().getSupportFragmentManager(), "Hero");
        }

        return null;
    }

    private void combineHeroToSkillsAndAttributes() {

        if (!(mHeroes.isEmpty() && mSkills.isEmpty())) {

            for (int i = 0; i < mHeroes.size(); i++) {

                for (int j = 0; j < mSkills.size(); j++) {

                    String heroName = mHeroes.get(i).getName();
                    String skillHeroName = mSkills.get(j).getHurl();

                    if (heroName.equalsIgnoreCase(skillHeroName)) {

                        mHeroes.get(i).getSkills().add(mSkills.get(j));
                        Log.i("teste", "yes______" + mHeroes.get(i).getName() + "-----" + mSkills.get(j).getHurl());

                    }
                }

                for (int y = 0; y < mAbilities.size(); y++) {

                    String heroName = mHeroes.get(i).getIdString().replaceAll("[A-Z/a-z/_/-]", "");
                    String skillHeroName = mAbilities.get(y).getU().replaceAll("[A-Z/a-z/_/-]", "");

                    if (mHeroes.get(i).getIdString().equals(mAbilities.get(y).getIdString())) {

                        mHeroes.get(i).setAbilites((mAbilities.get(y)));
                        break;
                    }

                    if (mHeroes.get(i).getName().equals("Anti-Mage") && mAbilities.get(y).getU().equals("Anti-Mage")) {

                        mHeroes.get(i).setAbilites(mAbilities.get(y));
                    }

                    if (mHeroes.get(i).getName().equals("Nature's_Prophet") && mAbilities.get(y).getU().equals("Natures_Prophet")) {

                        mHeroes.get(i).setAbilites(mAbilities.get(y));
                    }

                }

            }

            DotaZoneBrain.heroes = this.mHeroes;

        }
    }

    @Override
    protected void onPreExecute() {

        mProgressDialog = new ProgressDialog(mFragment.getActivity());
        mProgressDialog.setMessage(mFragment.getActivity().getString(R.string.loading));
        mProgressDialog.show();
        mProgressDialog.setCancelable(false);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        mProgressDialog.dismiss();
        mAdapterAction.initList();
    }

    private void createAbility(String result) {

        try {

            JSONObject jsonResponse = new JSONObject(result);
            JSONObject mainNode = (JSONObject) jsonResponse.get("herodata");
            Iterator<?> keys = mainNode.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                Ability ability = new Ability();
                if (mainNode.get(key) instanceof JSONObject) {

                    JSONObject jsonChild = mainNode.getJSONObject(key);
                    try {
                        ability.setIdString(key);
                        ability.setName(jsonChild.getString(AbilityElementy.DNAME.getValue()));
                        ability.setU(jsonChild.getString(AbilityElementy.U.getValue()));
                        ability.setPa(jsonChild.getString(AbilityElementy.PA.getValue()));
                        ability.setDroles(jsonChild.getString(AbilityElementy.DROLES.getValue()));
                        ability.setDac(jsonChild.getString(AbilityElementy.DAC.getValue()));

                        JSONObject jsonAttribs = jsonChild.getJSONObject(AbilityElementy.ATTRIBS.getValue());

                        JSONObject childAttribsInt = jsonAttribs.getJSONObject(AbilityElementy.INT.getValue());
                        String intAbilityA = childAttribsInt.getString(AbilityElementy.B.getValue());
                        String intAbilityB = childAttribsInt.getString(AbilityElementy.G.getValue());
                        ability.setInte(new String[]{intAbilityA, intAbilityB});

                        JSONObject childAttribsAgi = jsonAttribs.getJSONObject(AbilityElementy.AGI.getValue());
                        String agiAbilityA = childAttribsAgi.getString(AbilityElementy.B.getValue());
                        String agiAbilityB = childAttribsAgi.getString(AbilityElementy.G.getValue());
                        ability.setAgi(new String[]{agiAbilityA, agiAbilityB});

                        JSONObject childAttribsStr = jsonAttribs.getJSONObject(AbilityElementy.STR.getValue());
                        String strAbilityA = childAttribsStr.getString(AbilityElementy.B.getValue());
                        String strAbilityB = childAttribsStr.getString(AbilityElementy.G.getValue());
                        ability.setStr(new String[]{strAbilityA, strAbilityB});

                        JSONObject childAttribsDmg = jsonAttribs.getJSONObject(AbilityElementy.DMG.getValue());
                        String dmgAbilityA = childAttribsDmg.getString(AbilityElementy.MIN.getValue());
                        String dmgAbilityB = childAttribsDmg.getString(AbilityElementy.MAX.getValue());
                        ability.setDmg(new String[]{dmgAbilityA, dmgAbilityB});

                        ability.setMs(jsonAttribs.getString(AbilityElementy.MS.getValue()));
                        ability.setArmor(jsonAttribs.getString(AbilityElementy.ARMOR.getValue()));

                        mAbilities.add(ability);

                    } catch (JSONException e) {
                        Log.e(DotaZoneBrain.TAG, "No ability [" + ability.getName() + "  -]" + e);
                        errorMessengerJSON();
                    }

                }

            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            errorMessengerJSON();
        }
    }

    private void errorMessengerJSON() {

        DialogError fragmentError = DialogError.newFragmentDialog(mFragment.getActivity().getResources().getString(R.string.error_json_hero),
                (BaseActivity) mFragment.getActivity(), TypeError.ONE_OPTIONS);
        // DotaZoneBrain.mHeroes = new ArrayList<Hero>();
        fragmentError.show(mFragment.getActivity().getSupportFragmentManager(), "Hero");
    }

    public void createSkill(String result) {

        try {

            JSONObject jsonResponse = new JSONObject(result);
            JSONObject mainNode = (JSONObject) jsonResponse.get("abilitydata");
            Iterator<?> keys = mainNode.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                Skill skill = new Skill();
                if (mainNode.get(key) instanceof JSONObject) {

                    JSONObject jsonChild = mainNode.getJSONObject(key);
                    try {
                        skill.setIdString(key);
                        skill.setAffectes(jsonChild.getString(SkillElementy.AFFECTS.toString().toLowerCase()));
                        skill.setAttrib(jsonChild.getString(SkillElementy.ATTRIB.toString().toLowerCase()));
                        skill.setCmb(jsonChild.getString(SkillElementy.CMB.toString().toLowerCase()));
                        skill.setDesc(jsonChild.getString(SkillElementy.DESC.toString().toLowerCase()));
                        skill.setDmg(jsonChild.getString(SkillElementy.DMG.toString().toLowerCase()));
                        skill.setdName(jsonChild.getString(SkillElementy.DNAME.toString().toLowerCase()));
                        skill.setHurl(jsonChild.getString(SkillElementy.HURL.toString().toLowerCase()));
                        skill.setLore(jsonChild.getString(SkillElementy.LORE.toString().toLowerCase()));
                        skill.setNotes(jsonChild.getString(SkillElementy.NOTES.toString().toLowerCase()));
                        mSkills.add(skill);

                    } catch (JSONException e) {
                        errorMessengerJSON();
                        Log.e(DotaZoneBrain.TAG, "No ability [" + skill.getdName() + "  -]" + e);
                    }

                }

            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void createHero(String result) {

        try {

            JSONObject jsonResponse = new JSONObject(result);
            Iterator<?> keys = jsonResponse.keys();

            while (keys.hasNext()) {

                Hero hero = new Hero();
                String key = (String) keys.next();
                if (jsonResponse.get(key) instanceof JSONObject) {

                    JSONObject jsonChild = jsonResponse.getJSONObject(key);
                    JSONArray rolesList = null;
                    JSONArray rolesLList = null;
                    try {

                        hero.setIdString(key);
                        hero.setAtk(jsonChild.getString(HeroElementy.ATK.toString().toLowerCase()));
                        hero.setBio(jsonChild.getString(HeroElementy.BIO.toString().toLowerCase()));
                        hero.setName(jsonChild.getString(HeroElementy.NAME.toString().toLowerCase()));

                        String padrao = "\\s";
                        String padrao1 = "[- /.]";
                        Pattern regPat = Pattern.compile(padrao);
                        Pattern regPat1 = Pattern.compile(padrao1);
                        Matcher matcher = regPat.matcher(hero.getName());
                        String nameWithUnderline = matcher.replaceAll("_");
                        Matcher matcherTrace = regPat1.matcher(nameWithUnderline);
                        nameWithUnderline = matcherTrace.replaceAll("-");
                        hero.setName(nameWithUnderline.replace("'", ""));

                        try {

                            rolesList = jsonChild.getJSONArray(HeroElementy.ROLES.toString().toLowerCase());
                            rolesLList = jsonChild.getJSONArray(HeroElementy.ROLES_L.toString().toLowerCase());

                        } catch (Exception e) {
                            // Trata os nos que não tem um componente.
                            Log.e(DotaZoneBrain.TAG, "no ability hero[" + hero.getName() + "  -]");
                        }

                        if (rolesList != null) {
                            for (int i = 0; i < rolesList.length(); i++) {
                                hero.getRoles().add(rolesList.getString(i));
                                Log.i(DotaZoneBrain.TAG, "ability hero roles add[" + hero.getName() + "  -]");

                            }
                        }

                        if (rolesLList != null) {
                            for (int i = 0; i < rolesList.length(); i++) {
                                hero.getRolesL().add(rolesLList.getString(i));
                                Log.i(DotaZoneBrain.TAG, "ability hero roles_l add[" + hero.getName() + "  -]");

                            }
                        }

                        mHeroes.add(hero);

                    } catch (JSONException e) {
                        errorMessengerJSON();
                        Log.e(DotaZoneBrain.TAG, "No hero [" + hero.getName() + "  -]" + e);
                    }

                }

            }

        } catch (JSONException e) {
            errorMessengerJSON();
            e.printStackTrace();
        }

    }
}
