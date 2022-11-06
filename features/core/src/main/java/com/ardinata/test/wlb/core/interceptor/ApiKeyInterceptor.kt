package com.ardinata.test.wlb.core.interceptor

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl = request.url.newBuilder().addQueryParameter("api_key", "0b9fa6bda2fa39b65d867c3edf4ee2d0").build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}