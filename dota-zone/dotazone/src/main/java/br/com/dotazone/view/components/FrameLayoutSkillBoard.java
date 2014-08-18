package br.com.dotazone.view.components;

import android.content.Context;
import android.widget.FrameLayout;

import br.com.dotazone.R;

public class FrameLayoutSkillBoard extends FrameLayout {

    public FrameLayoutSkillBoard(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void changeBackgroud() {

        setBackgroundResource(R.drawable.heroes_skill_brightness);
    }

    public void removeBackground() {

        setBackgroundResource(0);
    }

}
