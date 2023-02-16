package com.contactbook.ui.main

import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.contactbook.R

class EditActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        viewPager = findViewById(R.id.pager)

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewPager.currentItem > 0) {
                    viewPager.currentItem = viewPager.currentItem - 1
                }
            }
        }
        onBackPressedDispatcher.addCallback(callback)

        //TODO: add clicklistener to binding
        findViewById<Button>(R.id.back_button).setOnClickListener {
            if (viewPager.currentItem > 0){
                viewPager.currentItem--
            } else {
                finish()
            }
        }
        //TODO: add clicklistener to binding
        findViewById<Button>(R.id.next_button).setOnClickListener {
            if (viewPager.currentItem < NUM_PAGES - 1) {
                viewPager.currentItem++
            } else {
                finish()
            }
        }
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {

            //TODO: Move creating to Dagger side
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