package com.example.data.domain.entity

import com.google.gson.annotations.SerializedName

data class DevicesDto(
  @SerializedName("Devices") val devices: List<DeviceDto>
)