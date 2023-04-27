package com.example.maktabhw18_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabhw18_1.adapter.TaskAdapter
import com.example.maktabhw18_1.adapter.UserAdapter
import com.example.maktabhw18_1.data.User
import com.example.maktabhw18_1.databinding.FragmentUsersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() {
    private var _binding:FragmentUsersBinding?=null
    private val binding get() = _binding!!
    private val viewModel:UserTaskViewModel by viewModels()
    lateinit var userAdapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentUsersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter=UserAdapter(::onItemClicked)
        viewModel.getUsers()
        viewModel.userListLiveData.observe(viewLifecycleOwner){users->
            userAdapter.differ.submitList(users.toList())
        }
        binding.rvUsers.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=userAdapter
        }
    }

    private fun onItemClicked(user: User){
        val action=UsersFragmentDirections.actionUsersFragmentToDeleteUserDialog(user)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}