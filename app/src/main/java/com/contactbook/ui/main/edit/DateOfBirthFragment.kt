package com.contactbook.ui.main.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import com.contactbook.databinding.FragmentDateOfBirthBinding
import com.contactbook.ui.main.BaseFragment
import com.contactbook.ui.main.MainViewModel
import java.time.LocalDate

class DateOfBirthFragment : BaseFragment() {

    private var _binding: FragmentDateOfBirthBinding? = null
    private lateinit var viewModel: MainViewModel

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDateOfBirthBinding.inflate(inflater, container, false)
        binding.dateOfBirth.setOnDateChangedListener { datePicker, _, _, _ ->
            saveSelectedDate(datePicker)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setDate()
    }

    private fun setDate() {
        val date = viewModel.currentClient.dateOfBirth
        binding.dateOfBirth.updateDate(date.year, date.monthValue, date.dayOfMonth)
    }

    private fun saveSelectedDate(datePicker: DatePicker) {
        viewModel.currentClient.dateOfBirth = LocalDate.of(datePicker.year, datePicker.month, datePicker.dayOfMonth)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}