package com.example.maktabhw18_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabhw18_1.State
import com.example.maktabhw18_1.UserTaskViewModel
import com.example.maktabhw18_1.adapter.TaskAdapter
import com.example.maktabhw18_1.data.Task
import com.example.maktabhw18_1.data.User
import com.example.maktabhw18_1.databinding.FragmentDoneBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DoneFragment(private val userName:String,private val user:User) : Fragment() {
    private var _binding:FragmentDoneBinding?=null
    private val binding get() = _binding!!
    @Inject
    lateinit var task: Task
    lateinit var taskAdapter: TaskAdapter
    private val viewModel: UserTaskViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentDoneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskAdapter=TaskAdapter(::onItemClicked)

        viewModel.getTasks(userName,State.DONE)
        viewModel.taskLiveData.observe(viewLifecycleOwner){tasks->
            taskAdapter.differ.submitList(tasks)

        }
        binding.fabDone.setOnClickListener {
            val action=TaskMainFragmentDirections.actionTaskMainFragmentToTaskDialog(State.DONE,userName,user)
            view.findNavController().navigate(action)

        }
        binding.rvDone.apply {
            layoutManager= LinearLayoutManager(requireContext())
            adapter=taskAdapter
        }
        binding.searchDone.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(str: String?): Boolean {
                if (str?.length!! >0){
                    viewModel.searchTask(userName, str)
                }else{
                    viewModel.getTasks(userName,State.DONE)
                }

                return true
            }

        })

    }

    private fun onItemClicked(task: Task){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}