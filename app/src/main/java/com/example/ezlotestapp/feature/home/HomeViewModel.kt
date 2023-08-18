package com.example.ezlotestapp.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.domain.model.Device
import com.example.data.repositories.DevicesRepository
import com.example.data.state.UiState
import com.example.ezlotestapp.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: DevicesRepository) :
  BaseViewModel() {

  private val _devicesLiveData: MutableLiveData<UiState<List<Device>>> = MutableLiveData()
  val devicesLiveData: LiveData<UiState<List<Device>>> = _devicesLiveData

  fun getDevices() {
    launchRequest(_devicesLiveData) { repository.retrieveCachedDevices() }
  }

  fun resetDevices() {
    launchRequest(_devicesLiveData) { repository.resetData() }
  }

}