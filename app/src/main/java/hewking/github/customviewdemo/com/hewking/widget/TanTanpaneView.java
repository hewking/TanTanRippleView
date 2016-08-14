package hewking.github.customviewdemo.com.hewking.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hewking on 2016/8/13.
 */
public class TanTanpaneView extends View {

    public static final String TAG = TanTanpaneView.class.getSimpleName();

    private int mHeight;
    private int mWidth;

    private Paint mPaint;

    float mRippleRadius = 4;

    private List<Integer> mAlphaPaint = new ArrayList<>();
    private List<Float> mWidthStart = new ArrayList<>();

    public TanTanpaneView(Context context) {
        this(context,null);
    }

    public TanTanpaneView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TanTanpaneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(0xFFD72E1F);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"onTouchEvent : " + event.getAction());
                startRippleAnim();
                break;
        }
//        return super.onTouchEvent(event);
        return true;
    }

    private void startRippleAnim() {
        mAlphaPaint.add(255);
        mWidthStart.add(mHeight / 8.0f);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        if(MeasureSpec.EXACTLY == wMode){
            mWidth = MeasureSpec.getSize(widthMeasureSpec);
        }
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        if(MeasureSpec.EXACTLY == hMode){
            mHeight = MeasureSpec.getSize(heightMeasureSpec);
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0 ; i < mAlphaPaint.size() ; i++){
            mPaint.setAlpha(mAlphaPaint.get(i));
            canvas.drawCircle(mWidth / 2 , mHeight / 2,mWidthStart.get(i)+ 30,mPaint);
            mAlphaPaint.set(i,mAlphaPaint.get(i) - 1);
            mWidthStart.set(i,mWidthStart.get(i) + 1);
            if(mAlphaPaint.get(i) == 0){
                mAlphaPaint.remove(i);
                mWidthStart.remove(i);
            }
        }
        invalidate();
        super.onDraw(canvas);
    }
}
