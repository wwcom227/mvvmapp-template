package com.template.mvvmapp.data.model

import java.io.Serializable

interface IUser : Serializable {
    var token: String?
    var phone: String?
}