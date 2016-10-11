package hewking.github.customviewdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateInterpolator;

import hewking.github.customviewdemo.com.hewking.widget.MarqueTextView;

public class MainActivity extends Activity {

    private MarqueTextView tv;

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

    private int index = 0 ;
    private String[] names = {"aaa","bbbb","ccc"};

    private void initView() {
//       tv = (MarqueTextView) findViewById(R.id.tv_marque);
//        Timer timer =  new Timer();
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                index ++;
//                if (index > 2) {
//                    index = 0;
//                }
//                MainActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        tv.setText(names[index]);
//                    }
//                });
//
//            }
//        };
//        timer.schedule(task,1000,1000);
        findViewById(R.id.iv_head).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("aaaa","onclick");
//                v.animate().setDuration(1000).scaleX(2).scaleY(2).setInterpolator(new BounceInterpolator()).start();
                AnimatorSet set = new AnimatorSet();
                set.setInterpolator(new AnticipateInterpolator());
                set.playTogether(
                        ObjectAnimator.ofFloat(v,"scaleX",1f,2f),
                         ObjectAnimator.ofFloat(v,"scaleX",2f,1f),
                         ObjectAnimator.ofFloat(v,"scaleY",1f,2f),
                         ObjectAnimator.ofFloat(v,"scaleY",2f,1f));
                set.setDuration(1000).start();
            }
        });


    }


}
