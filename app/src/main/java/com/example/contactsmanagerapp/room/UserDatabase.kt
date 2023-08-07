package com.example.contactsmanagerapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1 )
abstract class UserDatabase: RoomDatabase() {

    abstract val userDAO: UserDAO

    //Singleton
    //Reduce memory leaks, time.
    //Design pattern that is used mainly in databases
    //and connectivity that requires only one object during the
    //runtime of a class.(only one object do all function)
    companion object {
        //Makes it immediately visible to other threads.
        @Volatile
        private var INSTANCE: UserDatabase? = null
        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    //Creating the database object.
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "users_db"
                    ).build()
                }
                return instance
            }
        }
    }
}