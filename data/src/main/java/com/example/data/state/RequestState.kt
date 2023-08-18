package com.example.data.state

sealed class RequestState<out T> {
  class Success<out T>(val response: T) : RequestState<T>()
  class Error(val throwable: Throwable) : RequestState<Nothing>()
  object NetworkError : RequestState<Nothing>()
}
