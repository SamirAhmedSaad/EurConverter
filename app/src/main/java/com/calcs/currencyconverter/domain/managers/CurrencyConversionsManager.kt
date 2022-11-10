package com.calcs.currencyconverter.domain.managers

import com.calcs.currencyconverter.utils.formatAmountPattern
import com.calcs.currencyconverter.utils.toSafeDouble

class CurrencyConversionsManager {

    fun convertCurrency(baseCurrency : String,targetRate : String?) : String {
        val conversionResult =  baseCurrency.toSafeDouble() * targetRate.toSafeDouble()
        return conversionResult.formatAmountPattern()
    }

}