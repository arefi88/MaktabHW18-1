package com.example.maktabhw18_1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.maktabhw18_1.R
import com.example.maktabhw18_1.State
import com.example.maktabhw18_1.UserTaskViewModel
import com.example.maktabhw18_1.adapter.TaskAdapter
import com.example.maktabhw18_1.adapter.TasksAdapter
import com.example.maktabhw18_1.data.Task
import com.example.maktabhw18_1.data.User
import com.example.maktabhw18_1.databinding.FragmentTodoBinding
import com.example.maktabhw18_1.dialog.TaskDialog
import com.example.maktabhw18_1.dialog.TaskDialogArgs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TodoFragment(private val userName:String,private val user:User) : Fragment() {
    @Inject
    lateinit var task: Task
    lateinit var taskAdapter: TaskAdapter
    private var _binding:FragmentTodoBinding?=null
    private val viewModel:UserTaskViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentTodoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskAdapter=TaskAdapter(::onItemClicked)

        viewModel.getTasks(userName,State.TODO)
        viewModel.taskLiveData.observe(viewLifecycleOwner){tasks->
          taskAdapter.differ.submitList(tasks)

        }

        binding.searchTodo.setOnQueryTextListener(object :OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(str: String?): Boolean {
                if (str?.length!! >0){
                    viewModel.searchTask(userName, str)
                }else{
                    viewModel.getTasks(userName,State.TODO)
                }

                return true
            }

        })


        binding.fabTodo.setOnClickListener {
            val action=TaskMainFragmentDirections.actionTaskMainFragmentToTaskDialog(State.TODO,userName,user)
            view.findNavController().navigate(action)

        }
        binding.rvTodo.apply {
            layoutManager=LinearLayoutManager(requireContext())
            adapter=taskAdapter
        }
    }

    private fun onItemClicked(task: Task){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}