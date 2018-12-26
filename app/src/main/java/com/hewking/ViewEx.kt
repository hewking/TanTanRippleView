package com.hewking

import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.view.View

/**
 * 类的描述：
 * 创建人员：hewking
 * 创建时间：2018/12/27
 * 修改人员：hewking
 * 修改时间：2018/12/27
 * 修改备注：
 * Version: 1.0.0
 */

fun View.dp2px(dp : Float) : Int{
    return (context.resources.displayMetrics.density * dp + 0.5).toInt()
}

@ColorInt
fun View.getColor(@ColorRes resid: Int): Int {
    return ContextCompat.getColor(context, resid)
}