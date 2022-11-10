package com.calcs.currencyconverter.di

import com.calcs.currencyconverter.BuildConfig
import com.calcs.currencyconverter.network.HeaderInterceptor
import com.calcs.currencyconverter.data.source.remote.ApiService
import com.calcs.currencyconverter.network.ConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.BASE_URL) }

    single { createOkHttpClient(get(),get(),get()) }

    single { createHttpLoggingInterceptor() }

    single { createHeaderInterceptor() }

    single { ConnectionInterceptor(get()) }

}

fun createOkHttpClient(
    httpLoggingInterceptor : HttpLoggingInterceptor,
    headerInterceptor: HeaderInterceptor,
    connectionInterceptor: ConnectionInterceptor,
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(connectionInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(headerInterceptor)
        .build()
}

fun createHttpLoggingInterceptor(): HttpLoggingInterceptor {
   return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

fun createHeaderInterceptor(): HeaderInterceptor {
    return HeaderInterceptor()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}