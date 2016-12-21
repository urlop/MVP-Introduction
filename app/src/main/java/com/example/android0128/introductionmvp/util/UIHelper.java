package com.example.android0128.introductionmvp.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;

import com.example.android0128.introductionmvp.R;

/**
 * Created by tk-0130 on 11/8/16.
 */

public class UIHelper {
    public static void createErrorDialog(String title, String message, final Context context){
        final AlertDialog.Builder builder = new AlertDialog.Builder(
                new ContextThemeWrapper(context, R.style.AppTheme_NoActionBar));
        builder.setTitle(title);
        builder.setMessage(message);


        final AlertDialog alertDialog = builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
                    }
                }).create();


        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(context, R.color.dark_grey));
            }
        });

        alertDialog.show();
    }
}