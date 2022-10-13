package com.ardinata.test.wlb.core.model

import com.google.gson.annotations.SerializedName

class ResultDto<T>(
    @SerializedName("drinks")
    val data: T? = null,
) {
    companion object {
        const val SUCCESS = "000"
        fun <T> ResultDto<*>.toResult(data : T?) = Result(
            data = data
        )
    }
}