package com.davedecastro.cartrackexam.data.db.converters

import androidx.room.TypeConverter
import com.davedecastro.cartrackexam.data.db.entities.Address
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddressConverter {
    @TypeConverter
    fun addressToString(value: Address): String {
        val gson = Gson()
        val type = object : TypeToken<Address>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun stringToAddress(value: String): Address {
        val gson = Gson()
        val type = object : TypeToken<Address>() {}.type
        return gson.fromJson(value, type)
    }
}