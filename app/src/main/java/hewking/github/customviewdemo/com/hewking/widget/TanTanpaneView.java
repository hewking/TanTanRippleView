package hewking.github.customviewdemo.com.hewking.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.os.Message;
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
    private Paint mGradientPaint;
    private Matrix rotateMatrix;

    private int start;

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
        Handler.post(rotateRunnable);
    }

    private android.os.Handler Handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
        }
    };

    private Runnable rotateRunnable = new Runnable() {
        @Override
        public void run() {
            rotateMatrix = new Matrix();
            start+= 2;
            rotateMatrix.postRotate(start,mWidth / 2,mHeight / 2);
            TanTanpaneView.this.invalidate();
            Handler.postDelayed(rotateRunnable,10);
        }
    };

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(0xFFD72E1F);
        mGradientPaint = new Paint();
        mGradientPaint.setStrokeWidth(3);
        mGradientPaint.setAntiAlias(true);
        mGradientPaint.setColor(0xFFD72E1F);
        SweepGradient shader = new SweepGradient(mWidth / 2, mHeight / 2 , Color.TRANSPARENT,0xFFD72E1F);
        mGradientPaint.setShader(shader);
        rotateMatrix = new Matrix();
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
        invalidate();
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
        canvas.concat(rotateMatrix);
        canvas.drawCircle(mWidth / 2 ,mHeight / 2,mWidth / 2,mGradientPaint);
        if(mAlphaPaint.size() != 0){
            invalidate();
        }
        super.onDraw(canvas);
    }
}
