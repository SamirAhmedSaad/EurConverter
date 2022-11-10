package com.calcs.currencyconverter.domain.model

data class CurrencyRateExchangeDataModel(
    val base: String?,
    val date: String?,
    val rates: Map<String,Double>?,
    val success: Boolean,
    val timestamp: Int?
)