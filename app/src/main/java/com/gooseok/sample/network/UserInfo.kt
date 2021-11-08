package com.gooseok.sample.network

import com.google.gson.annotations.SerializedName

data class UserInfo(
    val id : Long,
    @SerializedName("login")
    val userId : String,
    val followers : Int,
    val following : Int,
    @SerializedName("name")
    val userName : String
)
