package com.example.data.network

import com.example.data.domain.entity.DevicesDto
import retrofit2.http.GET

interface DevicesService {

  @GET("test_android/items.test")
  suspend fun getDevices(): DevicesDto
}