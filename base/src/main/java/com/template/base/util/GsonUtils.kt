package com.template.base.util

import com.google.gson.Gson
import java.lang.reflect.Type

class GsonUtils private constructor() {
    private val gson: Gson = Gson()
    fun toJson(`object`: Any?): String {
        return gson.toJson(`object`)
    }

    /**
     * @param json
     * @param classOfT
     * @return
     */
    fun <T> fromJson(json: String?, classOfT: Class<T>?): T {
        return gson.fromJson(json, classOfT)
    }

    fun <T> fromJson(json: String?, t: Type?): T {
        return gson.fromJson(json, t)
    }

    companion object {
        var instance: GsonUtils = GsonUtils()
            private set
    }

}