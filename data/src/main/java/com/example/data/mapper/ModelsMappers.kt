package com.example.data.mapper

import com.example.data.domain.entity.DeviceDto
import com.example.data.domain.entity.DevicesDto
import com.example.data.domain.model.Device
import com.example.data.domain.model.Devices

fun DeviceDto.toDevice() = Device(
  pkDevice,
  macAddress,
  pkDeviceType,
  pkDeviceSubType,
  pkAccount,
  firmware,
  serverDevice,
  serverEvent,
  serverAccount,
  internalIP,
  lastAliveReported,
  platform
)

fun DevicesDto.toDevices(): Devices {
  val mappedList = mutableListOf<Device>()
  devices.forEach {
    mappedList.add(it.toDevice())
  }
  return Devices(devices = mappedList)
}