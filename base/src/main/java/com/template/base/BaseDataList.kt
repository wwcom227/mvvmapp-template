package com.template.base

import com.google.gson.annotations.SerializedName

class BaseDataList<T> {
    @SerializedName("code")
    var code = -1

    @SerializedName("status")
    var status: String? = null

    @SerializedName("message")
    var msg: String? = null

    @SerializedName("result")
    var data: BaseDataPager<T>? = null

    var state: State = State.Error
}
