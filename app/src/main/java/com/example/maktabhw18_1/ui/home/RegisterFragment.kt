package com.example.maktabhw18_1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.maktabhw18_1.R
import com.example.maktabhw18_1.UserTaskViewModel
import com.example.maktabhw18_1.data.User
import com.example.maktabhw18_1.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!

    private val viewModel:UserTaskViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {

            val user=User(userName = binding.edtUsernameRegister.text.toString()
                , email =binding.edtEmailRegister.text.toString()
                , password =binding.edtPasswordRegister.text.toString())
            //val id=user.id
            viewModel.saveUser(user)
            val action=RegisterFragmentDirections.actionRegisterFragmentToTaskMainFragment(binding.edtUsernameRegister.text.toString())
            view.findNavController().navigate(action)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}