package com.calcs.currencyconverter.network

import com.calcs.currencyconverter.domain.model.ApiError

sealed class NetworkResult<Type : Any> {

    class OnSuccess<Type : Any>(val result: Type) : NetworkResult<Type>()

    class OnError<Type : Any>(val apiError: ApiError) : NetworkResult<Type>()

}

