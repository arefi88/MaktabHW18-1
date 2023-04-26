package com.example.maktabhw18_1.data

import androidx.room.*
import com.example.maktabhw18_1.State
import kotlinx.coroutines.flow.Flow

@Dao
interface UserTaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user_tbl")
    fun getUsers():Flow<List<User>>

    @Query("SELECT * FROM task_tbl WHERE userName == :userName AND state == :state")
    fun getTasks(userName:String,state:State):Flow<List<Task>>

    @Query("SELECT * FROM task_tbl WHERE userName == :userName AND description LIKE '%' || :str || '%' ")
    fun searchTask(userName:String,str: String):Flow<List<Task>>

    @Query("SELECT * FROM user_tbl WHERE id == :id")
    fun getUser(id:Int):Flow<User>

//    @Transaction
//    @Query("SELECT * FROM user_tbl ")
//    fun getUserWithTasks():List<UserWithTasks>
}