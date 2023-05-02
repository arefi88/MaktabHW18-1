package com.example.maktabhw18_1.data

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.maktabhw18_1.State

@Entity(tableName = "task_tbl")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var userName:String?="",
    var title:String="",
    var description:String="",
    var date:String="",
    var time:String="",
    var img:Bitmap?=null,
    var state:State?=null
){

}
