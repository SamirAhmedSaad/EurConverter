package com.calcs.currencyconverter.data.repository

import com.calcs.currencyconverter.data.source.remote.ApiService
import com.calcs.currencyconverter.domain.model.CurrencyRateExchangeDataModel
import com.calcs.currencyconverter.domain.repository.CurrencyRateExchangeRepository
import retrofit2.Response

class CurrencyRatesExchangeRepositoryImp(private val apiService: ApiService) :
    CurrencyRateExchangeRepository {

    override suspend fun getCurrencyRates(
        baseCurrency: String,
        targetRates: List<String>
    ): Response<CurrencyRateExchangeDataModel> {
        return apiService.getCurrencyRateExchange(
            base = baseCurrency,
            symbols = targetRates.joinToString(",")
        )
    }

}