package com.example.data.domain.entity

import com.google.gson.annotations.SerializedName

data class DeviceDto(
  @SerializedName("PK_Device") val pkDevice: Int,
  @SerializedName("MacAddress") val macAddress: String,
  @SerializedName("PK_DeviceType") val pkDeviceType: Int,
  @SerializedName("PK_DeviceSubType") val pkDeviceSubType: Int,
  @SerializedName("PK_Account") val pkAccount: Int?,
  @SerializedName("Firmware") val firmware: String,
  @SerializedName("Server_Device") val serverDevice: String,
  @SerializedName("Server_Event") val serverEvent: String,
  @SerializedName("Server_Account") val serverAccount: String,
  @SerializedName("InternalIP") val internalIP: String,
  @SerializedName("LastAliveReported") val lastAliveReported: String,
  @SerializedName("Platform") val platform: String
)