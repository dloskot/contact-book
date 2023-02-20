package com.contactbook.ui.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.contactbook.databinding.FragmentWeightBinding
import com.contactbook.ui.main.BaseFragment
import com.contactbook.ui.main.MainViewModel

class WeightFragment : BaseFragment() {

    private var _binding: FragmentWeightBinding? = null
    private val viewModel: MainViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWeightBinding.inflate(inflater, container, false)
        binding.clientWeight.setText(viewModel.currentClient.weight.toString())
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}