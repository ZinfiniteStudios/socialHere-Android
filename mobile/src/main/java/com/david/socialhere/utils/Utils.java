package com.david.socialhere.utils;

import android.util.Log;

/**
 * Created by davidhodge on 12/13/14.
 */
public class Utils {

    public static void longInfo(String str) {
        if(str.length() > 4000) {
            Log.i("tag", str.substring(0, 4000));
            longInfo(str.substring(4000));
        } else
            Log.i("tag", str);
    }
}
