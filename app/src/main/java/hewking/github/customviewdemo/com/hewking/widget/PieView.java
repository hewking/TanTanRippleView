package hewking.github.customviewdemo.com.hewking.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/18.
 * <p>
 * 联系方式：。。。
 */
public class PieView extends View {

    private int[] colors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private List<PieData> datas;

    private int startAngle;
    private int currentAngle;

    private int mWidth;
    private int mHeight;

    private Paint mPaint;

    public PieView(Context context) {
        this(context,null);
    }

    public PieView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = 400;
        mWidth = 400;
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.FILL);

        datas = new ArrayList<>();
        List<PieData> testDatas = new ArrayList<>();
        testDatas.add(new PieData("aa",30));
        testDatas.add(new PieData("aa",50));
        testDatas.add(new PieData("aa",70));
        testDatas.add(new PieData("aa",50));
        testDatas.add(new PieData("aa",40));
        testDatas.add(new PieData("aa",70));
        testDatas.add(new PieData("aa",60));
        setData(testDatas);

    }

    public void setData(List<PieData> pieDatas){
        initData(pieDatas);
        invalidate();
    }

    private void initData(List<PieData> pieDatas){
        int sumValue = 0;
        for(int i = 0 ; i < pieDatas.size() ; i ++){
            PieData pieData = pieDatas.get(i);
            sumValue += pieData.getValue();
            pieData.setColor(colors[i % colors.length]);
        }
        for (int i = 0; i < pieDatas.size() ; i ++){
            PieData pieData = pieDatas.get(i);
            float i1 = pieData.getValue() / (float)sumValue;
            pieData.setPrecentage(i1);
            pieData.setAngle(pieData.getPrecentage() * 360);
        }
        datas.addAll(pieDatas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(datas == null && datas.size()  == 0){
            return;
        }
        int r = (int) (Math.min(mWidth,mHeight) * 0.8);
        canvas.translate(mWidth / 2 , mHeight / 2);
        RectF rect = new RectF(-r,-r,r,r);
        for(int i = 0 ; i < datas.size() ; i ++){
            PieData pieData = datas.get(i);
            float angle = pieData.getAngle();
            mPaint.setColor(pieData.getColor());
            canvas.drawArc(rect,currentAngle,angle,true,mPaint);
            currentAngle += angle;
        }
    }
}

class PieData{
    String name;
    int value;

    int color;

    public PieData(String name, int value) {
        this.name = name;
        this.value = value;
    }

    float precentage;
    float angle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getPrecentage() {
        return precentage;
    }

    public void setPrecentage(float precentage) {
        this.precentage = precentage;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
