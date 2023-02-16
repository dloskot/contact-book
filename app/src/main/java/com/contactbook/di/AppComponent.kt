package com.contactbook.di

import android.content.Context
import com.contactbook.ui.main.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainModule::class
    ]
)
interface AppComponent {

    fun inject(viewModel: MainViewModel)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}