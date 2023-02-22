package com.contactbook.di.modules

import com.contactbook.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object MainModule {
    @Singleton
    @Provides
    fun provideMainViewModel(): MainViewModel {
        return MainViewModel()
    }
}