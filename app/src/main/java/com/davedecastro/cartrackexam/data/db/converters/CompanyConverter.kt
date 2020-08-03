package com.davedecastro.cartrackexam.data.db.converters

import androidx.room.TypeConverter
import com.davedecastro.cartrackexam.data.db.entities.Company
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CompanyConverter {
    @TypeConverter
    fun fromGroupTaskMemberList(value: Company): String {
        val gson = Gson()
        val type = object : TypeToken<Company>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): Company {
        val gson = Gson()
        val type = object : TypeToken<Company>() {}.type
        return gson.fromJson(value, type)
    }
}