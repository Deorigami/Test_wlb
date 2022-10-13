package com.ardinata.test.wlb.core.base

import com.ardinata.core.BuildConfig
import com.ardinata.test.wlb.core.extension.StatefulResponse
import com.ardinata.test.wlb.core.extension.StatefulResult
import com.ardinata.test.wlb.core.model.Error
import com.ardinata.test.wlb.core.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseUseCase <P, R> {
    abstract val default : R
    private var job: Job? = null

    abstract suspend fun build(param : P) : Result<R>

    fun execute(
        coroutineScope: CoroutineScope,
        param : P,
        onResult : (StatefulResult<R>) -> Unit
    ){
        job = coroutineScope.launch {
            onResult(
                either {
                    build(param)
                }.toResult()
            )
        }
    }

    fun cancel(){
        job?.cancel()
    }

    suspend fun <V> either(block: suspend () -> Result<V>): StatefulResponse<V> = runCatching {
        StatefulResponse.Success(block())
    }.getOrElse {
        StatefulResponse.Error(it)
    }

    private fun <T> StatefulResponse<T>.toResult(): StatefulResult<T> {
        return when (this) {
            is StatefulResponse.Error -> StatefulResult.Failed(this.exception.toError())
            is StatefulResponse.Success -> StatefulResult.Success(this.data.data!!)
        }
    }

    private fun Throwable.toError(): Error {
        if (BuildConfig.DEBUG) {
            printStackTrace()
        }
        return when (this) {
            is SocketTimeoutException, is InterruptedIOException, is UnknownHostException, is ConnectException -> Error("NO INTERNET", this.message?: this::class.java.simpleName)
            is Exception -> Error("NO DATA", this.localizedMessage ?: this::class.java.simpleName)
            else -> Error("", this.message ?: this::class.java.simpleName)
        }
    }
}