package com.hamz.weatherapp.data.source.remote

import com.tosanaji.tajilestari.util.ApiException
import retrofit2.Response
import java.lang.StringBuilder

abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {

        val response = call.invoke()

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val message = StringBuilder()
            val error = response.errorBody()?.string()
            error.let {
                message.append("\n")
            }
            message.append("Error code : ${response.code()}")
            message.append("\n")
            message.append(response.errorBody()?.toString())

            throw ApiException(message.toString())
        }
    }
}