package com.example.maktabhw18_1.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.maktabhw18_1.ui.DoingFragment
import com.example.maktabhw18_1.ui.DoneFragment
import com.example.maktabhw18_1.ui.TodoFragment

class ViewPagerAdapter(fragment: Fragment,private val userName:String) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TodoFragment(userName)
            }
            1 -> {
                DoingFragment(userName)
            }
            2 -> {
                DoneFragment(userName)
            }
            else -> {
                TodoFragment(userName)
            }
        }
    }
}