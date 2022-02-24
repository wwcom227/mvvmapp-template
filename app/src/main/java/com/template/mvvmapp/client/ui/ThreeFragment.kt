package com.template.mvvmapp.client.ui

import android.view.View
import com.jeremyliao.liveeventbus.LiveEventBus
import com.template.mvvmapp.R
import com.template.base.BaseMvvmFragment
import com.template.mvvmapp.client.viewmodel.ThreeViewModel
import com.template.mvvmapp.event.ReplaceFragmentEvent
import kotlinx.android.synthetic.main.fragment_three.*

/**
 * three fragment
 */
class ThreeFragment : BaseMvvmFragment<ThreeViewModel>() {
    override fun getLayoutId() = R.layout.fragment_three


    override fun initViews(view: View) {
        btn_back.setOnClickListener {
            LiveEventBus
                .get(ReplaceFragmentEvent::class.java)
                .post(ReplaceFragmentEvent(1,true))
        }
    }

}