package com.eagskunst.topddit.common

/**
 * From Chris Banes's Tivi:
 * https://github.com/chrisbanes/tivi/blob/main/base/src/main/java/app/tivi/data/entities/Result.kt
 */
sealed class DataResult<T> {
    open fun get(): T? = null

    fun getOrThrow(): T =
        when (this) {
            is Success -> get()
            is ErrorResult -> throw throwable
        }
}

data class Success<T>(val data: T) : DataResult<T>() {
    override fun get(): T = data
}

data class ErrorResult<T>(
    val throwable: Throwable,
    val errorInfo: ErrorInformation = ErrorInformation(throwable),
) : DataResult<T>()

enum class ErrorMessage {
    Http,
    Connection,
    Timeout,
    Unknown,
}

data class ErrorInformation(
    val throwable: Throwable,
    val message: ErrorMessage = ErrorMessage.Unknown,
)
