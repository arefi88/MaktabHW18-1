package com.example.maktabhw18_1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.maktabhw18_1.Converters

@Database(entities = [User::class,Task::class], version = 8, exportSchema = true)
@TypeConverters(Converters::class)
abstract class UserTaskDatabase:RoomDatabase() {
    abstract fun getDao():UserTaskDao
}