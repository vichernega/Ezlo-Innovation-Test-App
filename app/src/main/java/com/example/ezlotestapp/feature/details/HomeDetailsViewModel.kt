package com.example.ezlotestapp.feature.details

import com.example.data.domain.model.Device
import com.example.data.repositories.DevicesRepository
import com.example.ezlotestapp.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeDetailsViewModel @Inject constructor(private val repository: DevicesRepository) :
  BaseViewModel() {

    fun updateTitle(device: Device, title: String) {
      launchRequest {
        repository.updateDevice(device.copy(name = title))
      }
    }
}