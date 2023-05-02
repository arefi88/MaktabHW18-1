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
    suspend fun updateUser(user: User)=userTaskDao.updateUser(user)
    suspend fun deleteUser(user: User)=userTaskDao.deleteUser(user)
    suspend fun deleteTask(task: Task)=userTaskDao.deleteTask(task)
    fun getTasksForAdmin(userName:String)=userTaskDao.getTasksForAdmin(userName)
    suspend fun deleteTasksUser(userName: String)=userTaskDao.deleteTasksUser(userName)
}