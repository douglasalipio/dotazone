package br.com.dotazone.view.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;

import br.com.dotazone.R;

public class ImageGetter implements Html.ImageGetter {

    private Context context;

    public ImageGetter(Context context) {

        this.context = context;
    }

    @Override
    public Drawable getDrawable(String source) {

        if (source.indexOf("mana") != -1) {

            Drawable d = context.getResources().getDrawable(R.drawable.items_icon_mana);
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            return d;
        } else if (source.indexOf("cooldown") != -1) {

            Drawable d = context.getResources().getDrawable(R.drawable.items_icon_duration);
            d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            return d;
        }

        return null;
    }

}
