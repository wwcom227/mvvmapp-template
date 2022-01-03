package com.template.mvvmapp.base

import com.google.gson.annotations.SerializedName

class BaseData<T> {
    @SerializedName("code")
    var code = -1
    @SerializedName("message")
    var msg: String? = null
    var data: T? = null
    var state: State = State.Error
}

enum class State {
    Success, Error
}
