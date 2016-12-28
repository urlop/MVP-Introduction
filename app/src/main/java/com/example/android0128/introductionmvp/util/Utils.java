package com.example.android0128.introductionmvp.util;

import android.content.Context;
import android.os.Build;

import java.util.Locale;

/**
 * Created by android0128 on 12/28/16.
 */

public class Utils {

    public static String getLocale(Context context){
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = context.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = context.getResources().getConfiguration().locale;
        }
        return locale.getLanguage();
    }
}
