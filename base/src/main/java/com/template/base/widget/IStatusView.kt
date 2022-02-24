package com.template.base.widget

interface IStatusView {
    fun showMainView() //正常视图
    fun showEmptyView() //空视图
    fun showErrorView(errMsg: String) //数据错误视图
    fun showLoadingView(isShow: Boolean) //展示Loading视图
}