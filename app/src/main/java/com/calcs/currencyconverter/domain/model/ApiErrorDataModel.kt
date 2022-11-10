package com.calcs.currencyconverter.domain.model

import android.content.Context
import android.icu.text.DateTimePatternGenerator.PatternInfo.CONFLICT
import com.calcs.currencyconverter.R


private const val BAD_REQUEST_ERROR_MESSAGE = R.string.bad_request
private const val FORBIDDEN_ERROR_MESSAGE = R.string.bad_request
private const val NOT_FOUND_ERROR_MESSAGE = R.string.bad_request
private const val METHOD_NOT_ALLOWED_ERROR_MESSAGE = R.string.bad_request
private const val CONFLICT_ERROR_MESSAGE = R.string.bad_request
private const val UNAUTHORIZED_ERROR_MESSAGE = R.string.user_not_authorized
private const val INTERNAL_SERVER_ERROR_MESSAGE = R.string.internal_server_error
private const val NO_CONNECTION_ERROR_MESSAGE = R.string.no_internet_connection
private const val TIMEOUT_ERROR_MESSAGE = R.string.timeout
const val UNKNOWN_ERROR_MESSAGE = R.string.bad_request


data class ApiError(val message: String?, val code: Int?, var errorStatus: ErrorStatus) {

    constructor(message: String? = null, errorStatus: ErrorStatus) : this(message, null, errorStatus)

    fun getErrorMessage(context: Context): String =  if(message.isNullOrBlank()) {
        context.run {
            getString(
                when (errorStatus) {
                    ErrorStatus.BAD_REQUEST -> BAD_REQUEST_ERROR_MESSAGE
                    ErrorStatus.FORBIDDEN -> FORBIDDEN_ERROR_MESSAGE
                    ErrorStatus.NOT_FOUND -> NOT_FOUND_ERROR_MESSAGE
                    ErrorStatus.METHOD_NOT_ALLOWED -> METHOD_NOT_ALLOWED_ERROR_MESSAGE
                    ErrorStatus.CONFLICT -> CONFLICT_ERROR_MESSAGE
                    ErrorStatus.UNAUTHORIZED -> UNAUTHORIZED_ERROR_MESSAGE
                    ErrorStatus.INTERNAL_SERVER_ERROR -> INTERNAL_SERVER_ERROR_MESSAGE
                    ErrorStatus.NO_CONNECTION -> NO_CONNECTION_ERROR_MESSAGE
                    ErrorStatus.TIMEOUT -> TIMEOUT_ERROR_MESSAGE
                    ErrorStatus.UNKNOWN_ERROR -> UNKNOWN_ERROR_MESSAGE
                }
            )
        }
    } else message

    enum class ErrorStatus {
        BAD_REQUEST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        METHOD_NOT_ALLOWED,
        CONFLICT,
        INTERNAL_SERVER_ERROR,
        TIMEOUT,
        NO_CONNECTION,
        UNKNOWN_ERROR
    }

}
