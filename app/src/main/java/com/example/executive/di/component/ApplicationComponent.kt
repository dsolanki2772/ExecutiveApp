package com.example.executive.di.component

import android.content.Context
import com.example.executive.MVVMApplication
import com.example.executive.data.api.NetworkService
import com.example.executive.data.repository.UserDetailsRepository
import com.example.executive.di.ApplicationContext
import com.example.executive.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MVVMApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getUserDetailsRepository(): UserDetailsRepository
}