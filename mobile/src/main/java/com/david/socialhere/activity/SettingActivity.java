package com.david.socialhere.activity;

import android.content.Context;
import android.os.Bundle;

/**
 * Created by davidhodge on 1/8/15.
 */
public class SettingActivity extends BaseActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        this.finish();
    }
}
