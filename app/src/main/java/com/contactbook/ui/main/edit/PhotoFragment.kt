package com.contactbook.ui.main.edit

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.contactbook.R
import com.contactbook.databinding.FragmentPhotoBinding
import com.contactbook.ui.main.BaseFragment

class PhotoFragment : BaseFragment() {

    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private lateinit var resultLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            updatePhoto(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        binding.choosePhotoButton.setOnClickListener {
            choosePhoto()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Glide
            .with(this)
            .load(viewModel.currentClient.photo)
            .centerCrop()
            .placeholder(R.drawable.portrait_placeholder)
            .into(binding.photo)
    }

    private fun updatePhoto(uri: Uri?) {
        viewModel.currentClient.photo = uri
        Glide
            .with(this)
            .load(uri)
            .centerCrop()
            .placeholder(R.drawable.portrait_placeholder)
            .into(binding.photo)
    }

    private fun choosePhoto() {
        resultLauncher.launch("image/*")
    }
}