package hewking.github.customviewdemo.com.hewking.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/9/19.
 * <p>
 * 联系方式：。。。
 */
public class CanvasView extends View{

    private Paint mCanvasPaint;

    private int mHeight;
    private int mWidth;

    public CanvasView(Context context) {
        this(context,null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCanvasPaint = new Paint();
        mCanvasPaint.setAntiAlias(true);
        mCanvasPaint.setColor(Color.BLUE);
        mCanvasPaint.setStyle(Paint.Style.STROKE);
        mCanvasPaint.setStrokeWidth(2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    /**
     * translate 平移基于当前位置 并且可以叠加
     * @param canvas
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        canvas.drawCircle(0,0,100,mCanvasPaint);
//        canvas.translate(200,200);
//        mCanvasPaint.setColor(Color.BLACK);
//        canvas.drawCircle(0,0,100,mCanvasPaint);
//    }

//    /**
//     * scale 缩放 可叠加
//     * @param canvas
//     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        canvas.translate(mWidth / 2 , mHeight / 2);
////        Rect rect = new Rect(0,-400,400,0);
//        Rect rect = new Rect(-400,-400,400,400);
//        canvas.drawRect(rect,mCanvasPaint);
//        for(int i = 0 ; i < 100 ; i ++){
//            canvas.scale(0.9f,0.9f);
//            canvas.drawRect(rect,mCanvasPaint);
//        }
////        canvas.scale(0.5f,0.5f);
////        canvas.scale(0.5f,0.5f,200,0);
////        canvas.scale(-0.5f,-0.5f,200,0);
//    }

    /**
     * rotate 旋转
     * @param canvas
     */
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.translate(mWidth / 2 ,mHeight / 2);
//        canvas.drawCircle(0,0,400,mCanvasPaint);
//        canvas.drawCircle(0,0,380,mCanvasPaint);
//        for(int i=0,j=0;i < 36 ;i ++){
//            canvas.drawLine(0,380,0,400,mCanvasPaint);
//            canvas.rotate(10);
//        }
//    }
}
