package com.example.contactsmanagerapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
//If entity name not given room will use class name as the main name.
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    //Without the specific name of the column it'll work using default name.
    val id: Int,

    @ColumnInfo(name = "user_name")
    var name: String,

    @ColumnInfo(name= "user_email")
    var email: String
)
