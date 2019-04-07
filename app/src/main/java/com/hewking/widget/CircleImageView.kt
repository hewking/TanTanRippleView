package com.hewking.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.widget.ImageView
import com.hewking.dp2px
import hewking.github.customviewdemo.R

class CircleImageView(ctx : Context, attrs: AttributeSet) : ImageView(ctx,attrs) {

    private var borderWidth = dp2px(1f).toFloat()

    private val path by lazy {
        Path()
    }

    init {
        val typeArray = ctx.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
        borderWidth = typeArray.getDimensionPixelSize(R.styleable.CircleImageView_c_border_width,borderWidth.toInt()).toFloat()
        typeArray.recycle()
    }

    private val mBorderPaint by lazy {
        Paint().apply{
            isAntiAlias = true
            style = Paint.Style.STROKE
            color = Color.WHITE
            strokeWidth = borderWidth
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = Math.min(getMeasuredWidth(),getMeasuredHeight())
        setMeasuredDimension(size,size)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?:return
        val radius = Math.min(width,height).div(2f)
        path.addCircle(width.div(2f),height.div(2f),radius,Path.Direction.CW)
        canvas.clipPath(path)
        canvas.drawCircle(width.div(2f),height.div(2f),radius,mBorderPaint)
        canvas.save()
        canvas.scale(0.9f,0.9f)
        super.onDraw(canvas)
        canvas.restore()
    }

}
