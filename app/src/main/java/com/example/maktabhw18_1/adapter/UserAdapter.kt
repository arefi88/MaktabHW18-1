package com.example.maktabhw18_1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.maktabhw18_1.data.Task
import com.example.maktabhw18_1.data.User
import com.example.maktabhw18_1.data.UserWithTasks
import com.example.maktabhw18_1.databinding.ItemTaskBinding
import com.example.maktabhw18_1.databinding.ItemUserBinding

class UserAdapter ( private val onItemClicked:(User)->Unit) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var binding: ItemUserBinding
    inner class ViewHolder: RecyclerView.ViewHolder(binding.root){
        fun setData(user: User){
            binding.txtNumberTask.text=user.numberTasks.toString()
            binding.txtRegisterData.text=user.registerDate
            binding.txtUsername.text=user.userName
            binding.root.setOnClickListener {
                onItemClicked(user)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(true)



    }

    override fun getItemCount() = differ.currentList.size


    private val differCallback= object : DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,differCallback)
}