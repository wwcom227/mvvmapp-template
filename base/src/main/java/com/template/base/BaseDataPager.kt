package com.template.base

import com.google.gson.annotations.SerializedName

class BaseDataPager<T> {
    @SerializedName("have_next")
    var haveNext: Boolean = false

    @SerializedName("records")
    var records: T? = null
}