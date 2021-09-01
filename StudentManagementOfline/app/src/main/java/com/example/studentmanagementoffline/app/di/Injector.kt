package com.example.studentmanagementoffline.app.di

import com.example.studentmanagementoffline.app.di.subcomponent.SubComponentApp

interface Injector {
    fun createSubComponentApp(): SubComponentApp
}