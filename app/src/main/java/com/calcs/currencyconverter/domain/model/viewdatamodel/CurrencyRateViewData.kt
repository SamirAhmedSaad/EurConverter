package com.calcs.currencyconverter.domain.model.viewdatamodel

import android.os.Parcelable
import com.calcs.currencyconverter.domain.model.CurrencyRateExchangeDataModel
import com.calcs.currencyconverter.utils.extensions.DatePattern
import com.calcs.currencyconverter.utils.extensions.formatDate
import kotlinx.android.parcel.Parcelize

data class CurrencyRateViewData(
    val base: String,
    val date: String,
    val rates: List<CurrencyRate>,
)

@Parcelize
data class CurrencyRate(
    val name: String,
    val rate: Double
): Parcelable

fun CurrencyRateExchangeDataModel.mapToViewData() : CurrencyRateViewData{
    val baseCurrency = this.base.orEmpty()
    val formattedDate =  this.date.formatDate(DatePattern.DATE_DD_MMM_YY)
    val ratesList = this.rates?.flatMap { listOf(CurrencyRate(it.key,it.value)) }.orEmpty()
    return CurrencyRateViewData(
        base = baseCurrency,
        date = formattedDate,
        rates = ratesList
    )
}