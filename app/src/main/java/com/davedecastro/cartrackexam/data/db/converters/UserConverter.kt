package com.davedecastro.cartrackexam.data.db.converters

import androidx.room.TypeConverter
import com.davedecastro.cartrackexam.data.db.entities.User
import com.davedecastro.cartrackexam.utils.fromJson
import com.davedecastro.cartrackexam.utils.toJson

class UserConverter {
    @TypeConverter
    fun stringToMeasurements(json: String?): List<User> = json?.fromJson() ?: listOf()

    @TypeConverter
    fun measurementsToString(list: List<User?>?): String = list.toJson()
}