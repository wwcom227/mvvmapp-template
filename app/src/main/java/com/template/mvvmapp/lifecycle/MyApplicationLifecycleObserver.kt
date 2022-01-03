package com.template.mvvmapp.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.template.mvvmapp.app.Constants
import com.template.mvvmapp.util.ktx.showToast

/**
 * 监听Application的生命周期，可以用来判断应用前后台判断
 */
class MyApplicationLifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onAppForeground() {
        Log.e(Constants.LIFE_APPLICATION_TAG, "onAppForeground")
        showToast("应用处于前台")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_STOP)
    fun onAppBackground() {
        Log.e(Constants.LIFE_APPLICATION_TAG, "onAppBackground")
        showToast("应用处于后台")
    }
}