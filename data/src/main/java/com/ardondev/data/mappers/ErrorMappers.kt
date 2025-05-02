package com.ardondev.data.mappers

import com.ardondev.domain.model.AppError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.io.IOException
import java.net.UnknownHostException

fun Throwable.toAppError(): AppError {
    this.printStackTrace()
    return when (this) {
        is ClientRequestException -> {
            AppError(response.status.value, "Solicitud incorrecta. Verifica los datos enviados.")
        }

        is ServerResponseException -> {
            AppError(response.status.value, "Error del servidor. Intenta nuevamente más tarde.")
        }

        is RedirectResponseException -> {
            AppError(response.status.value, "La solicitud fue redirigida. Revisa la URL o la configuración.")
        }

        is ResponseException -> {
            AppError(response.status.value, "Error inesperado en la respuesta del servidor.")
        }

        is NoTransformationFoundException -> {
            AppError(404, "Error al interpretar los datos.")
        }

        is UnknownHostException -> {
            AppError(-1, "Error de conexión.")
        }

        is IOException -> {
            AppError(-1, "Error de red.")
        }

        else -> {
            AppError(-1,"Error desconocido.")
        }
    }
}