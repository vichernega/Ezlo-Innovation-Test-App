package com.example.data.db

import androidx.room.*
import com.example.data.domain.model.Device
import com.example.data.util.TABLE_DEVICES

@Dao
interface DevicesDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertDevice(device: Device)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertDevicesList(devices: List<Device>)

  @Delete
  suspend fun removeDevice(device: Device)

  @Query("SELECT * FROM $TABLE_DEVICES")
  suspend fun getDevicesList(): List<Device>

  @Query("DELETE FROM $TABLE_DEVICES")
  suspend fun clearTable()
}