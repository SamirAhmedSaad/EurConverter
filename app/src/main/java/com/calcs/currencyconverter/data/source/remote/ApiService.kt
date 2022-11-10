package com.calcs.currencyconverter.data.source.remote

import com.calcs.currencyconverter.domain.model.CurrencyRateExchangeDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("latest")
    suspend fun getCurrencyRateExchange(
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): Response<CurrencyRateExchangeDataModel>
}