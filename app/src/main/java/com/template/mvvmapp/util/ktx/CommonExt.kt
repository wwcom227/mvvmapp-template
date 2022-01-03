package com.template.mvvmapp.util.ktx

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.template.mvvmapp.app.MyApplication
import com.template.mvvmapp.util.ScreenUtil

/**
 * Toast扩展函数
 */
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.getApplication(), message, duration).show()
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
    return ScreenUtil.dp2px(MyApplication.getApplication(), toFloat())
}

fun Number.px2dp(): Int {
    return ScreenUtil.px2dp(MyApplication.getApplication(), toFloat())
}

fun Number.sp2px(): Int {
    return ScreenUtil.sp2px(MyApplication.getApplication(), toFloat())
}

fun Number.px2sp(): Int {
    return ScreenUtil.px2sp(MyApplication.getApplication(), toFloat())
}



