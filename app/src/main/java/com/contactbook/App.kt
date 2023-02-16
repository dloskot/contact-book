package com.contactbook

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.contactbook.di.AppComponent
import com.contactbook.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        appComponent = DaggerAppComponent.factory().create(this)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set

        lateinit var appComponent: AppComponent
            private set
    }
}