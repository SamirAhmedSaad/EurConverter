package com.calcs.currencyconverter.ui.currencyRates

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calcs.currencyconverter.domain.model.ApiError
import com.calcs.currencyconverter.domain.model.viewdatamodel.CurrencyRateViewData
import com.calcs.currencyconverter.domain.model.viewdatamodel.mapToViewData
import com.calcs.currencyconverter.domain.repository.CurrencyRateExchangeRepository
import com.calcs.currencyconverter.network.NetworkResult
import com.calcs.currencyconverter.utils.SingleLiveEvent
import com.calcs.currencyconverter.utils.extensions.networkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class CurrencyRateExchangeViewModel(private val currencyRatesExchangeRepositoryImp: CurrencyRateExchangeRepository) :
    ViewModel() {

    val showProgressbar = SingleLiveEvent<Int>()
    val currencyRateLiveData = MutableLiveData<CurrencyRateViewData>()
    val apiErrorData = MutableLiveData<ApiError>()

    fun getCurrencyRatesExchange(
        baseCurrency: String = "EUR",
        targetRates: List<String> = listOf("USD", "EGP", "BBD", "AED", "ARS")
    ) {
        showProgressbar.value = View.VISIBLE
        viewModelScope.launch(Dispatchers.IO) {
            val response = networkApi { currencyRatesExchangeRepositoryImp.getCurrencyRates(baseCurrency,targetRates) }
            when (response) {
                is NetworkResult.OnError -> apiErrorData.postValue(response.apiError)
                is NetworkResult.OnSuccess -> currencyRateLiveData.postValue(
                    response.result.body()?.mapToViewData()
                )
            }.also { showProgressbar.postValue(View.GONE) }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}