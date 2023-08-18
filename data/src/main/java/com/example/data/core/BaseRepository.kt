package com.example.data.core

import com.example.data.state.RequestState
import com.example.data.util.log
import java.io.IOException

open class BaseRepository {

  protected suspend fun <T> handleRequest(request: suspend () -> T): RequestState<T> {
    return try {
      val result = request.invoke()
      RequestState.Success(result)
    } catch (t: Throwable) {
      log("DevicesRepository getDevices() throwable.cause: ${t.cause}")
      log("DevicesRepository getDevices() throwable.message: ${t.message}")
      when (t) {
        is IOException -> RequestState.NetworkError
        else -> RequestState.Error(t)
      }
    }
  }
}