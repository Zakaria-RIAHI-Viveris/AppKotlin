package com.viveris.appkotlin.network

import com.viveris.appkotlin.model.Users

import io.reactivex.Single
import retrofit2.http.GET

interface RequestInterface {

    @GET("users?order=desc&sort=reputation&site=stackoverflow")
    fun fetchUsers(): Single<Users>
}
