package com.template.mvvmapp.app

import androidx.annotation.IntDef

object Constants {

    const val LIFE_TAG: String = "SERVICE"
    const val LIFE_APPLICATION_TAG: String = "Lifecycle_App"
    const val ACTIVITY: String = "ACTIVITY"
    const val SERVICE: String = "SERVICE"
    const val FRAGMENT: String = "FRAGMENT"
    const val LIVE_DATA: String = "LIVEDATA"
    const val VIEW_MODEL: String = "VIEW_MODEL"

    const val FRAGMENT_ONE = 0
    const val FRAGMENT_TWO = 1
    const val FRAGMENT_THREE = 2


    const val StateEmpty = 0
    const val StateError = -1

    //指定注解的保留策略，AnnotationRetention.SOURCE表示只保留源码中，编译时删除。还有CLASS和RUNTIME
    @Retention(AnnotationRetention.SOURCE)
    //定义int值
    @IntDef(value = [StateEmpty, StateError])
    //定义注解类型
    annotation class State
}