package com.calcs.currencyconverter.ui.currencyconverter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calcs.currencyconverter.domain.managers.CurrencyConversionsManager
import com.calcs.currencyconverter.domain.model.viewdatamodel.CurrencyRate

class ConvertCurrencyViewModel (private val currencyConversionsManager: CurrencyConversionsManager) : ViewModel() {

    val targetCurrencyLiveData = MutableLiveData<String>()
    val currencyRateLiveData = MutableLiveData<CurrencyRate>()

    fun convertBaseCurrency(amount : String){
        this.targetCurrencyLiveData.value =
            currencyConversionsManager.convertCurrency(amount,currencyRateLiveData.value?.rate.toString())
    }

}