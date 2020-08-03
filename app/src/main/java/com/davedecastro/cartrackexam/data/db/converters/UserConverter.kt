package com.davedecastro.cartrackexam.data.db.converters

import androidx.room.TypeConverter
import com.davedecastro.cartrackexam.data.db.entities.User
import com.davedecastro.cartrackexam.utils.fromJson
import com.davedecastro.cartrackexam.utils.toJson

class UserConverter {
    @TypeConverter
    fun stringToUser(json: String?): List<User> = json?.fromJson() ?: listOf()

    @TypeConverter
    fun userToString(list: List<User?>?): String = list.toJson()
}