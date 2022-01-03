package com.template.mvvmapp.client.ui

import android.view.View
import com.jeremyliao.liveeventbus.LiveEventBus
import com.template.mvvmapp.R
import com.template.mvvmapp.base.BaseMvvmFragment
import com.template.mvvmapp.client.viewmodel.OneViewModel
import com.template.mvvmapp.event.ReplaceFragmentEvent
import kotlinx.android.synthetic.main.fragment_one.*


/**
 * one fragment
 */
class OneFragment : BaseMvvmFragment<OneViewModel>() {

    override fun getLayoutId() = R.layout.fragment_one


    override fun initViews(view: View) {
        btn_next.setOnClickListener {
            LiveEventBus
                .get(ReplaceFragmentEvent::class.java)
                .post(ReplaceFragmentEvent(1))
        }
    }

}