package com.ardinata.test.wlb.core.extension

import com.ardinata.test.wlb.core.model.Result

sealed class StatefulResponse<out T> {
    val succeeded
        get() = this is Success && data != null

    data class Success<T>(val data: Result<T>) : StatefulResponse<T>()
    data class Error(val exception: Throwable) : StatefulResponse<Nothing>()
}