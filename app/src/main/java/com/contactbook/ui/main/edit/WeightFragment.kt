package com.contactbook.ui.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.contactbook.R
import com.contactbook.data.model.SystemOfMeasure
import com.contactbook.databinding.FragmentWeightBinding
import com.contactbook.ui.main.BaseFragment

class WeightFragment : BaseFragment() {

    private var _binding: FragmentWeightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentWeightBinding.inflate(inflater, container, false)
        binding.clientWeight.doOnTextChanged { text, _, _, _ ->
            try {
                val weight = text.toString().toInt()
                viewModel.currentClient.weight = weight
            } catch (ex: NumberFormatException) {
                //TODO: Show error message
            }
        }
        binding.metricUnitsSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.weightInitOfMeasure.text = requireContext().getString(R.string.kilogram)
                viewModel.currentClient.systemOfMeasure = SystemOfMeasure.METRIC
            } else {
                binding.weightInitOfMeasure.text = requireContext().getString(R.string.pound)
                viewModel.currentClient.systemOfMeasure = SystemOfMeasure.IMPERIAL
            }

        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.clientWeight.setText(viewModel.currentClient.weight.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}