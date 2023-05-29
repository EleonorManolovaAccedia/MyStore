package com.example.mystore.di

import com.example.mystore.TokenManager
import com.example.mystore.repository.ProductsRepositoryImpl
import com.example.mystore.networking.API
import com.example.mystore.repository.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    private const val BASE_URL = "https://ethereal-artefacts.fly.dev/api/"

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
    fun provideTokenManager(): TokenManager {
        return TokenManager()
    }

    @Singleton
    @Provides
    fun provideServiceInterceptor(tokenManager: TokenManager) = ServiceInterceptor(tokenManager)


    @Singleton
    @Provides
    fun provideProductsRepository(apiService: API) = ProductsRepositoryImpl(apiService)

    @Singleton
    @Provides
    fun provideLoginRepository(apiService: API) = LoginRepositoryImpl(apiService)
}