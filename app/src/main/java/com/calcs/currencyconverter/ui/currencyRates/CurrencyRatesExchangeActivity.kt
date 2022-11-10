package com.calcs.currencyconverter.ui.currencyRates

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.LinearLayoutManager
import com.calcs.currencyconverter.R
import com.calcs.currencyconverter.databinding.ActivityCurrencyRateExchangeBinding
import com.calcs.currencyconverter.domain.model.viewdatamodel.CurrencyRateViewData
import com.calcs.currencyconverter.ui.currencyconverter.ConvertCurrencyActivity
import com.calcs.currencyconverter.utils.BaseActivity
import com.calcs.currencyconverter.utils.extensions.addDefaultVerticalDivider
import kotlinx.android.synthetic.main.activity_currency_rate_exchange.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CurrencyRatesExchangeActivity : BaseActivity<ActivityCurrencyRateExchangeBinding>() {

    private val currencyRatesViewModel: CurrencyRateExchangeViewModel by viewModel()

    override fun resourceId(): Int = R.layout.activity_currency_rate_exchange

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.currencyRatesViewModel.getCurrencyRatesExchange()
    }

    override fun getIntentData() {}

    override fun initUi() {
        this.dataBinding.toolTitle = getString(R.string.EURbaseTitle)
    }

    override fun observe() {

        currencyRatesViewModel.run {
            currencyRateLiveData.observe(this@CurrencyRatesExchangeActivity) {
                this@CurrencyRatesExchangeActivity.dataBinding.lastUpdatedDate = it.date
                this@CurrencyRatesExchangeActivity.configureCurrencyRatesAdapter(it)
            }

            apiErrorData.observe(this@CurrencyRatesExchangeActivity) {
                Toast.makeText(
                    this@CurrencyRatesExchangeActivity,
                    it?.getErrorMessage(this@CurrencyRatesExchangeActivity),
                    LENGTH_LONG
                ).show()
            }

            showProgressbar.observe(this@CurrencyRatesExchangeActivity) {
                this@CurrencyRatesExchangeActivity.dataBinding.loadingVisibility = it
            }
        }
    }

    private fun configureCurrencyRatesAdapter(currencyViewModelData: CurrencyRateViewData) {
        currencyRatesRecyclerView.apply {
            adapter = CurrencyRatesAdapter(currencyViewModelData.rates) { currencyRate ->
                val intent = Intent(
                    this@CurrencyRatesExchangeActivity,
                    ConvertCurrencyActivity::class.java
                )
                intent.apply {
                    putExtra(
                        ConvertCurrencyActivity.CURRENCY_BASE,
                        currencyViewModelData.base
                    )
                    putExtra(
                        ConvertCurrencyActivity.CURRENCY_LAST_MODIFIED,
                        currencyViewModelData.date
                    )
                    putExtra(ConvertCurrencyActivity.CURRENCY_RATE, currencyRate)
                }
                startActivity(intent)
            }
            layoutAnimation = AnimationUtils.loadLayoutAnimation(
                this@CurrencyRatesExchangeActivity,
                R.anim.item_recycler_anim
            )
            layoutManager = LinearLayoutManager(this@CurrencyRatesExchangeActivity)
            addDefaultVerticalDivider()
        }
    }

    override fun clicks() {

        refreshLayout.setOnRefreshListener {
            refreshLayout.isRefreshing = false
            this.currencyRatesViewModel.getCurrencyRatesExchange()
        }

    }
}

