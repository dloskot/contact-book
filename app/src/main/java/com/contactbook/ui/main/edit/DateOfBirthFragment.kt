package com.contactbook.ui.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.contactbook.databinding.FragmentDateOfBirthBinding
import com.contactbook.ui.main.BaseFragment
import com.contactbook.ui.main.MainViewModel
import java.util.*

class DateOfBirthFragment : BaseFragment() {

    private var _binding: FragmentDateOfBirthBinding? = null
    private val viewModel: MainViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDateOfBirthBinding.inflate(inflater, container, false)
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = viewModel.currentClient.dateOfBirth
        binding.dateOfBirth.updateDate(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}