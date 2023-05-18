package com.example.mystore.di

import com.example.mystore.ServiceInterceptor
import com.example.mystore.TokenManager
import com.example.mystore.repository.ProductsRepository
import com.example.mystore.networking.API
import com.example.mystore.networking.ApiClient
import com.example.mystore.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "https://fakestoreapi.com"

    @Singleton
    @Provides
    fun providersHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
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
    fun provideApiClient(serviceInterceptor: ServiceInterceptor) = ApiClient(serviceInterceptor)

    @Singleton
    @Provides
    fun provideProductsRepository(apiService: API) = ProductsRepository(apiService)

    @Singleton
    @Provides
    fun provideLoginRepository(
        apiService: API,
        apiClient: ApiClient,
        tokenManager: TokenManager
    ) =
        LoginRepository(apiService, apiClient,  tokenManager)
}