package com.ardinata.test.wlb.core.model

class Result<T>(
    val data : T? = null,
    val message : String? = null,
    val status : Int = 0,
    val code : String = "XXX"
)