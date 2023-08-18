package com.example.data.state

sealed class UiState<out T> {
  data class Success<T>(val data: T) : UiState<T>()
  data class Error(val errorMessage: String) : UiState<Nothing>()
  object Loading : UiState<Nothing>()
}