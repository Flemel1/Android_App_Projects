package com.example.studentmanagementoffline.app

import android.app.Application
import com.example.studentmanagementoffline.app.di.Injector
import com.example.studentmanagementoffline.app.di.core.AppComponent
import com.example.studentmanagementoffline.app.di.core.AppModule
import com.example.studentmanagementoffline.app.di.core.DaggerAppComponent
import com.example.studentmanagementoffline.app.di.subcomponent.SubComponentApp

class App : Application(), Injector {

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(applicationContext)).build()
    }

    override fun createSubComponentApp(): SubComponentApp {
        return appComponent.subComponentApp().create()
    }
}