package com.example.executive

import android.app.Application
import com.example.executive.di.component.ApplicationComponent
import com.example.executive.di.component.DaggerApplicationComponent
import com.example.executive.di.module.ApplicationModule

class MVVMApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}