> 闲着也是闲着的时候，打开探探划一划，挺多男同胞会这样吧。这不，我也是这样，看到首页探探的效果还是挺吸引人的。之前仿照实现了一个，效果还差一点，正好今天没事完善一下，写下来，希望看到能有收获。

##### 实现的效果
首先看看实现后的效果，先不多说。当然跟探探的原版还是有差距的，没有在细节上面优化的更多。不过花时间调一调还是可以的，现在的效果可以看到，我在下面加了帧数的显示，在真机上显示还是很流畅的，模拟器上由于性能不行还是有点卡。

![实现效果](https://user-gold-cdn.xitu.io/2019/4/7/169f81ecb5cb9a18?w=480&h=845&f=gif&s=1986428)


##### 实现的分析
通过效果图可以看到，整体的实现可以分为以下四步：
1. 波纹涟漪的效果
2. 渐变扫描的效果和中间的镂空
3. 旋转
4. 点击头像的动画

把以上步骤分别加以实现，就可以做到了。具体实现方法也不止一种，我这里选择的实现还算是简单易懂，易于实现的。以下分解各个步骤，并对关键的细节详加解释。

##### 如何实现
因为有头像，并且涉及到加载网络图片。理论上来说我们可以直接继承ImageView来实现，可是这样太复杂了，是不可取的。所以头像跟我们现在所要实现效果是分开的。然后在跟头像组合在一起，这里可以使自定义一个ViewGroup把两者结合，我这里图省事，这里就没有去做了，而是直接在使用的时候，在布局里面组合在一起。

1. 所以第一步先不考虑头像而是实现TanTanRippleView.接下来看水波纹的实现：
我们需要的是，波纹是动态添加的，通过点击头像添加，所以需要暴露接口。并且波纹是有渐变的，越到边缘透明度越低，直到消失。每一个波纹都是一个圆，透明度通过改变Paint的颜色即可，透明度跟圆的半径也是有规律可循的。所以我这里把每个波纹做了封装。
```
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
```
看到以上代码可能对slice这个属性有疑惑，这是定义波纹持续时间的，如果60帧每秒，那么持续4s，总共是240帧。这里默认取150帧，所以在60帧持续的时间是2.5s.透明度和半径都跟slice有关：
```
ripplePaint.alpha = (1 - progress.div(slice * 1.0f)).times(255).toInt()
            val radis = startRadius + (endRadius - startRadius).div(slice).times(progress)
```
随着时间的增长，透明度越低，半径越大。

怎么使用封装的RippleCircle。我们的要求是可以动态添加，并且消失之后需要移除，所以通过ArrayList来作为容器。但这里涉及到对集合的添加和删除操作，如果同时进行会发生异常。解决如下,使用CopyOnWriteArrayList，并且移除通过：
```
post {
                    rippleCircles.remove(this)
                }
```
然后在onDraw中,值得一提的是为了防止被扫描的部分挡住，这里的代码需要写在onDraw方法的后部分。
```
  for (i in 0 until rippleCircles.size) {
            rippleCircles[i].draw(canvas)
        }
```

在startRipple()方法中添加RippleCircle:
```
 rippleCircles.add(RippleCircle().apply {
                cx = width.div(2).toFloat()
                cy = height.div(2).toFloat()
                val maxRadius = Math.min(width, height).div(2).toFloat()
                startRadius = maxRadius.div(3)
                endRadius = maxRadius
            })
```
startRipple也是暴露出去调用添加波纹的方法。点击头像然后添加。涉及到自定义View当然测量是很关键的一部分。不过现在直接使用默认就可以，然后去宽高的最小值，除以2作为半径。在这里为什么startRadius要处以3呢，因为定义该大小作为波纹圆开始的半径。到这里第一步就算完成了。

2. 扫描的效果是关键的部分，而且效率直接影响是否可用。仔细看效果，其实也是一个圆只不过添加了shader。所以重点就是shader的实现。android中默认提供了几种Shader给我们使用。SweepGradient就是我们需要的，扫描渐变。然后选择了之后，就是调整参数了，看一下SweepGradient的用法:
构造函数
```
SweepGradient(float cx, float cy,
            @NonNull @ColorInt int colors[], @Nullable float positions[])
```
重点在于positions 的理解。按照文档解释以及代码。
比如跟colors 的值一一对应，还必须是单调递增的，防止出现严重异常。
positions 对应每一个颜色的位置，当然是再圆的位置。顺时针，0为0°，0.5为180°，1为360°。
如果要像探探一样，最开始是一根线颜色很深。说明第一种颜色很深占比很小，第二种颜色浅占比很大，如下
```
        val colors = intArrayOf(getColor(R.color.pink_fa758a),getColor(R.color.pink_f5b8c2),getColor(R.color.top_background_color),getColor(R.color.white))

SweepGradient(width.div(2).toFloat(), height.div(2).toFloat(), colors, floatArrayOf(0f,0.001f,0.9f,1f))
```
所以设置对了参数，整个扫描渐变的效果就差不多了。然后在对画笔设置shader，在drawCircle。
```
        backPaint.setShader(SweepGradient(width.div(2).toFloat(), height.div(2).toFloat(), colors, floatArrayOf(0f, 0.001f, 0.9f, 1f)))
        canvas.drawCircle(width.div(2).toFloat(), height.div(2).toFloat(), radius, backPaint)

```
当做完上面的操作之后，整个扫面的范围是整个圆，而需要的效果是中间有镂空的校园，这里又涉及到对xfermode的操作了。进行xfermode操作，必须要对canvas设置layer。如果不设置会有问题，镂空的校园是黑色的。详细的解释在我之间的文章中有[高仿QQ 发送图片高亮HaloProgressView](https://www.jianshu.com/p/0254501d744d)一文中做过阐述。setLayer需要设置范围，那么我们的范围就是覆盖整个大圆的矩形
```
        val rectF = RectF(width.div(2f) - radius
                , height.div(2f) - radius
                , width.div(2f) + radius
                , height.div(2f) + radius)
        val sc = canvas.saveLayer(rectF, backPaint, Canvas.ALL_SAVE_FLAG)
```
然后再drawCircle之后在设置xfermode
```
        backPaint.setXfermode(PorterDuffXfermode(PorterDuff.Mode.DST_OUT))

```

这里采取DST_OUT，为什么采用这种模式，在之前文章中可以详细查看[Paint Xfermode 详解](https://www.jianshu.com/p/19997b0b5b24).到这里扫描渐变和镂空都实现了，只差最后一步，转动起来。
转动直接通过canvas的rotate方法是很适合现在的场景。因为整个View都是圆。涉及到canvas操作，需要save,然后再restore
```
 canvas.save()
        canvas.rotate(sweepProgress.toFloat(), width.div(2f), height.div(2f))
        ...
        canvas.restore()
```
可以看到sweepProgress是转动的关键，通过动画控制是很方便的。
```
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
```
可以看到参数设置一秒60次执行。也就是60帧。再通过到了360°，置0即可。到这里已经完成了TanTanRippleView的实现。接着实现头像的动画。在头像的点击事件里面直接添加:
```
 ((TanTanRippleView)findViewById(R.id.ripple)).startRipple();
                AnimatorSet set = new AnimatorSet();
                set.setInterpolator(new BounceInterpolator());
                set.playTogether(
                        ObjectAnimator.ofFloat(v,"scaleX",1.2f,0.8f,1f),
                         ObjectAnimator.ofFloat(v,"scaleY",1.2f,0.8f,1f));
                set.setDuration(1100).start();
```
有兴趣查看源码[我是源码](https://github.com/hewking/TanTanRippleView)，查看更多细节。
