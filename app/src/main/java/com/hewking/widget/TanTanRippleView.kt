package com.hewking.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.support.v4.content.ContextCompat.getColor
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.hewking.dp2px
import com.hewking.getColor
import hewking.github.customviewdemo.BuildConfig
import hewking.github.customviewdemo.R

import java.util.concurrent.CopyOnWriteArrayList

/**
 * 项目名称：FlowChat
 * 类的描述：xfermode 的使用采用canvas.drawBitmap 的方式实现
 * 创建人员：hewking
 * 创建时间：2018/12/11 0011
 * 修改人员：hewking
 * 修改时间：2018/12/11 0011
 * 修改备注：
 * Version: 1.0.0
 */
class TanTanRippleView(ctx: Context, attrs: AttributeSet) : View(ctx, attrs) {

    private var radiuls: Int = 0

    private val rippleCircles = CopyOnWriteArrayList<RippleCircle>()

    init {

    }

    private val ripplePaint by lazy {
        Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = dp2px(0.5f).toFloat()
            color = getColor(R.color.color_FF434343)
            isAntiAlias = true
        }
    }

    private val backPaint by lazy {
        Paint().apply {
            style = Paint.Style.FILL
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
                        sweepProgress++

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

    var backCanvas: Canvas? = null
    var backBitmap: Bitmap? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        backBitmap?.recycle()
        if (w != 0 && h != 0) {
            backBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            backCanvas = Canvas(backBitmap)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        val maxRadius = Math.min(width, height).div(2).toFloat()
        val radius = maxRadius
        canvas.save()
        canvas.rotate(sweepProgress.toFloat(), width.div(2f), height.div(2f))

        val colors = intArrayOf(getColor(R.color.pink_fa758a), getColor(R.color.pink_f5b8c2), getColor(R.color.top_background_color), getColor(R.color.white))
        backPaint.setShader(SweepGradient(width.div(2).toFloat(), height.div(2).toFloat(), colors, floatArrayOf(0f, 0.001f, 0.9f, 1f)))
        val rectF = RectF(width.div(2f) - radius
                , height.div(2f) - radius
                , width.div(2f) + radius
                , height.div(2f) + radius)
        val sc = canvas.saveLayer(rectF, backPaint, Canvas.ALL_SAVE_FLAG)
//        canvas.drawBitmap(makeDst(), null,rectF, backPaint)
        canvas.drawCircle(width.div(2).toFloat(), height.div(2).toFloat(), radius, backPaint)
        backPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.DST_OUT))
//        canvas.drawCircle(width.div(2f), height.div(2f), radius.div(3f), backPaint)
/*        rectF.apply {
            left = width.div(2f) - radius * 1f.div(3)
            top = height.div(2f) - radius * 1f.div(3)
            right = width.div(2f) + radius * 1f.div(3)
            bottom = height.div(2f) + radius * 1f.div(3)
        }
        canvas.drawBitmap(makeSrc(),null,rectF,backPaint)*/
        canvas.drawCircle(width.div(2f), height.div(2f), radius.div(3f), backPaint)
        backPaint.setXfermode(null)
        backPaint.setShader(null)
        canvas.restoreToCount(sc)
        canvas.restore()

        for (i in 0 until rippleCircles.size) {
            rippleCircles[i].draw(canvas)
        }

        if (BuildConfig.DEBUG) {
            canvas.drawText(fps.toString(), paddingStart.toFloat()
                    , height - dp2px(10f).toFloat() - paddingBottom, fpsPaint)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        // start anim
//        startRipple()
        renderAnimator.start()
    }

    open fun startRipple() {
        val runnable = Runnable {
            rippleCircles.add(RippleCircle().apply {
                cx = width.div(2).toFloat()
                cy = height.div(2).toFloat()
                val maxRadius = Math.min(width, height).div(2).toFloat()
                startRadius = maxRadius.div(3)
                endRadius = maxRadius
            })
//            startRipple()
        }
        postOnAnimation(runnable)
//        postOnAnimationDelayed(runnable, 2000)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        // end anim
        renderAnimator.end()
        backBitmap?.recycle()
    }

    inner class RippleCircle {
        // 4s * 60 frms = 240
        private val slice = 150
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