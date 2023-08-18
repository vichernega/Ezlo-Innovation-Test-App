package com.example.ezlotestapp.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.domain.model.Devices
import com.example.data.repositories.DevicesRepository
import com.example.data.state.RequestState
import com.example.data.util.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: DevicesRepository) : ViewModel() {

  private val _devicesLiveData: MutableLiveData<Devices> = MutableLiveData()
  val devicesLiveData: LiveData<Devices> = _devicesLiveData

  fun getDevices() {
    viewModelScope.launch {
      when (val requestState = repository.getDevices()) {
        is RequestState.Success -> log("SUCCESS ---- ${requestState.response}")
        is RequestState.NetworkError -> log("NETWORK ERROR")
        is RequestState.Error -> log("ERROR")
      }
    }
  }

}