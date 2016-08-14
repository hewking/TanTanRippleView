package hewking.github.customviewdemo;

import android.app.Activity;
import android.os.Bundle;

import hewking.github.customviewdemo.com.hewking.widget.MarqueTextView;

public class MainActivity extends Activity {

    private MarqueTextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int i = 10;

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

    }


}
