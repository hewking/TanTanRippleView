package com.hewking.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.hewking.dp2px
import hewking.github.customviewdemo.BuildConfig
import java.util.concurrent.CopyOnWriteArrayList

/**
 * 项目名称：FlowChat
 * 类的描述：
 * 创建人员：hewking
 * 创建时间：2018/12/11 0011
 * 修改人员：hewking
 * 修改时间：2018/12/11 0011
 * 修改备注：
 * Version: 1.0.0
 */
class TideRippleView(ctx: Context, attrs: AttributeSet) : View(ctx, attrs) {

    private var radiuls: Int = 0

    private val rippleCircles = CopyOnWriteArrayList<RippleCircle>()

    init {

    }

    private val ripplePaint by lazy {
        Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = dp2px(0.5f).toFloat()
            color = Color.BLUE
            isAntiAlias = true
        }
    }

    private val backPaint by lazy {
         Paint().apply {
             style= Paint.Style.FILL_AND_STROKE
             isAntiAlias = true
             strokeWidth = dp2px(0.5f).toFloat()
         }
    }

    private var sweepProgress = 0
    set(value) {
        if (value >= 360) {
            field = 0
        } else {
            field = value
        }
    }
    private var fps: Int = 0
    private var fpsPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = Color.GREEN
        textSize = dp2px(20f).toFloat()
        strokeWidth = dp2px(1f).toFloat()
    }

    private val renderAnimator by lazy {
        ValueAnimator.ofInt(0, 60)
                .apply {
                    interpolator = LinearInterpolator()
                    duration = 1000
                    repeatMode = ValueAnimator.RESTART
                    repeatCount = ValueAnimator.INFINITE
                    addUpdateListener {
                        postInvalidateOnAnimation()
                        fps++
                        sweepProgress ++

                    }
                    addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationRepeat(animation: Animator?) {
                            super.onAnimationRepeat(animation)
                            fps = 0
                        }

                    })
                }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val wMode = MeasureSpec.getMode(widthMeasureSpec)
        val wSize = MeasureSpec.getSize(widthMeasureSpec)
        val hMode = MeasureSpec.getMode(heightMeasureSpec)
        val hSize = MeasureSpec.getSize(heightMeasureSpec)
        val size = Math.min(wSize, hSize)
        if (wMode == MeasureSpec.AT_MOST || hMode == MeasureSpec.AT_MOST) {
            radiuls = size.div(2)
        }
    }

    var backCanvas : Canvas? = null
    var backBitmap : Bitmap? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        backBitmap?.recycle()
        if (w != 0 && h != 0){
            backBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
            backCanvas = Canvas(backBitmap)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        canvas.save()
//        canvas.translate(width.div(2f),height.div(2f))
        /*  rippleCircles.forEach {
          }*/

        for (i in 0 until rippleCircles.size) {
            rippleCircles[i].draw(canvas)
        }
        canvas.restore()
        backCanvas?.let {
            val maxRadius = Math.min(width, height).div(2).toFloat()
            val radius = maxRadius.div(2)
            it.save()
//            backPaint.color = Color.WHITE
            backPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.CLEAR))
            it.drawPaint(backPaint)
            backPaint.setXfermode(null)
            it.rotate(sweepProgress.toFloat(),width.div(2f),height.div(2f))
            backPaint.setShader(SweepGradient(width.div(2).toFloat(),height.div(2).toFloat(),Color.RED,Color.WHITE))
            it.drawCircle(width.div(2).toFloat(),height.div(2).toFloat(),radius,backPaint)
            backPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.SRC_OUT))
//            backPaint.color = Color.TRANSPARENT
            it.drawCircle(width.div(2f),height.div(2f),radius.div(3f),backPaint)
            it.restore()
            canvas.drawBitmap(backBitmap,0f,0f,null)
        }


        if (BuildConfig.DEBUG) {
            canvas.drawText(fps.toString(), paddingStart.toFloat()
                    , height - dp2px(10f).toFloat() - paddingBottom, fpsPaint)
        }
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        // start anim
        startRipple()
        renderAnimator.start()
    }

    private fun startRipple() {
        val runnable = Runnable {
            rippleCircles.add(RippleCircle().apply {
                cx = width.div(2).toFloat()
                cy = height.div(2).toFloat()
                val maxRadius = Math.min(width, height).div(2).toFloat()
                startRadius = maxRadius.div(2)
                endRadius = maxRadius
            })
            startRipple()
        }
        postOnAnimationDelayed(runnable, 2000)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        // end anim
        renderAnimator.end()
        backBitmap?.recycle()
    }

    inner class RippleCircle {
        // 4s * 60 frms = 240
        private val slice = 300
        var startRadius = 0f
        var endRadius = 0f
        var cx = 0f
        var cy = 0f

        private var progress = 0

        fun draw(canvas: Canvas) {
            if (progress >= slice) {
                // remove
                post {
                    rippleCircles.remove(this)
                }
                return
            }
            progress++
            ripplePaint.alpha = (1 - progress.div(slice * 1.0f)).times(255).toInt()
            val radis = startRadius + (endRadius - startRadius).div(slice).times(progress)
            canvas.drawCircle(cx, cy, radis, ripplePaint)
        }
    }

}