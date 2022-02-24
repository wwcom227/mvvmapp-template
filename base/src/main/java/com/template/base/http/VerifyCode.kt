package com.template.base.http

import com.template.base.BaseAppConfig

/**
 * code校验
 */
internal object VerifyCode {
    private val SUCCESS_CODE = intArrayOf(0, 200)
    private const val LOGOUT_CODE = 403

    fun isSuccess(code: Int): Boolean {
        for (value in SUCCESS_CODE) {
            if (value == code) {
                return true
            }
        }
        if (LOGOUT_CODE == code) {
            BaseAppConfig.callback.logout()
        }
        return false
    }
}