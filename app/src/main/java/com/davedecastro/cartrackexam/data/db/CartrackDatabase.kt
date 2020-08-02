package com.davedecastro.cartrackexam.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.davedecastro.cartrackexam.data.db.daos.UserDao
import com.davedecastro.cartrackexam.data.db.entities.User

@Database(
    version = 1,
    entities = [
        User::class
    ]
)
abstract class CartrackDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private lateinit var INSTANCE: CartrackDatabase

        fun init(context: Context) {
            if (!::INSTANCE.isInitialized)
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    CartrackDatabase::class.java,
                    "cartrack_database"
                ).build()
        }

        fun getInstance() = INSTANCE
    }

    abstract fun userDao(): UserDao
}