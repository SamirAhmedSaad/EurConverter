package com.calcs.currencyconverter.application

import android.app.Application
import com.calcs.currencyconverter.di.AppModule
import com.calcs.currencyconverter.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        this.initKoin()
    }

    private fun initKoin(){
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(listOf(AppModule, NetworkModule))
        }
    }

}