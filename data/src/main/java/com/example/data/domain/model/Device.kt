package com.example.data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.util.TABLE_DEVICES

@Entity(tableName = TABLE_DEVICES)
data class Device(
  @PrimaryKey val pkDevice: Int,
  val name: String,
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
