package hewking.github.customviewdemo.com.hewking.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import hewking.github.customviewdemo.com.hewking.custom.util.LOG;

/**
 * Created by hewking on 2016/1/14.
 */
public class PopupView extends View {

    private int mWidth;

    private int mHeight;

    private int mRectWidth;

    private int mRectHeight;

    private double mRate = 0.8;

    Paint rectPaint;

    Paint pathPaint;

    public PopupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public PopupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLUE);
        rectPaint.setAntiAlias(true);
        rectPaint.setStrokeWidth(3);
        rectPaint.setStyle(Paint.Style.FILL);

        pathPaint = new Paint();
        pathPaint.setColor(Color.RED);
        pathPaint.setAntiAlias(true);
        pathPaint.setStrokeWidth(3);

    }

    public PopupView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if(MeasureSpec.EXACTLY == widthMode){
            mWidth = widthSize;
            mRectWidth = (int) (mWidth * mRate);
        }
        if(MeasureSpec.EXACTLY == heightMode){
            mHeight = heightSize;
            mRectHeight = (int) (mHeight * mRate);
        }


        setMeasuredDimension(mWidth  ,mHeight );
        LOG.cjh("width = " + mWidth + "height = " + mHeight + "rectwidth = " + mRectWidth + "rectheight" + mRectHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                mCx = (int) event.getX();
                mCy = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    private  int mCx = 100;
    private int mCy = 100;

    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawRoundRect(new RectF(0,0,mRectWidth,mRectHeight),10,10,rectPaint);

        Path path = new Path();
        path.moveTo(mRectWidth / 2 - 30, mRectHeight);
        path.lineTo(mRectWidth / 2 , mRectHeight  + 10);
        path.lineTo(mRectWidth / 2 + 30 , mRectHeight);

//        path.moveTo(50,100);
//        path.lineTo(100,0);
//        path.lineTo(100,100);

        path.close();
        canvas.drawPath(path,pathPaint);

//        canvas.drawCircle(mCx,mCy,50,pathPaint);

        super.onDraw(canvas);
    }
}
