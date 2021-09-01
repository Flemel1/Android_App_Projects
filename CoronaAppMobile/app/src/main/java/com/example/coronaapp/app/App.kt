package com.example.coronaapp.app

import android.app.Application
import com.example.coronaapp.app.di.Injector
import com.example.coronaapp.app.di.core.AppComponent
import com.example.coronaapp.app.di.core.DaggerAppComponent
import com.example.coronaapp.app.di.module.CoronaCaseSubComponent

class App : Application(), Injector {
    private lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    override fun createCoronaCaseSubComponent(): CoronaCaseSubComponent {
        return appComponent.coronaCaseSubComponent().create()
    }

}