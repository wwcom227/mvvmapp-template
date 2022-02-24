package com.template.base

import com.template.base.http.VerifyCode

open class BaseRepository {
    suspend fun <T : Any> executeRequest(
        block: suspend () -> BaseData<T>,
    ): BaseData<T> {
        val baseData = block.invoke()
        if (VerifyCode.isSuccess(baseData.code)) {
            //正确
            baseData.state = State.Success
        } else {
            //错误
            baseData.state = State.Error
        }
        return baseData
    }

    suspend fun <T : Any> executeRequestList(
        block: suspend () -> BaseDataList<T>,
    ): BaseDataList<T> {
        val baseData = block.invoke()
        if (VerifyCode.isSuccess(baseData.code)) {
            //正确
            baseData.state = State.Success
        } else {
            //错误
            baseData.state = State.Error
        }
        return baseData
    }
}