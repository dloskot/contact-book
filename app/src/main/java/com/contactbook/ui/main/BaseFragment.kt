package com.contactbook.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.contactbook.App
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
    }
}