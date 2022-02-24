package com.template.mvvmapp.app

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDex
import com.facebook.drawee.backends.pipeline.Fresco
import com.template.base.http.RetrofitUtil.initRetrofit
import com.template.base.lifecycle.MyApplicationLifecycleObserver
import com.template.mvvmapp.app.AppConfig.init

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init(this)
        MultiDex.install(this)
        //初始化Retrofit
        initRetrofit()
        //初始化Fresco
        Fresco.initialize(this)
        //基于Lifecycle，监听Application的生命周期
        ProcessLifecycleOwner.get().lifecycle.addObserver(MyApplicationLifecycleObserver())
    }
}