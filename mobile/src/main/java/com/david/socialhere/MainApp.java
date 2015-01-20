package com.david.socialhere;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.mopub.common.MoPub;

import io.fabric.sdk.android.Fabric;

/**
 * Created by davidhodge on 12/13/14.
 */
public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics(), new MoPub());
    }
}
