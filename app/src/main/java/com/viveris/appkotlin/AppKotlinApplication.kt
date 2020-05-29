package com.viveris.appkotlin

import android.app.Application

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AppKotlinApplication : Application() {

    companion object {
        const val BASE_URL = "https://api.stackexchange.com/2.2/"
    }

    private var gson: Gson? = null
    var retrofit: Retrofit? = null

    override fun onCreate() {
        super.onCreate()
        if (gson == null) {
            initializeGson()
        }
        if (retrofit == null) {
            initializeRetrofit()
        }
    }

    private fun initializeGson() {
        val gsonBuilder = GsonBuilder()
        gson = gsonBuilder.create()
    }

    private fun initilializeOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private fun initializeRetrofit() {
        retrofit = Retrofit.Builder()
                .client(initilializeOkHttpClient())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }
}
