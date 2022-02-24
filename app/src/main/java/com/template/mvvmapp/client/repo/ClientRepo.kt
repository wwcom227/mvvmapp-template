package com.template.mvvmapp.client.repo

import com.template.base.http.RetrofitUtil
import com.template.mvvmapp.data.api.ApiService

class ClientRepo : com.template.base.BaseRepository() {
    private val service = RetrofitUtil.getService(ApiService::class.java)

}