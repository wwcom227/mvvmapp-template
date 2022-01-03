package com.template.mvvmapp.client.viewmodel

import com.template.mvvmapp.base.BaseViewModel
import com.template.mvvmapp.client.repo.ClientRepo

/**
 * Two VM
 */
class TwoViewModel : BaseViewModel() {

    //Repository中间层 管理所有数据来源 包括本地的及网络的
    private val repo = ClientRepo()


    init {
    }

}