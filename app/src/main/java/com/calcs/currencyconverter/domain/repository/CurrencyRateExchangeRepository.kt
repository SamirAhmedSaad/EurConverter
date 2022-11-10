package com.calcs.currencyconverter.domain.repository

import com.calcs.currencyconverter.domain.model.CurrencyRateExchangeDataModel
import retrofit2.Response

interface CurrencyRateExchangeRepository {

    suspend fun getCurrencyRates(
        baseCurrency: String = "EUR",
        targetRates: List<String> = listOf("USD","EGP","BBD","AED","ARS")
    ): Response<CurrencyRateExchangeDataModel>
}