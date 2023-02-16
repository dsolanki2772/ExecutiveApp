package com.example.executive.di.component

import com.example.executive.di.ActivityScope
import com.example.executive.di.module.ActivityModule
import com.example.executive.ui.userdetails.UserActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: UserActivity)
}