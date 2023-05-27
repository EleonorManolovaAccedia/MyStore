package com.example.mystore.networking

import com.example.mystore.ServiceInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient (private val serviceInterceptor: ServiceInterceptor) {
    private val DEFAULT_TIMEOUT = 1L

    val defaultService: API

    init {
        defaultService = createService()
    }

    private fun createService(): API {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(serviceInterceptor)
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestBuilder = originalRequest.newBuilder()

                chain.proceed(requestBuilder.build())
            }
            .build()

        var client = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://ethereal-artefacts.fly.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return client.create(API::class.java)
    }
}