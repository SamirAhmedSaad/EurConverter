package com.calcs.currencyconverter.utils

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.calcs.currencyconverter.R

abstract class BaseActivity<D : ViewDataBinding> : AppCompatActivity() {

    lateinit var dataBinding: D

    abstract fun resourceId(): Int

    abstract fun getIntentData()

    abstract fun clicks()

    abstract fun initUi()

    abstract fun observe()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.dataBinding = DataBindingUtil.setContentView(this, resourceId())
        this.dataBinding.lifecycleOwner = this
        this.getIntentData()
        this.initUi()
        this.observe()
        this.clicks()
    }

    override fun onBackPressed() {
        this.overridePendingTransition(R.anim.no_change, R.anim.exit_slide_right)
        super.onBackPressed()
    }

}