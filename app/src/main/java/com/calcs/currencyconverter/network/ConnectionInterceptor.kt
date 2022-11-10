package com.calcs.currencyconverter.network

import android.content.Context
import com.calcs.currencyconverter.domain.model.ApiError
import com.calcs.currencyconverter.domain.model.ApiErrorDataModel
import com.calcs.currencyconverter.domain.model.ErrorDataModel
import com.calcs.currencyconverter.utils.extensions.isNetworkAvailable
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class ConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().build()
        if (!context.isNetworkAvailable()) {
            return generateDefaultResponse(
                context, request,
                ApiError(errorStatus = ApiError.ErrorStatus.NO_CONNECTION)
            )
        }
        return chain.proceed(request)
    }

    private fun generateDefaultResponse(
        context: Context,
        request: Request,
        errorCode: ApiError,
    ): Response =
        Response.Builder()
            .code(502)
            .request(request)
            .message(errorCode.getErrorMessage(context))
            .protocol(Protocol.HTTP_2)
            .body(createTimeOutResponseBody(errorCode.getErrorMessage(context)))
            .build()

    private fun createTimeOutResponseBody(
        message: String,
    ) = GsonBuilder()
        .create()
        .toJson(
            ApiErrorDataModel(
                success = false,
                message = message,
                error = ErrorDataModel(
                    code = 502,
                    type = "",
                    info = message
                )
            )
        ).toResponseBody()
}
