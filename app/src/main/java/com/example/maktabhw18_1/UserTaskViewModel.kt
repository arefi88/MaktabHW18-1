package com.example.maktabhw18_1

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabhw18_1.data.Task
import com.example.maktabhw18_1.data.User
import com.example.maktabhw18_1.data.UserWithTasks
import com.example.maktabhw18_1.repository.UserTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserTaskViewModel @Inject constructor(private val userTaskRepository: UserTaskRepository) : ViewModel() {
   private val _userLiveData = MutableLiveData<List<User>>()
   val userLiveData: LiveData<List<User>> = _userLiveData

    private val _taskLiveData = MutableLiveData<List<Task>>()
    val taskLiveData: LiveData<List<Task>> = _taskLiveData

    private val _tasksWithUserName = MutableLiveData<List<UserWithTasks>>()
    val tasksWithUserName: LiveData<List<UserWithTasks>> = _tasksWithUserName

    private val _getUserLiveData = MutableLiveData<User>()
    val getUserLiveData: LiveData<User> = _getUserLiveData

    private val _existUserLiveData = MutableLiveData<Boolean>()
    val existUserLiveData: LiveData<Boolean> = _existUserLiveData





    fun saveUser(user: User)=viewModelScope.launch {
        userTaskRepository.saveUser(user)

    }



    fun saveTask(task: Task)=viewModelScope.launch {
        userTaskRepository.saveTask(task)
    }

    fun getUsers()=viewModelScope.launch {
        userTaskRepository.getUsers().collect{
            _userLiveData.value=it
        }
    }
    fun getTasks(userName: String,state: State)=viewModelScope.launch {
        userTaskRepository.getTasks(userName,state).collect{
            _taskLiveData.value=it
        }
    }
    fun searchTask(userName:String,str: String)=viewModelScope.launch {
        userTaskRepository.searchTask(userName, str).collect{
            _taskLiveData.value=it
        }
    }
    fun getUser(id:Int)=viewModelScope.launch {
        userTaskRepository.getUser(id).collect{
            _getUserLiveData.value=it
        }
    }

    fun getUserWithTasks()=viewModelScope.launch {
           // _tasksWithUserName.value=userTaskRepository.getUserWithTasks()
    }

    fun loginUser(userName:String,password:String){

        viewModelScope.launch {
            userTaskRepository.getUsers().collect{users->
               users.forEach { user->
                   if (user.userName == userName && user.password==password){
                       _existUserLiveData.postValue(true)
                       return@collect

                   }else{
                       _existUserLiveData.postValue(false)
                   }

               }
            }
        }

    }
}