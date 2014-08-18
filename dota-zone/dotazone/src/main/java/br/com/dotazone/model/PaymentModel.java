package br.com.dotazone.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;

import br.com.dotazone.DotaZoneBrain;
import br.com.dotazone.R;
import br.com.dotazone.model.util.Utils;
import br.com.dotazone.view.activity.MainActivity;

/**
 * Created by Douglas on 13/08/2014.
 */
public class PaymentModel {

    private final Context mContext;
    private boolean mReturn;

    public PaymentModel(Context context) {

        Parse.initialize(context, "pAITCj4uAT18pLaUe6HXXbthuLnUwPs9sDUb6C1s", "LsYMadOqhu6kGM0VIbgt4i1QvXEdolHxNxpnNa0G");
        this.mContext = context;
    }


    public boolean activeDiscountDotaZonePro(final String key) {
        // Assume ParseObject myPost was previously created.
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("promotion_key");
        query.whereEqualTo("key", key);
        Toast.makeText(mContext, mContext.getString(R.string.payment_londing_code), Toast.LENGTH_SHORT).show();
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentList, ParseException e) {

                if (!commentList.isEmpty()) {

                    for (final ParseObject comment : commentList) {

                        if (e == null) {
                            comment.put("active", true);
                            comment.put("email", Utils.getEmailUser(mContext));
                            comment.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    mReturn = true;
                                }
                            });
                        }
                    }
                } else {
                    Toast.makeText(mContext, mContext.getString(R.string.error_payment_invalid_code), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return mReturn;
    }

    public void verifyPromotionByTimeIsValid(final String emailParam) {

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("promotion_key");
        query.whereEqualTo("email", emailParam);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentList, ParseException e) {

                if (commentList != null && !commentList.isEmpty()) {

                    for (final ParseObject comment : commentList) {

                        String email = (String) comment.get("email");
                        Boolean active = (Boolean) comment.get("active");
                        if (email.equals(emailParam) && active) {
                            DotaZoneBrain.isPremium = true;
                        }
                    }
                }
            }
        });
    }

    private void disablePaymentButton(Button paymentButton) {
        paymentButton.setEnabled(false);
        paymentButton.setTextColor(Color.parseColor("#ee8366"));
    }

    private void enablePaymentButton(Button paymentButton) {
        paymentButton.setEnabled(true);
        paymentButton.setTextColor(Color.parseColor("#ffffff"));
    }

    public void activePromotionByTime(final String keyParam, final Button paymentButton) {
        // Assume ParseObject myPost was previously created.
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("promotion_key");
        Toast.makeText(mContext, mContext.getString(R.string.payment_londing_code), Toast.LENGTH_SHORT).show();
        query.whereEqualTo("key", keyParam);
        disablePaymentButton(paymentButton);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> commentList, ParseException e) {

                if (commentList != null && !commentList.isEmpty()) {

                    for (final ParseObject comment : commentList) {

                        String key = (String) comment.get("key");
                        boolean active = (Boolean) comment.get("active");

                        if (key.equals(keyParam) && (!active)) {
                            DotaZoneBrain.isPremium = true;
                            comment.put("active", true);
                            comment.put("email", Utils.getEmailUser(mContext).get(0));
                            comment.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    enablePaymentButton(paymentButton);
                                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                                }
                            });
                            Toast.makeText(mContext, mContext.getString(R.string.payment_promotion_active), Toast.LENGTH_SHORT).show();
                        } else {

                            if (active) {
                                Toast.makeText(mContext, mContext.getString(R.string.payment_error_key_used), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, mContext.getString(R.string.error_payment_invalid_code), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } else {

                    if (e != null && e.getCode() == 100) {
                        Toast.makeText(mContext, mContext.getString(R.string.payment_error_conection), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, mContext.getString(R.string.error_payment_invalid_code), Toast.LENGTH_SHORT).show();
                    }
                }
                enablePaymentButton(paymentButton);
            }
        });
    }
}
