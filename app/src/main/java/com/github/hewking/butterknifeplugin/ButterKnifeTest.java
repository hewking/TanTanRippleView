package com.github.hewking.butterknifeplugin;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;
import hewking.github.customviewdemo.R;

/**
 * Created by hewking on 2016/2/22.
 */
public class ButterKnifeTest extends Activity {
//    @Bind(R.id.popupview)
//    PopupView popupview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
