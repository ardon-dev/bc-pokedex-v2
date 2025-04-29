package com.ardondev.core.network

import io.ktor.client.plugins.ResponseException

data class ApiError(val status: Int, override val message: String) : Throwable()

object ApiErrorMapper {

    fun map(throwable: Throwable): ApiError {
        return when (throwable) {
            is ResponseException -> ApiError(
                throwable.response.status.value,
                throwable.message.orEmpty()
            )

            else -> ApiError(500, throwable.message.orEmpty())
        }
    }
}
