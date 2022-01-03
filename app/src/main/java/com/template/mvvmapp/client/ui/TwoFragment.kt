package com.template.mvvmapp.client.ui

import android.view.View
import com.jeremyliao.liveeventbus.LiveEventBus
import com.template.mvvmapp.R
import com.template.mvvmapp.base.BaseMvvmFragment
import com.template.mvvmapp.client.viewmodel.TwoViewModel
import com.template.mvvmapp.event.ReplaceFragmentEvent
import kotlinx.android.synthetic.main.fragment_one.btn_next
import kotlinx.android.synthetic.main.fragment_two.*


/**
 * two fragment
 */
class TwoFragment : BaseMvvmFragment<TwoViewModel>() {
    override fun getLayoutId() = R.layout.fragment_two


    override fun initViews(view: View) {
        btn_back.setOnClickListener {
            LiveEventBus
                .get(ReplaceFragmentEvent::class.java)
                .post(ReplaceFragmentEvent(0,true))
        }
        btn_next.setOnClickListener {
            LiveEventBus
                .get(ReplaceFragmentEvent::class.java)
                .post(ReplaceFragmentEvent(2))
        }
    }
}