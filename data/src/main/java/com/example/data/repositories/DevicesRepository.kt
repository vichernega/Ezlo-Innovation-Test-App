package com.example.data.repositories

import com.example.data.core.BaseRepository
import com.example.data.db.DevicesDao
import com.example.data.domain.model.Device
import com.example.data.mapper.mapDevicesList
import com.example.data.network.DevicesService
import com.example.data.state.RequestState
import javax.inject.Inject

class DevicesRepository @Inject constructor(
  private val devicesService: DevicesService,
  private val devicesDao: DevicesDao
) : BaseRepository() {

  suspend fun retrieveCachedDevices() = handleRequest { devicesDao.getDevicesList() }

  suspend fun resetData(): RequestState<List<Device>> {
    val requestResult = getRemoteDevices()
    if (requestResult is RequestState.Success) {
      devicesDao.clearTable()
      devicesDao.insertDevicesList(requestResult.response)
    }
    return retrieveCachedDevices()
  }

  suspend fun updateDevice(device: Device) = handleRequest { devicesDao.insertDevice(device) }

  private suspend fun getRemoteDevices(): RequestState<List<Device>> {
    return handleRequest {
      devicesService.getDevices().mapDevicesList()
    }
  }

  suspend fun deleteDevice(device: Device) {
    devicesDao.removeDevice(device)
  }
}