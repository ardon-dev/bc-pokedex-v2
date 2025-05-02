package com.ardondev.domain.model

data class AppError(
    val status: Int,
    override val message: String
): Throwable()


