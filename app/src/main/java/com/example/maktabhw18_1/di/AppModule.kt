package com.example.maktabhw18_1.di

import android.content.Context
import androidx.room.Room
import com.example.maktabhw18_1.USER_TASK_DATABASE
import com.example.maktabhw18_1.data.Task
import com.example.maktabhw18_1.data.UserTaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,UserTaskDatabase::class.java,
        USER_TASK_DATABASE
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db:UserTaskDatabase)=db.getDao()

    @Provides
    @Singleton
    fun provideTask()=Task()
}