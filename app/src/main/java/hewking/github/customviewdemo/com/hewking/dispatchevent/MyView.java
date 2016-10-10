package hewking.github.customviewdemo.com.hewking.dispatchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2016/10/9.
 * <p>
 * 联系方式：。。。
 */
public class MyView extends RelativeLayout {

    public static final String TAG = MyView.class.getSimpleName();


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"ontouchevent myview");
        return super.onTouchEvent(event);
    }


}
