package com.example.maktabhw18_1.repository

import com.example.maktabhw18_1.State
import com.example.maktabhw18_1.data.Task
import com.example.maktabhw18_1.data.User
import com.example.maktabhw18_1.data.UserTaskDao
import javax.inject.Inject

class UserTaskRepository @Inject constructor(private val userTaskDao: UserTaskDao) {
    suspend fun saveTask(task: Task)=userTaskDao.insertTask(task)
    suspend fun saveUser(user: User)=userTaskDao.insertUser(user)
    fun getTasks(userName:String,state: State)=userTaskDao.getTasks(userName,state)
    fun getUsers()=userTaskDao.getUsers()
    fun searchTask(userName:String,str: String)=userTaskDao.searchTask(userName,str)
    fun getUser(id:Int)=userTaskDao.getUser(id)
    //fun getUserWithTasks()=userTaskDao.getUserWithTasks()
}