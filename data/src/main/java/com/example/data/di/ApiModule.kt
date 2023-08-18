package com.example.data.di

import com.example.data.network.DevicesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

  private val baseUrl = "https://veramobile.mios.com/"

  @Provides
  @Singleton
  fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
    return OkHttpClient
      .Builder()
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(baseUrl)
      .build()
  }

  @Provides
  @Singleton
  fun provideDevicesService(retrofit: Retrofit): DevicesService {
    return retrofit.create(DevicesService::class.java)
  }
}