package com.calcs.currencyconverter.di

import com.calcs.currencyconverter.data.repository.CurrencyRatesExchangeRepositoryImp
import com.calcs.currencyconverter.data.source.remote.ApiService
import com.calcs.currencyconverter.domain.managers.CurrencyConversionsManager
import com.calcs.currencyconverter.domain.repository.CurrencyRateExchangeRepository
import com.calcs.currencyconverter.ui.currencyRates.CurrencyRateExchangeViewModel
import com.calcs.currencyconverter.ui.currencyconverter.ConvertCurrencyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { CurrencyRateExchangeViewModel(get()) }

    viewModel { ConvertCurrencyViewModel(get()) }

    single { createCurrencyRateExchangeRepository(get()) }

    single { CurrencyConversionsManager() }

    //we can use factory if we need new instance every time we need dependency no needed case here

}


fun createCurrencyRateExchangeRepository(apiService: ApiService): CurrencyRateExchangeRepository {
    return CurrencyRatesExchangeRepositoryImp(apiService)
}