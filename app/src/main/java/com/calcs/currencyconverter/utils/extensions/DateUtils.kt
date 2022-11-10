package com.calcs.currencyconverter.utils.extensions

import java.text.SimpleDateFormat
import java.util.*


enum class DatePattern(val format: String) {
    DATE_YYYY_MM_DD("yyyy-MM-dd"),
    DATE_DD_MMM_YY("dd MMM,yyyy"),
}

fun String?.formatDate(datePattern: DatePattern) : String{
    this ?: return ""
    val lastUpdateDate = SimpleDateFormat(DatePattern.DATE_YYYY_MM_DD.format, Locale.getDefault()).parse(this)
    return SimpleDateFormat(datePattern.format, Locale.getDefault()).format(lastUpdateDate)
}