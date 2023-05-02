package com.example.maktabhw18_1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.maktabhw18_1.data.Task
import com.example.maktabhw18_1.data.User
import com.example.maktabhw18_1.data.UserWithTasks
import com.example.maktabhw18_1.repository.UserTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserTaskViewModel @Inject constructor(private val userTaskRepository: UserTaskRepository) : ViewModel() {
   private val _userListLiveData = MutableLiveData<List<User>>()
   val userListLiveData: LiveData<List<User>> = _userListLiveData

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

    fun deleteTask(task: Task)=viewModelScope.launch {
        userTaskRepository.deleteTask(task)
    }



    fun saveTask(task: Task)=viewModelScope.launch {
        userTaskRepository.saveTask(task)
    }

    fun getUsers()=viewModelScope.launch {
        userTaskRepository.getUsers().collect{
            _userListLiveData.value=it
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
    fun updateUser(user: User)=viewModelScope.launch {
        userTaskRepository.updateUser(user)
    }

    fun deleteUser(user:User)=viewModelScope.launch {
        userTaskRepository.deleteUser(user)

    }

    fun getTasksForAdmin(userName:String)=viewModelScope.launch {
        userTaskRepository.getTasksForAdmin(userName).collect{
            _taskLiveData.value=it
        }
    }

    fun deleteTasksUser(userName: String)=viewModelScope.launch {
        userTaskRepository.deleteTasksUser(userName)
    }

    fun loginUser(userName:String,password:String){

        viewModelScope.launch {
            userTaskRepository.getUsers().collect{users->
               users.forEach { user->
                   if (user.userName == userName && user.password==password){
                       _existUserLiveData.postValue(true)
                       _getUserLiveData.value=user
                       return@collect

                   }else{
                       _existUserLiveData.postValue(false)
                   }

               }
            }
        }

    }
}