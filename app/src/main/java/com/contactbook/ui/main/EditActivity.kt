package com.contactbook.ui.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.contactbook.App
import com.contactbook.R
import com.contactbook.databinding.ActivityEditBinding
import com.contactbook.ui.main.edit.DateOfBirthFragment
import com.contactbook.ui.main.edit.PhotoFragment
import com.contactbook.ui.main.edit.WeightFragment
import javax.inject.Inject

class EditActivity : AppCompatActivity() {

    @Inject
    lateinit var  viewModel: MainViewModel
    private var _binding: ActivityEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        _binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        binding.pager.adapter = pagerAdapter

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.pager.currentItem > 0) {
                    binding.pager.currentItem = binding.pager.currentItem - 1
                }
            }
        }
        onBackPressedDispatcher.addCallback(callback)

        binding.backButton.setOnClickListener {
            if (binding.pager.currentItem > 0){
                binding.pager.currentItem--
                binding.nextButton.text = getString(R.string.next_button_label)
            } else {
                finish()
            }
        }
        binding.nextButton.setOnClickListener {
            if (binding.pager.currentItem == NUM_PAGES - 2) {
                binding.nextButton.text = getString(R.string.done_button_label)
            }
            if (binding.pager.currentItem < NUM_PAGES - 1) {
                binding.pager.currentItem++
            } else {
                if (!viewModel.editMode) {
                    viewModel.addClient()
                }
                finish()
            }
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            when (position) {
                1 -> return DateOfBirthFragment()
                2 -> return PhotoFragment()
            }
            return WeightFragment()
        }
    }

    companion object {
        private const val NUM_PAGES = 3
    }
}