package com.eagskunst.topddit.domain

import com.eagskunst.topddit.common.DataResult
import com.eagskunst.topddit.common.ErrorMessage
import com.eagskunst.topddit.common.ErrorResult
import com.eagskunst.topddit.common.thread.Asyncable
import com.eagskunst.topddit.common.thread.CoroutineDispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseInteractor(protected val internalDispatchers: CoroutineDispatchers) : Asyncable {
    protected suspend inline fun switchToIo(crossinline block: suspend () -> Unit) {
        withContext(internalDispatchers.io) { block() }
    }

    protected suspend inline fun <T> switchToIoWithResult(crossinline block: suspend () -> T): T {
        return withContext(internalDispatchers.io) { block() }
    }

    protected fun <T> addErrorInformationToResult(errorResult: ErrorResult<T>): DataResult<T> {
        val errorInfo = errorResult.errorInfo
        val newInfo =
            when (errorInfo.throwable) {
                is HttpException -> errorInfo.copy(message = ErrorMessage.Unknown)
                is SocketTimeoutException -> errorInfo.copy(message = ErrorMessage.Timeout)
                is IOException -> errorInfo.copy(message = ErrorMessage.Connection)
                else -> errorInfo.copy(message = ErrorMessage.Unknown)
            }

        return ErrorResult(errorResult.throwable, newInfo)
    }
}
