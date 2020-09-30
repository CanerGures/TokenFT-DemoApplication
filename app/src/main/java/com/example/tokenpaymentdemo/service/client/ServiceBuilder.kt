package com.example.tokenpaymentdemo.service.client

import com.example.tokenpaymentdemo.service.util.getUnsafeOkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://sandbox-api.payosy.com/"

object WebClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getUnsafeOkHttpClient())
        .build()

    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }
}

