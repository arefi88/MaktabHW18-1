package com.example.maktabhw18_1.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.maktabhw18_1.UserTaskViewModel
import com.example.maktabhw18_1.data.Task
import com.example.maktabhw18_1.data.User
import com.example.maktabhw18_1.databinding.DialogTaskBinding
import com.example.maktabhw18_1.databinding.LayoutDialogBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskDialog :DialogFragment() {
    private val REQUEST_CODE = 69
    val Fragment.packageManager get() = activity?.packageManager
    private lateinit var image:Bitmap
    @Inject
    lateinit var task: Task
    val args: TaskDialogArgs by navArgs()

    private var _binding:DialogTaskBinding?=null
    private val binding get() = _binding!!
    private val viewModel:UserTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=DialogTaskBinding.inflate(inflater,container,false)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCamera.setOnClickListener{
            val takePictureIntent =  Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, REQUEST_CODE)

        }
        binding.btnDate.setOnClickListener {
            val datePickerFragment = DatePickerFragment()
            val supportFragmentManager =activity?.supportFragmentManager
            supportFragmentManager?.setFragmentResultListener(
                "REQUEST_KEY",
                requireActivity()
            ){resultkey,bundle->
                if (resultkey=="REQUEST_KEY"){
                    val date=bundle.getString("SELECTED_DATE")
                    binding.btnDate.text=date
                }

            }
            if (supportFragmentManager != null) {
                datePickerFragment.show(supportFragmentManager,"")
            }
        }
        binding.btnTime.setOnClickListener {
            openTimePicker()
        }
        binding.btnSave.setOnClickListener {
            task.date=binding.btnDate.text.toString()
            task.time=binding.btnTime.text.toString()
            task.title=binding.edtTitle.text.toString()
            task.description=binding.edtDescription.text.toString()
            task.state=args.state
            task.userName=args.userName
            task.img=image
            args.user.numberTasks++
            viewModel.updateUser(args.user)
            viewModel.saveTask(task)
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==REQUEST_CODE && resultCode==Activity.RESULT_OK){
            image=data?.extras?.get("data") as Bitmap
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    private fun openTimePicker(){
        val isSystem24Hour= DateFormat.is24HourFormat(requireActivity())
        val clockFormat=if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(0).build()
        picker.show(childFragmentManager,"")
        picker.addOnPositiveButtonClickListener{
            binding.btnTime.text="${picker.hour}:${picker.minute}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}