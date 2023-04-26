package com.example.maktabhw18_1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.maktabhw18_1.R
import com.example.maktabhw18_1.UserTaskViewModel
import com.example.maktabhw18_1.databinding.FragmentLoginBinding
import com.example.maktabhw18_1.ui.TaskMainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding:FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private val viewModel:UserTaskViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.existUserLiveData.observe(viewLifecycleOwner){
            if (it){
                val action=LoginFragmentDirections.actionLoginFragmentToTaskMainFragment(binding.edtUsername.text.toString())
                view.findNavController().navigate(action)

            }else{
                Toast.makeText(requireActivity(),"user not found!",Toast.LENGTH_SHORT).show()
            }

        }
        binding.btnLogin.setOnClickListener {
            viewModel.loginUser(
                binding.edtUsername.text.toString(),
                binding.edtPassword.text.toString()
            )

        }
        binding.txtRegister.setOnClickListener {
           view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}