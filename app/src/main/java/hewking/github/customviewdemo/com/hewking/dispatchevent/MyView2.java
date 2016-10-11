package hewking.github.customviewdemo.com.hewking.dispatchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/10/9.
 * <p>
 * 联系方式：。。。
 */
public class MyView2 extends ImageView {

    public static final String TAG = MyView2.class.getSimpleName();

    public MyView2(Context context) {
        super(context);
    }

    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"ontouchevent myview2");
        return super.onTouchEvent(event);
    }
}
