package br.com.dotazone.view.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.com.dotazone.R;
import br.com.dotazone.model.entity.Skill;
import br.com.dotazone.model.util.Utils;
import br.com.dotazone.view.components.ImageGetter;

public class HeroSkillFragment extends BaseFragment {

    private View mView;
    private Skill mSkill;
    private TextView mTextSkillName;
    private LinearLayout mLinearSkill;


    public static final HeroSkillFragment newInstance(Skill skill) {
        HeroSkillFragment f = new HeroSkillFragment();
        f.mSkill = skill;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.hero_descripition_skill_view, container, false);

        initComponents();

        return mView;
    }

    @Override
    public void initComponents() {

        mTextSkillName = (TextView) mView.findViewById(R.id.hero_text_skill_name);
        mLinearSkill = (LinearLayout) mView.findViewById(R.id.linear_skill_description);
        mTextSkillName.setText(mSkill.getdName());

        String affects = mSkill.getAffectes().equals("") ? null : mSkill.getAffectes();
        String desc = mSkill.getDesc().equals("") ? null : mSkill.getDesc();
        String notes = mSkill.getNotes().equals("") ? null : mSkill.getNotes();
        String dmg = mSkill.getDmg().equals("") ? null : mSkill.getDmg();
        String attribs = mSkill.getAttrib().equals("") ? null : mSkill.getAttrib();
        String cmb = mSkill.getCmb().equals("") ? null : mSkill.getCmb();
        String lore = mSkill.getLore().equals("") ? null : mSkill.getLore();
        createAttributsSkill(cmb, true, 3);
        createAttributsSkill(affects, false, 20);
        createAttributsSkill(attribs, false, 20);
        createAttributsSkill(desc, false, 20);
        createAttributsSkill(notes, false, 20);
        createAttributsSkill(dmg, false, 20);
        createAttributsSkill(lore, false, 20);


    }


    private void createAttributsSkill(String attribute, boolean imageInHTML, int countLine) {

        if (attribute != null) {

            TextView textView = new TextView(getActivity());
            textView.setTextSize(13);
            textView.setTextColor(getResources().getColor(R.color.color_text_list));
            textView.setMaxLines(countLine);
            textView.setIncludeFontPadding(false);
            if (imageInHTML) {
                textView.setText(Html.fromHtml(attribute, new ImageGetter(getActivity()), null));

            } else {

                textView.setText(Html.fromHtml(attribute));
            }

            // textView.setText(((String)
            // textView.getText().toString()).replace("\n", " "));
            mLinearSkill.addView(textView);
            LinearLayout.LayoutParams paramSkill = (LinearLayout.LayoutParams) new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            paramSkill.topMargin = Utils.convertDpToPixel(10, getResources());
            paramSkill.leftMargin = Utils.convertDpToPixel(10, getResources());
            paramSkill.rightMargin = Utils.convertDpToPixel(10, getResources());
            // paramSkill.setMargins(Utils.convertDpToPixel(0, getResources()),
            // Utils.convertDpToPixel(-5, getResources()),
            // Utils.convertDpToPixel(0, getResources()),
            // Utils.convertDpToPixel(0, getResources()));
            textView.setLayoutParams(paramSkill);

        }

    }
}
