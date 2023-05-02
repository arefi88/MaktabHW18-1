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

class TaskAdapter ( private val onItemClicked:(Task)->Unit,private val onItemDeleteClicked:(Task)->Unit) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private lateinit var binding:ItemTaskBinding
    inner class ViewHolder: RecyclerView.ViewHolder(binding.root){
        fun setData(task: Task){
            //binding.txtChar.text=task.title.substring(0,1)
            binding.txtTitle.text=task.title
            binding.txtDescription.text=task.description
            binding.txtDate.text=task.date
            binding.txtTime.text=task.time
            binding.img.load(task.img){
                crossfade(true)
                transformations(CircleCropTransformation())
            }

            binding.btnShare.setOnClickListener {
                onItemClicked(task)
            }

            binding.root.setOnClickListener {
                onItemDeleteClicked(task)

            }
        }
        fun set(userWithTasks: UserWithTasks,position: Int){
            binding.txtTitle.text= userWithTasks.tasks[position].title
            binding.txtDescription.text=userWithTasks.tasks[position].description
            binding.img.load(userWithTasks.tasks[position].img){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding=ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(true)



    }

    override fun getItemCount() = differ.currentList.size


    private val differCallback= object : DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,differCallback)
}