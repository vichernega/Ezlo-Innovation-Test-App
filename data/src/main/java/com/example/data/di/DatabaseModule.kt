package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.db.DevicesDao
import com.example.data.db.DevicesDatabase
import com.example.data.util.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

  @Provides
  @Singleton
  fun provideDevicesDao(devicesDatabase: DevicesDatabase): DevicesDao = devicesDatabase.devicesDao()

  @Provides
  @Singleton
  fun provideDevicesDatabase(@ApplicationContext context: Context): DevicesDatabase {
    return Room.databaseBuilder(
      context = context.applicationContext,
      klass = DevicesDatabase::class.java,
      name = DB_NAME
    ).build()
  }
}