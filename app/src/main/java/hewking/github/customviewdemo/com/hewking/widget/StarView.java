package hewking.github.customviewdemo.com.hewking.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hewking on 2016/1/29.
 */
public class StarView extends View {

    private int mHeight;
    private int mWidth;

    public StarView(Context context) {
        this(context,null);
    }

    public StarView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        if(wMode == MeasureSpec.EXACTLY){
            mWidth = MeasureSpec.getSize(widthMeasureSpec);
        }

        if(hMode == MeasureSpec.EXACTLY){
             mHeight = MeasureSpec.getSize(heightMeasureSpec);
        }
        mWidth = mHeight > mWidth ? mHeight : mWidth;
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {



        super.onDraw(canvas);
    }
}
