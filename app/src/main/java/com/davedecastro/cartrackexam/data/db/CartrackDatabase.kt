package com.davedecastro.cartrackexam.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.davedecastro.cartrackexam.data.db.converters.AddressConverter
import com.davedecastro.cartrackexam.data.db.converters.CompanyConverter
import com.davedecastro.cartrackexam.data.db.converters.GeoConverter
import com.davedecastro.cartrackexam.data.db.daos.AccountDao
import com.davedecastro.cartrackexam.data.db.daos.UserDao
import com.davedecastro.cartrackexam.data.db.entities.Account
import com.davedecastro.cartrackexam.data.db.entities.User
import com.davedecastro.cartrackexam.data.network.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Database(
    version = 1,
    entities = [
        Account::class,
        User::class
    ]
)
@TypeConverters(CompanyConverter::class, AddressConverter::class, GeoConverter::class)
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

    abstract fun accountDao(): AccountDao
    abstract fun userDao(): UserDao
}