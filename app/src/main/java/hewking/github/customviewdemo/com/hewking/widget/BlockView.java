package hewking.github.customviewdemo.com.hewking.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import hewking.github.customviewdemo.R;

/**
 * Created by hewking on 2016/7/29.
 */
public class BlockView extends View {

    private float borderWidth;
    private int borderColor;

    private Paint mPaint;
    private RectF mBounds;
    private float width;
    private float height;

    private float radius;

    private int smallLength;
    private int largeLength;


    public BlockView(Context context) {
        this(context,null);
    }

    public BlockView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BlockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BlockView,0,0);
        borderColor = array.getColor(R.styleable.BlockView_border_color,0xffff0000);
        borderWidth = array.getDimension(R.styleable.BlockView_border_width,3);
        array.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(borderWidth);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mBounds = new RectF(getLeft(),getTop(),getRight(),getBottom());
        width = mBounds.right - mBounds.left;
        height = mBounds.bottom - mBounds.top;

        if(width > height){
            radius = width / 4;
        }else{
            radius = height / 4;
        }

        smallLength = 10;
        largeLength = 20;

        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.BLUE);
        canvas.drawRoundRect(new RectF(mBounds.left + 5,mBounds.top + 5,mBounds.right - 5,mBounds.bottom - 5),30,30,mPaint);
        canvas.drawCircle(mBounds.centerX() + 5,mBounds.centerY() + 5,radius,mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mBounds.centerX() + 5,mBounds.centerY() + 5,30,mPaint);
        canvas.drawLine(mBounds.centerX() + 5,mBounds.centerY() + 5,mBounds.centerX() + 5,mBounds.centerY() - radius,mPaint);
        float baseX = mBounds.centerX() + 5;
        float baseY = mBounds.centerY() + 5;
        for (int i = 0 ; i <60 ; i ++){
            float baseLengthX = radius * (float)Math.sin( 6 * i) + baseX;
            float baseLengthY = radius * (float)Math.cos( 6 * i) + baseY;
            float baseSLengthX = (radius - 10) * (float)Math.sin( 6 * i) + baseX;
            float baseSLengthY = (radius - 10)* (float)Math.cos( 6 * i) + baseY;
            canvas.drawLine(baseLengthX,baseLengthY,baseSLengthX,baseSLengthY,mPaint);
        }
    }
}
