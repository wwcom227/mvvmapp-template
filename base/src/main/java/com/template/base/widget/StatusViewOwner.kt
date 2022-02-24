package com.template.base.widget

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.template.base.R
import com.template.base.util.ktx.showToast

/**
 * 加载中、空视图、错误视图集合
 */
class StatusViewOwner(
    var context: Activity,
    private var mMainView: View? = null,
    private var mOnclickListener: View.OnClickListener? = null
) : IStatusView {

    private var mLoadingDialog: LoadingDialog
    private var mLastShowView: View? = null
    private var mChildViewIndex = 0

    init {
        if (mMainView == null) {
            mMainView = context.findViewById(android.R.id.content)
        }
        mLoadingDialog = LoadingDialog(context, false)
        mChildViewIndex = mMainView?.let { getParentView(it)?.indexOfChild(it) } ?: 0
    }

    /**
     * 展示主视图
     */
    override fun showMainView() {
        if (mLastShowView != null) {
            removeView(mLastShowView)
            mLastShowView = null
        }
        mMainView?.visibility = View.VISIBLE
    }

    /**
     * 空视图
     */
    override fun showEmptyView() {
        val emptyView =
            LayoutInflater.from(context).inflate(R.layout.lib_dialog_default_empty, null)
        if (mOnclickListener != null) {
            emptyView.setOnClickListener(mOnclickListener)
        }
        showCustomView(emptyView)
    }

    /**
     * 错误视图
     */
    override fun showErrorView(errMsg: String) {
        showToast(errMsg)
        val errView = LayoutInflater.from(context).inflate(R.layout.lib_dialog_default_error, null)
        if (mOnclickListener != null) {
            errView.setOnClickListener(mOnclickListener)
        }
        showCustomView(errView)
    }

    /**
     * 加载中
     */
    override fun showLoadingView(isShow: Boolean) {
        if (isShow) {
            mLoadingDialog.showDialog()
        } else {
            mLoadingDialog.dismissDialog()
        }
    }

    fun showCustomView(incomeView: View) {
        if (incomeView == mLastShowView) return
        if (mLastShowView != null && mLastShowView != mMainView) {
            removeView(mLastShowView)
            mLastShowView = null
        }
        if (incomeView != mMainView) {
            mMainView?.visibility = View.GONE
            val parentView = mMainView?.let { getParentView(it) }
            if (parentView != null) {
                checkChildView(incomeView)
                parentView.addView(incomeView, mChildViewIndex, mMainView?.layoutParams)
            }
        } else {
            mMainView?.visibility = View.VISIBLE
        }
        mLastShowView = incomeView
    }


    /**
     * 如果当前子View有父View，对其进行解绑
     */
    private fun checkChildView(childView: View) {
        if (childView.parent != null) removeView(childView)
    }

    /**
     * 获取父View
     */
    private fun getParentView(childView: View): ViewGroup? {
        return if (childView.parent != null) childView.parent as ViewGroup else null
    }

    /**
     * 从父View删除当前子View
     */
    private fun removeView(childView: View?) {
        if (childView != null) {
            val viewParent = childView.parent
            if (viewParent != null && viewParent is ViewGroup) {
                viewParent.removeView(childView)
            }
        }
    }

}