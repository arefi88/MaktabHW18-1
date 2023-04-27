package com.example.maktabhw18_1.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_tbl")
data class User(
    @PrimaryKey(autoGenerate = true)
   var id:Int=0,
   var userName:String,
   var email:String,
   var password:String,
    var numberTasks:Int=0,
  var registerDate:String=""
):java.io.Serializable{




}
