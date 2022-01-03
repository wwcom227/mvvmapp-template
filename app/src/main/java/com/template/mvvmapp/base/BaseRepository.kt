package com.template.mvvmapp.base

open class BaseRepository {
    suspend fun <T : Any> executeRequest(
        block: suspend () -> BaseData<T>,
    ): BaseData<T> {
        val baseData = block.invoke()
        if (baseData.code == 200) {
            //正确
            baseData.state = State.Success
        } else {
            //错误
            baseData.state = State.Error
        }
        return baseData
    }
}