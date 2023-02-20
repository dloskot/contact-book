package com.contactbook.ui.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.contactbook.R
import com.contactbook.databinding.FragmentDateOfBirthBinding
import com.contactbook.databinding.FragmentPhotoBinding
import com.contactbook.ui.main.BaseFragment
import com.contactbook.ui.main.EditActivity
import com.contactbook.ui.main.MainViewModel

class PhotoFragment : BaseFragment() {

    private var _binding: FragmentPhotoBinding? = null
    private lateinit var viewModel: MainViewModel

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        binding.choosePhotoButton.setOnClickListener {

        }
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }
}