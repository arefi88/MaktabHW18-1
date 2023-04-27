package com.example.maktabhw18_1.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.maktabhw18_1.data.User
import com.example.maktabhw18_1.ui.DoingFragment
import com.example.maktabhw18_1.ui.DoneFragment
import com.example.maktabhw18_1.ui.TodoFragment

class ViewPagerAdapter(fragment: Fragment,private val userName:String,
                      private val user:User) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 3


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                TodoFragment(userName,user)
            }
            1 -> {
                DoingFragment(userName,user)
            }
            2 -> {
                DoneFragment(userName,user)
            }
            else -> {
                TodoFragment(userName,user)
            }
        }
    }
}