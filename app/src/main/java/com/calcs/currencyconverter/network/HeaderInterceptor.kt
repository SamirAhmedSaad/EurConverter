package com.calcs.currencyconverter.network

import com.calcs.currencyconverter.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    private val API_KEY = "apikey"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(API_KEY, BuildConfig.API_KEY)
            .build()
        return chain.proceed(request)
    }
}