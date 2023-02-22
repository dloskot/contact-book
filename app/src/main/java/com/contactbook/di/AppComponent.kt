package com.contactbook.di

import android.content.Context
import com.contactbook.di.modules.MainModule
import com.contactbook.di.modules.PersistenceModule
import com.contactbook.di.modules.RemoteModule
import com.contactbook.ui.main.BaseFragment
import com.contactbook.ui.main.EditActivity
import com.contactbook.ui.main.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainModule::class,
        PersistenceModule::class,
        RemoteModule::class
    ]
)
interface AppComponent {

    fun inject(viewModel: MainViewModel)
    fun inject(baseFragment: BaseFragment)
    fun inject(editActivity: EditActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}