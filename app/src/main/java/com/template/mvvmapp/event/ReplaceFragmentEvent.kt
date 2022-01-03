package com.template.mvvmapp.event

import com.jeremyliao.liveeventbus.core.LiveEvent

/**
 * 切换fragment事件
 */
data class ReplaceFragmentEvent(
    /** 切换的位置 */
    var index: Int = 0,
    /** 是否需要回退 */
    var needPop: Boolean = false,
) : LiveEvent {

}