package com.template.mvvmapp.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.template.mvvmapp.base.widget.IStatusView
import com.template.mvvmapp.base.widget.LoadingDialog
import java.lang.reflect.ParameterizedType

abstract class BaseMvvmFragment<VM : BaseViewModel> : BaseFragment(), IStatusView {

    protected lateinit var mViewModel: VM
    private lateinit var mLoadingDialog: LoadingDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLoadingDialog = LoadingDialog(requireContext(), false)
        mViewModel = getViewModel()!!
        mViewModel.init(arguments)
        initViews(view)
        registerEvent()
        bindData()
    }

    protected open fun bindData() {

    }

    /**
     * 获取ViewModel 子类可以复写，自行初始化
     * 注：初始化ViewModel，还可以通过activity-ktx、fragment-ktx扩展库
     * 初始化方式：val model: VM by viewModels()
     */
    protected open fun getViewModel(): VM? {
        //当前对象超类的Type
        val type = javaClass.genericSuperclass
        //ParameterizedType表示参数化的类型
        if (type != null && type is ParameterizedType) {
            //返回此类型实际类型参数的Type对象数组
            val actualTypeArguments = type.actualTypeArguments
            val tClass = actualTypeArguments[0]
            return ViewModelProvider(this).get(tClass as Class<VM>)
        }
        return null
    }

    private fun registerEvent() {
        //接收错误信息
        mViewModel.errorLiveData.observe(this) { errMsg ->
            showErrorView(errMsg)
        }
        //接收Loading信息
        mViewModel.loadingLiveData.observe(this, { isShow ->
            showLoadingView(isShow)
        })
    }

    override fun showLoadingView(isShow: Boolean) {
        if (isShow) {
            mLoadingDialog.showDialog()
        } else {
            mLoadingDialog.dismissDialog()
        }
    }

    override fun showEmptyView() {
    }

    override fun showMainView() {

    }

    override fun showErrorView(errMsg: String) {
    }

    abstract fun initViews(view: View)

}