package com.contactbook.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

open class BaseFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

}