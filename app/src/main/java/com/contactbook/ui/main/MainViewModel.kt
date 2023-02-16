package com.contactbook.ui.main

import androidx.lifecycle.ViewModel
import com.contactbook.App

class MainViewModel : ViewModel() {
    init {
        App.appComponent.inject(this)
    }
}