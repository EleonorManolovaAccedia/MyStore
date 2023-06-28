package com.example.mystore.di

import android.content.Context
import com.example.mystore.TokenManager
import com.example.mystore.networking.API
import com.example.mystore.repository.ApiRepository
import com.example.mystore.repository.DataStoreRepository
import com.example.mystore.repository.IApiRepository
import com.example.mystore.repository.IDataStoreRepository
import com.example.mystore.util.Constants.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private val DEFAULT_TIMEOUT = 1L

    @Singleton
    @Provides
    fun providersHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        serviceInterceptor: ServiceInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(serviceInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): API = retrofit.create(API::class.java)


    @Provides
    @Singleton
    fun provideApiRepository(apiService: API): IApiRepository = ApiRepository(apiService)

    @Provides
    @Singleton
    fun provideTokenManager(): TokenManager = TokenManager()

    @Singleton
    @Provides
    fun provideServiceInterceptor(tokenManager: TokenManager) = ServiceInterceptor(tokenManager)

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context,
        gson: Gson
    ): IDataStoreRepository = DataStoreRepository(context, gson)

}