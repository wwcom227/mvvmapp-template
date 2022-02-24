package com.template.mvvmapp.app

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.template.mvvmapp.client.ui.FragmentMainActivity

object NavigationUtil {

    /**
     * 跳转到网页
     */
    fun gotoWebView(context: Context, url: String, title: String?) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("template://web?url=${url}&title=${title}&title_sticky=true")
        )
        context.startActivity(intent)
    }

    /**
     * 跳转到首页
     */
    fun gotoHome(context: Context) {
        val intent = Intent(context, FragmentMainActivity::class.java)
        context.startActivity(intent)
    }

    /**
     * 跳转到应用设置界面
     */
    fun gotoApplicationSettings(context: Context) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = Uri.parse("package:" + context.packageName)
        context.startActivity(intent)
    }
}