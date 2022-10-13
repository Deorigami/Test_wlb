package com.ardinata.test.wlb.core.extension

import com.ardinata.test.wlb.core.model.Result

fun <T> toResult(data: T?): Result<T> {
    return Result(data)
}