package com.calcs.currencyconverter.domain.model

data class ApiErrorDataModel(
    val success: Boolean,
    val message: String,
    val error: ErrorDataModel
)

data class ErrorDataModel(
    val code: Int,
    val type: String,
    val info: String,
)