package com.template.mvvmapp.app;

import android.app.Application;

import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.template.mvvmapp.base.http.RetrofitUtil;
import com.template.mvvmapp.lifecycle.MyApplicationLifecycleObserver;


public class MyApplication extends Application {

    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        MultiDex.install(this);
        //初始化Retrofit
        RetrofitUtil.INSTANCE.initRetrofit();
        //初始化Fresco
        Fresco.initialize(this);
        //基于Lifecycle，监听Application的生命周期
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new MyApplicationLifecycleObserver());
    }

    public static MyApplication getApplication() {
        return application;
    }
}
