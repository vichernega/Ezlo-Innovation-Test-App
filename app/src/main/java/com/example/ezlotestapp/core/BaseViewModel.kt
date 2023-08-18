package com.example.ezlotestapp.core

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.state.RequestState
import com.example.data.state.UiState
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel() {
  protected fun <T> launchRequest(
    result: MutableLiveData<UiState<T>>? = null,
    context: CoroutineContext = Dispatchers.IO,
    scope: CoroutineScope = viewModelScope,
    request: suspend CoroutineScope.() -> RequestState<T>
  ): Job {
    return scope.launch {
      result?.postValue(UiState.Loading)
      withContext(context) { request() }.apply {
        when (this) {
          is RequestState.Success -> {
            result?.postValue(UiState.Success(this.response))
          }

          is RequestState.Error -> {
            result?.postValue(
              UiState.Error(this.throwable.message ?: "Unexpected error")
            )
          }

          is RequestState.NetworkError -> {
            result?.postValue(
              UiState.Error("Network Error")
            )
          }
        }
      }
    }
  }
}