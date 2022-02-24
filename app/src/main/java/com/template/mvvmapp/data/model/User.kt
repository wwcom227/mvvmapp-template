package com.template.mvvmapp.data.model

data class User(
    override var token: String? = null,
    override var phone: String? = null
) : IUser