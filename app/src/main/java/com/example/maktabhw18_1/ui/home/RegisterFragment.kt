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
import com.example.maktabhw18_1.dialog.DatePickerFragment
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

        binding.edtRegisterDate.setOnClickListener {

                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager =activity?.supportFragmentManager
                supportFragmentManager?.setFragmentResultListener(
                    "REQUEST_KEY",
                    requireActivity()
                ){resultkey,bundle->
                    if (resultkey=="REQUEST_KEY"){
                        val date=bundle.getString("SELECTED_DATE")
                        binding.edtRegisterDate.text = date
                    }

                }
                if (supportFragmentManager != null) {
                    datePickerFragment.show(supportFragmentManager,"")
                }

        }

        binding.btnRegister.setOnClickListener {

            val user=User(userName = binding.edtUsernameRegister.text.toString()
                , email =binding.edtEmailRegister.text.toString()
                , password =binding.edtPasswordRegister.text.toString()
            , registerDate = binding.edtRegisterDate.text.toString())
            //val id=user.id
            viewModel.saveUser(user)

            //val action=RegisterFragmentDirections.actionRegisterFragmentToTaskMainFragment(binding.edtUsernameRegister.text.toString(),user)
            val bundle=Bundle()
            bundle.putString("userName",binding.edtUsernameRegister.text.toString())
            bundle.putString("password",binding.edtPasswordRegister.text.toString())
            //val action=RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment,bundle)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}