package com.template.mvvmapp.client.repo

import com.template.mvvmapp.base.BaseData
import com.template.mvvmapp.base.BaseRepository
import com.template.mvvmapp.base.http.RetrofitUtil
import com.template.mvvmapp.base.http.api.ApiService
import com.template.mvvmapp.base.http.data.Model

class ClientRepo : BaseRepository() {
    private val service = RetrofitUtil.getService(ApiService::class.java)

}