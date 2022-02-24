package com.template.base.util.ktx

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.template.base.BaseAppConfig
import com.template.base.util.ScreenUtil

/**
 * Toast扩展函数
 */
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(BaseAppConfig.appContext, message, duration).show()
}

fun Context.log(message: String) {
    Log.e("TTT", message)
}

fun log(message: String) {
    Log.e("TTT", message)
}

/**
 * Fragment中Toast扩展函数
 */
fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}

fun Number.dp2px(): Int {
    return ScreenUtil.dp2px(BaseAppConfig.appContext, toFloat())
}

fun Number.px2dp(): Int {
    return ScreenUtil.px2dp(BaseAppConfig.appContext, toFloat())
}

fun Number.sp2px(): Int {
    return ScreenUtil.sp2px(BaseAppConfig.appContext, toFloat())
}

fun Number.px2sp(): Int {
    return ScreenUtil.px2sp(BaseAppConfig.appContext, toFloat())
}



