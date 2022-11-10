package com.calcs.currencyconverter.utils.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.calcs.currencyconverter.domain.exception.traceErrorException
import com.calcs.currencyconverter.domain.model.ApiError
import com.calcs.currencyconverter.domain.model.ApiErrorDataModel
import com.calcs.currencyconverter.network.NetworkResult
import com.google.gson.Gson
import retrofit2.Response
import java.util.concurrent.CancellationException

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    //Internet connectivity check in Android Q
    val networks = connectivityManager.allNetworks
    var hasInternet = false
    if (networks.isNotEmpty()) {
        for (network in networks) {
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            if (networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) {
                hasInternet = true
            }
        }
    }
    return hasInternet
}

suspend fun <TYPE : Response<*>> networkApi(action: suspend () -> TYPE): NetworkResult<TYPE> {
    var response: TYPE? = null
    return try {
        response = action.invoke()
        /* Instead of generic "*"
        * we should define generic base class for REST data models to check for body status error in generic way
        * instead on only use response.isSuccessful but unfortunately time is limited.
        * */
        if (response.isSuccessful) {
            NetworkResult.OnSuccess(response)
        } else {
            val errorResponse = Gson().fromJson(response.errorBody()?.string() , ApiErrorDataModel::class.java)
            NetworkResult.OnError(ApiError(message = errorResponse.message, ApiError.ErrorStatus.UNKNOWN_ERROR))
        }
    } catch (e: CancellationException) {
        e.printStackTrace()
        NetworkResult.OnError(traceErrorException(e))
    } catch (e: Exception) {
        e.printStackTrace()
        NetworkResult.OnError(traceErrorException(e))
    }
}
