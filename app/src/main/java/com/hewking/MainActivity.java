package com.hewking;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.hewking.widget.TanTanRippleView;

import hewking.github.customviewdemo.R;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int i = 10;
        getWindow().getDecorView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initView();
    }

    private void initView() {
        findViewById(R.id.iv_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TanTanRippleView)findViewById(R.id.ripple)).startRipple();
                AnimatorSet set = new AnimatorSet();
                set.setInterpolator(new BounceInterpolator());
                set.playTogether(
                        ObjectAnimator.ofFloat(v,"scaleX",1.2f,0.8f,1f),
                         ObjectAnimator.ofFloat(v,"scaleY",1.2f,0.8f,1f));
                set.setDuration(1100).start();
            }
        });


    }


}
