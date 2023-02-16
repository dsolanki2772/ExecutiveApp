package com.example.executive.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.executive.data.model.Country
import com.example.executive.data.repository.UserDetailsRepository
import com.example.executive.di.ActivityContext
import com.example.executive.ui.base.ViewModelProviderFactory
import com.example.executive.ui.userdetails.UserAdapter
import com.example.executive.ui.userdetails.UserViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {
    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideTopHeadlineViewModel(userDetailsRepository: UserDetailsRepository): UserViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(UserViewModel::class) {
                UserViewModel(activity.applicationContext,userDetailsRepository)
            })[UserViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = UserAdapter(activity,HashMap(),true)
}