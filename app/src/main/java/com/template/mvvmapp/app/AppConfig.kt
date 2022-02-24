package com.template.mvvmapp.app

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.text.TextUtils
import com.template.base.BaseAppConfig
import com.template.base.BaseCallback
import com.template.base.util.GsonUtils
import com.template.base.util.SPUtils
import com.template.mvvmapp.data.model.IUser
import com.template.mvvmapp.data.model.User

/**
 * 全局变量
 */
object AppConfig {

    lateinit var appContext: Context

    fun init(context: Context) {
        BaseAppConfig.appContext = ContextWrapper(context.applicationContext)
        BaseAppConfig.callback = object : BaseCallback {
            override fun logout() {
                logout()
            }
        }
        appContext = BaseAppConfig.appContext
    }

    /**
     * 用户是否已登陆
     */
    val isLogin: Boolean
        get() = user != null && !TextUtils.isEmpty(token)

    /**
     * token
     */
    val token: String?
        get() {
            return if (user != null) user?.token else ""
        }

    /**
     * 退出登录
     */
    fun logout() {
        //清除本地缓存的用户数据
        if (user != null) {
            val phone = user
            user = null
        }

        SPUtils.put(
            appContext,
            Constants.SP_NAME_USER,
            GsonUtils.instance.toJson(user)
        )

        val intent = Intent()
        intent.action = "android.intent.action.com.luca.kekeapp.Login"
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        appContext.startActivity(intent)
    }

    /**
     * 用户信息
     */
    var user: IUser? = null
        get() {
            if (field == null) {
                val json = SPUtils[appContext, Constants.SP_NAME_USER, ""] as String?
                field = if (json != null && json != "") {
                    GsonUtils.instance.fromJson(json, IUser::class.java)
                } else {
                    User()
                }
            }
            return field
        }
        set(value) {
            field = value
            var json = ""
            if (field != null) {
                json = GsonUtils.instance.toJson(value)
            }
            SPUtils.put(appContext, Constants.SP_NAME_USER, json)
        }

    /**
     * 是否是首次启动
     */
    var isFirstLaunch: Boolean? = null
        get() {
            if (field == null) {
                val json =
                    SPUtils[appContext, Constants.SP_NAME_IS_FIRST_LAUNCH, true] as Boolean?
                field = json ?: true
            }
            return field
        }
        set(value) {
            field = value
            val json = value ?: true
            SPUtils.put(appContext, Constants.SP_NAME_IS_FIRST_LAUNCH, json)
        }

    /**
     * 是否已同意用户协议
     */
    var isAgreeUserProtocol: Boolean? = null
        get() {
            if (field == null) {
                val json =
                    SPUtils[appContext, Constants.SP_NAME_AGREE_USER_PROTOCOL, false] as Boolean?
                field = json ?: false
            }
            return field
        }
        set(value) {
            field = value
            val json = value ?: false
            SPUtils.put(appContext, Constants.SP_NAME_AGREE_USER_PROTOCOL, json)
        }
}