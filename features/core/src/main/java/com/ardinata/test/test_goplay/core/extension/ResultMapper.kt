package com.ardinata.test.test_goplay.core.extension

import com.ardinata.test.test_goplay.core.model.Result

fun <T> toResult(data: T?): Result<T> {
    return Result(data)
}