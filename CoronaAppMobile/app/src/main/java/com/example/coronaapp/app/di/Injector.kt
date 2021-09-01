package com.example.coronaapp.app.di

import com.example.coronaapp.app.di.module.CoronaCaseSubComponent

interface Injector {
    fun createCoronaCaseSubComponent() : CoronaCaseSubComponent
}