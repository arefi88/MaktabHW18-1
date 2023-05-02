package com.example.maktabhw18_1.data

import androidx.room.Embedded
import androidx.room.Relation


data class UserWithTasks(
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val tasks:List<Task>
)
