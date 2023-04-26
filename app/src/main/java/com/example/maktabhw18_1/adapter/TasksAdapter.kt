package com.example.maktabhw18_1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.maktabhw18_1.data.Task
import com.example.maktabhw18_1.data.UserWithTasks
import com.example.maktabhw18_1.databinding.ItemTaskBinding

class TasksAdapter ( private val onItemClicked:(Task)->Unit) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    private lateinit var binding: ItemTaskBinding
    inner class ViewHolder: RecyclerView.ViewHolder(binding.root){
//        fun setData(task: Task){
//            //binding.txtChar.text=task.title.substring(0,1)
//            binding.txtTitle.text=task.title
//            binding.txtDescription.text=task.description
//            binding.img.load(task.img){
//                crossfade(true)
//                transformations(CircleCropTransformation())
//            }
//
//
//
//            binding.root.setOnClickListener {
//                onItemClicked(task)
//
//            }
//        }
        fun setData(userWithTasks: UserWithTasks, position: Int){
            binding.txtTitle.text= userWithTasks.tasks[position].title
            binding.txtDescription.text=userWithTasks.tasks[position].description
            binding.img.load(userWithTasks.tasks[position].img){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
           binding.root.setOnClickListener {
               onItemClicked(userWithTasks.tasks[position])
           }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.setData(differ.currentList[position])
        holder.setData(differ.currentList[position],position)
        holder.setIsRecyclable(true)



    }

    override fun getItemCount() = differ.currentList.size


    private val differCallback= object : DiffUtil.ItemCallback<UserWithTasks>(){
        override fun areItemsTheSame(oldItem: UserWithTasks, newItem: UserWithTasks): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: UserWithTasks, newItem: UserWithTasks): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,differCallback)
}