package com.example.studentmanagementoffline.app.di.core

import android.content.Context
import com.example.studentmanagementoffline.app.di.subcomponent.SubComponentApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [SubComponentApp::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}