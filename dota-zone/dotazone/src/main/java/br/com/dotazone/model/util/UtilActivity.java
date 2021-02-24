package br.com.dotazone.model.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.entity.Item;

public class UtilActivity {

    private static boolean isBuy;

    public static void ratingDotaZoneDialog(final Context context) {

        final SharedPreferences settings = context.getSharedPreferences(UrlUtils.PREFS_NAME, 0);
        int rating = settings.getInt(DotaZoneBrain.RATING_DOTA_ZONE, 0);

        if (rating == 3) {

            final Dialog dialog = new Dialog(context, R.style.FullHeightDialog);
            dialog.show();
            dialog.setContentView(R.layout.alert_rating_view);
            dialog.setCancelable(false);
            Button mButtonRating = (Button) dialog.findViewById(R.id.rating_start);
            Button mButtonClose = (Button) dialog.findViewById(R.id.rating_close);

            mButtonClose.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });
            mButtonRating.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=" + context.getApplicationContext().getPackageName()));
                    context.startActivity(intent);

                }
            });

        }

    }

    public static boolean showAlertBuyProVersion(final Context context) {


        final Dialog dialog = new Dialog(context, R.style.FullHeightDialog);
        dialog.show();
        dialog.setContentView(R.layout.alert_buy_view);
        dialog.setCancelable(false);
        Button buy = (Button) dialog.findViewById(R.id.buy_pro_version);
        Button close = (Button) dialog.findViewById(R.id.buy_close);

        close.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        buy.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                isBuy = true;
                dialog.dismiss();

            }
        });

        return isBuy;


    }


    public static void showHelpBuildHero(Context context) {
        // custom dialog
        final Dialog dialog = new Dialog(context, R.style.FullHeightDialogTransparent);
        dialog.setContentView(R.layout.help_build_hero);
        RelativeLayout layoutClose = (RelativeLayout) dialog.findViewById(R.id.layout_help_build_hero);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public static void showDescriptionItem(Item item, Context context) {

        // custom dialog
        final Dialog dialog = new Dialog(context, R.style.FullHeightDialog);
        dialog.setContentView(R.layout.item_dialog_view);

        TextView itemName = (TextView) dialog.findViewById(R.id.textItemName);
        TextView itemAttribute = (TextView) dialog.findViewById(R.id.textAttribute);
        TextView itemCd = (TextView) dialog.findViewById(R.id.textCdTimer);
        TextView itemCost = (TextView) dialog.findViewById(R.id.textCost);
        TextView itemDescription = (TextView) dialog.findViewById(R.id.textDescription);
        TextView itemMana = (TextView) dialog.findViewById(R.id.textMana);
        ImageView itemIcon = (ImageView) dialog.findViewById(R.id.imageItem);
        LinearLayout itemLayoutComponents = (LinearLayout) dialog.findViewById(R.id.itemLayoutComponents);


        itemName.setText(item.getName());
        itemAttribute.setText(Html.fromHtml(item.getAttribute()));
        String cd = item.getCloudown().equals("false") ? context.getString(R.string.item_no_cd) : item.getCloudown();

        String mana = item.getMc().equals("false") ? context.getString(R.string.item_no_cd) : item.getMc();
        itemCd.setText(cd);
        itemCost.setText(item.getCost().toString());
        itemDescription.setText(Html.fromHtml(item.getDescription()));
        itemMana.setText(mana);
        int pos = item.getImageName().lastIndexOf('.');
        int idImage = context.getResources().getIdentifier(item.getImageName().substring(0, pos), "drawable",
                context.getPackageName());

        itemIcon.setImageResource(idImage);
        setImageComponents(item, itemLayoutComponents, context);

        dialog.show();

    }

    private static void setImageComponents(Item item, LinearLayout linearLayout, Context context) {

        if (!item.getComponents().isEmpty()) {

            for (String itemName : item.getComponents()) {

                int idImage = context.getResources().getIdentifier(itemName + "_lg", "drawable", context.getPackageName());
                if (idImage != 0) {
                    ImageView imageView = new ImageView(context);
                    imageView.setImageResource(idImage);
                    linearLayout.addView(imageView);
                    LinearLayout.LayoutParams param = (LinearLayout.LayoutParams) new LinearLayout.LayoutParams(UrlUtils.convertDpToPixel(50,
                            context.getResources()), UrlUtils.convertDpToPixel(32, context.getResources()));
                    imageView.setLayoutParams(param);
                }

            }
        }

    }

}
