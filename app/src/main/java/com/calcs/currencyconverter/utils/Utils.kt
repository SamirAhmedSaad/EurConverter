package com.calcs.currencyconverter.utils

import android.content.Context
import android.content.res.Resources
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.text.DecimalFormat
import java.util.*


fun dp2Px(context: Context, dp: Int) = (dp * context.resources.displayMetrics.density).toInt()

fun dp2Px(dp: Float): Float = dp * Resources.getSystem().displayMetrics.density

fun Disposable.add(compositeDisposable: CompositeDisposable){
    compositeDisposable.add(this)
}

fun String?.toSafeDouble() : Double{
    return this?.toDoubleOrNull() ?: 0.0
}

fun Double.formatAmountPattern(): String {
    val formatter = DecimalFormat.getNumberInstance(Locale.ENGLISH) as DecimalFormat
    formatter.applyPattern("#,##0.00")
    return formatter.format(this)
}