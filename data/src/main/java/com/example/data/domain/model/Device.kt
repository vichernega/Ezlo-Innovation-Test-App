package com.example.data.domain.model

data class Device(
  val pkDevice: Int,
  val macAddress: String,
  val pkDeviceType: Int,
  val pkDeviceSubType: Int,
  val pkAccount: Int?,
  val firmware: String,
  val serverDevice: String,
  val serverEvent: String,
  val serverAccount: String,
  val internalIP: String,
  val lastAliveReported: String,
  val platform: String
)
