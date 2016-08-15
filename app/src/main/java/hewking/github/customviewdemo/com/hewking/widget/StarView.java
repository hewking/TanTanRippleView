package hewking.github.customviewdemo.com.hewking.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hewking on 2016/1/29.
 */
public class StarView extends View {

    private int mHeight;
    private int mWidth;

    private Paint mPaint;

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
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
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

        RectF rectF = new RectF(0, 0, mWidth, mHeight);
        Path path = new Path();
        canvas.drawRect(rectF,mPaint);
        path.moveTo(rectF.centerX(),rectF.centerY());
        for (int i =0 ; i < 5 ; i ++){
            canvas.rotate(72,rectF.centerX(),rectF.centerY());
            canvas.drawCircle(rectF.centerX() + 100,rectF.centerY() + 100,1,mPaint);
            canvas.drawLine(rectF.centerX() + 30,rectF.centerY(),rectF.centerX() + 100,rectF.centerY() + 100,mPaint);
            canvas.drawLine(rectF.centerX(),rectF.centerY()+ 30,rectF.centerX() + 100,rectF.centerY() + 100,mPaint);
        }
        canvas.rotate(- 72 * 5,rectF.centerX(),rectF.centerY());

        super.onDraw(canvas);
    }
}
