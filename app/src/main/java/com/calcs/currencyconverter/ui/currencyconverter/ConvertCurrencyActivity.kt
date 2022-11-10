package com.calcs.currencyconverter.ui.currencyconverter

import com.calcs.currencyconverter.R
import com.calcs.currencyconverter.databinding.ActivityConvertCurrencyBinding
import com.calcs.currencyconverter.domain.model.viewdatamodel.CurrencyRate
import com.calcs.currencyconverter.utils.BaseActivity
import com.calcs.currencyconverter.utils.add
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class ConvertCurrencyActivity : BaseActivity<ActivityConvertCurrencyBinding>() {

    companion object {
        const val CURRENCY_BASE = "currencyBase"
        const val CURRENCY_LAST_MODIFIED = "currencyLastModified"
        const val CURRENCY_RATE = "currencyRate"
    }

    private lateinit var lastModifiedDate: String
    private lateinit var baseCurrency: String
    private var currencyRate: CurrencyRate? = null
    private val convertCurrencyViewModel: ConvertCurrencyViewModel by viewModel()
    private val compositeDisposable = CompositeDisposable()

    override fun resourceId(): Int = R.layout.activity_convert_currency

    override fun initUi() {
        this.dataBinding.toolTitle = getString(R.string.convertBasedOnEur)
        this.dataBinding.baseCurrencyName = baseCurrency
        this.dataBinding.targetCurrencyName = currencyRate?.name
        this.dataBinding.lastUpdatedDate = lastModifiedDate
        this.convertCurrencyViewModel.currencyRateLiveData.value = currencyRate
    }

    override fun getIntentData() {
        intent.extras?.run {
            baseCurrency = getString(CURRENCY_BASE, "")
            lastModifiedDate = getString(CURRENCY_LAST_MODIFIED, "")
            currencyRate = getParcelable(CURRENCY_RATE)
        }
    }

    override fun clicks() {
        this.dataBinding.toolbar.backImg.setOnClickListener {
            this.onBackPressed()
        }

        this.dataBinding.baseCurrencyLayout.currencyRateTxt.textChanges()
            .skipInitialValue()
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                convertCurrencyViewModel.convertBaseCurrency(amount = it.toString())
            }.add(compositeDisposable)
    }

    override fun observe() {
        this.convertCurrencyViewModel.targetCurrencyLiveData.observe(this) {
            this.dataBinding.targetCurrencyLayout.currencyValue = it
        }
    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onDestroy()
    }

}