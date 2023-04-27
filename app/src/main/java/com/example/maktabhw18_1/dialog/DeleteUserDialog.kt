package com.example.maktabhw18_1.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.maktabhw18_1.UserTaskViewModel
import com.example.maktabhw18_1.databinding.DialogUserDeleteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteUserDialog :DialogFragment() {
    private var _binding:DialogUserDeleteBinding?=null
    private val binding get() = _binding!!
    private val args:DeleteUserDialogArgs by navArgs()
    private val viewModel:UserTaskViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=DialogUserDeleteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDelete.setOnClickListener {
            viewModel.deleteUser(args.user)
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}