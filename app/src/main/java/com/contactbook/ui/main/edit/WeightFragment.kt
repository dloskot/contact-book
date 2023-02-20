package com.contactbook.ui.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.contactbook.databinding.FragmentWeightBinding
import com.contactbook.ui.main.BaseFragment
import com.contactbook.ui.main.MainViewModel

class WeightFragment : BaseFragment() {

    private var _binding: FragmentWeightBinding? = null
    private lateinit var viewModel: MainViewModel

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWeightBinding.inflate(inflater, container, false)
        binding.clientWeight.setText(viewModel.currentClient.weight.toString())
        binding.clientWeight.doOnTextChanged { text, _, _, _ ->
            viewModel.currentClient.weight = text.toString().toInt()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}