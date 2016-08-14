package hewking.github.customviewdemo.com.hewking.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hewking on 2016/1/30.
 */
public class HCircleView extends View {

    private Paint cicleP;
    private Paint arcP;
    private Paint lineP;
    private Paint textP;

    private int mWidth;
    private int mHeight;

    public HCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        cicleP = new Paint();
        cicleP.setStrokeWidth(5);
        cicleP.setAntiAlias(true);
        cicleP.setColor(Color.RED);
    }

    public HCircleView(Context context) {
        this(context,null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(mWidth / 2 , mHeight /2 ,mWidth / 2 ,cicleP);
//        canvas.drawText("hello",mWidth / 2- 30, mWidth / 2 + 30,0,0,cicleP);
    }
}
