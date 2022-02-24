package com.template.base

import android.content.Context

object BaseAppConfig {

    lateinit var appContext: Context

    lateinit var callback: BaseCallback

}

interface BaseCallback {
    fun logout() {}
}