package com.example.data.repositories

import com.example.data.domain.model.Devices
import com.example.data.mapper.toDevices
import com.example.data.network.DevicesService
import com.example.data.state.RequestState
import com.example.data.util.log
import java.io.IOException
import javax.inject.Inject

class DevicesRepository @Inject constructor(private val devicesService: DevicesService) {

  suspend fun getDevices(): RequestState<Devices> {
    return try {
      val result = devicesService.getDevices().toDevices()
      RequestState.Success(result)
    } catch (throwable: Throwable) {
      log("DevicesRepository getDevices() throwable.cause: ${throwable.cause}")
      log("DevicesRepository getDevices() throwable.message: ${throwable.message}")
      when (throwable) {
        is IOException -> RequestState.NetworkError
        else -> RequestState.Error(throwable)
      }
    }
  }
}