package com.example.maktabhw18_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.maktabhw18_1.adapter.ViewPagerAdapter
import com.example.maktabhw18_1.databinding.FragmentMainTaskBinding
import com.example.maktabhw18_1.dialog.TaskDialogArgs
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskMainFragment:Fragment() {
    private var _binding:FragmentMainTaskBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewPagerAdapter: ViewPagerAdapter
     private val args:TaskMainFragmentArgs by navArgs()
    private val labelList:ArrayList<String> = arrayListOf("Todo","Doing","Done")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentMainTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter=ViewPagerAdapter(this,args.userName,args.user)
        binding.viewPager.adapter=viewPagerAdapter
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->
            tab.text=labelList[position]


        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}