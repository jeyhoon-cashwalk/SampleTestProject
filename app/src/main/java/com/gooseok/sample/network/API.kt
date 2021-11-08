package com.gooseok.sample.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("users/{userId}")
    fun getUserInfo(@Path("userId") userId : String): Call<GitUserInfo>
}