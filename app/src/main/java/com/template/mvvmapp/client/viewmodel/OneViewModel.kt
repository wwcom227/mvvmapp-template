package com.template.mvvmapp.client.viewmodel

import com.template.base.BaseViewModel
import com.template.mvvmapp.client.repo.ClientRepo

/**
 * One VM
 */
class OneViewModel : BaseViewModel() {

    //Repository中间层 管理所有数据来源 包括本地的及网络的
    private val repo = ClientRepo()

    init {
    }

}