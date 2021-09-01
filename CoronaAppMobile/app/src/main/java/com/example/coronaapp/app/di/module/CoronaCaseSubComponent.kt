package com.example.coronaapp.app.di.module

import com.example.coronaapp.app.presentation.HomeFragment
import dagger.Subcomponent

@CoronaCaseScope
@Subcomponent(modules = [CoronaCaseModule::class])
interface CoronaCaseSubComponent {

    fun inject(homeFragment: HomeFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create() : CoronaCaseSubComponent
    }

}