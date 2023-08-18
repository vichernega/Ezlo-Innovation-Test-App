package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.domain.model.Device

@Database(entities = [Device::class], version = 1)
abstract class DevicesDatabase : RoomDatabase() {
  abstract fun devicesDao(): DevicesDao
}