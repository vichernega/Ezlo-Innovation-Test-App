package com.example.data.mapper

import com.example.data.domain.entity.DeviceDto
import com.example.data.domain.entity.DevicesDto
import com.example.data.domain.model.Device
import com.example.data.util.DEVICE_NAME_PREFIX

fun DeviceDto.toDevice(position: Int) = Device(
  pkDevice,
  name = "$DEVICE_NAME_PREFIX $position",
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

fun DevicesDto.mapDevicesList(): List<Device> {
  val mappedList = mutableListOf<Device>()
  val sortedList = devices.sortedWith(compareBy { it.pkDevice })
  sortedList.forEachIndexed { index, deviceDto ->
    mappedList.add(deviceDto.toDevice(index + 1))
  }
  return mappedList
}