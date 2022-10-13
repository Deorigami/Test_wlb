package com.ardinata.test.wlb.core.interception

import com.ardinata.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter("key", BuildConfig.API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}