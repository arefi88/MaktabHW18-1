package com.example.maktabhw18_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabhw18_1.adapter.UserTasksAdapter
import com.example.maktabhw18_1.data.Task
import com.example.maktabhw18_1.databinding.FragmentUserTasksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserTasks : Fragment() {
    private var _binding:FragmentUserTasksBinding?=null
    private val binding get() = _binding!!
    private val viewModel:UserTaskViewModel by viewModels()
    private lateinit var taskAdapter:UserTasksAdapter
    private val args:UserTasksArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentUserTasksBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskAdapter=UserTasksAdapter(::onItemClicked,::onItemDeleteClicked)
        viewModel.getTasksForAdmin(args.user.userName)
        viewModel.taskLiveData.observe(viewLifecycleOwner){
            taskAdapter.differ.submitList(it.toList())
        }
        binding.rvUserTasks.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=taskAdapter
        }

    }

    private fun onItemClicked(task: Task){


    }
    private fun onItemDeleteClicked(task: Task){
         viewModel.deleteTask(task)
         args.user.numberTasks--
         viewModel.updateUser(args.user)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}