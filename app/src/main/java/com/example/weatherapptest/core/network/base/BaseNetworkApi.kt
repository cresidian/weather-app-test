package com.example.weatherapptest.core.network.base

import com.example.weatherapptest.core.network.NetworkResponse
import com.example.weatherapptest.domain.model.WeatherAppBackendError
import com.example.weatherapptest.domain.model.responses.*
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.net.SocketTimeoutException


abstract class BaseNetworkApi {

    protected suspend inline fun <T> executeApiCall(crossinline requestFunction: suspend () -> T): NetworkResponse<T> {
        return withContext(Dispatchers.IO) {
             try {
                val response = requestFunction.invoke()
                var error: WeatherAppBackendError? = null

                if (error == null) {
                    NetworkResponse.Success(response)
                } else {
                    NetworkResponse.Error(error)
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                handleException(exception)
            }
        }
    }

    fun handleException(exception: Exception): NetworkResponse.Error {
        if (exception is SocketTimeoutException) {
            return NetworkResponse.Error(
                WeatherAppBackendError(
                    message = CONNECTION_TIMED_OUT_MESSAGE,
                    errorCode = UNDEFINED_ERROR_CODE
                )
            )
        }
        if (exception is HttpException) {
            val errorCode = exception.code()
            val errorMessage = exception.message().toString()
            val error = when (errorCode) {
                in 400..499 -> {
                    return getParsedErrorResponse(exception.response()?.errorBody())
                }
                in 500..599 -> {
                    "$GENERAL_SERVER_ERROR_MESSAGE - $errorCode"
                }
                in 300..399 -> {
                    errorMessage
                }
                else -> {
                    GENERAL_SERVER_ERROR_MESSAGE
                }
            }
            return NetworkResponse.Error(
                WeatherAppBackendError(
                    message = error,
                    errorCode = errorCode
                )
            )
        }
        return NetworkResponse.Error(
            WeatherAppBackendError(
                message = GENERAL_SERVER_ERROR_MESSAGE,
                errorCode = UNDEFINED_ERROR_CODE
            )
        )
    }

    private fun getParsedErrorResponse(errorBody: ResponseBody?): NetworkResponse.Error {
        return NetworkResponse.Error(
            gson.fromJson(
                errorBody?.string(),
                WeatherAppBackendError::class.java
            )
        )
    }

    companion object {
        private const val GENERAL_SERVER_ERROR_MESSAGE = "Oops, something went wrong"
        private const val CONNECTION_TIMED_OUT_MESSAGE = "Connection timeout"
        private const val UNDEFINED_ERROR_CODE = 0
        private val gson: Gson by lazy {
            Gson()
        }
    }

}