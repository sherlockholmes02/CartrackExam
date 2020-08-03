package com.davedecastro.cartrackexam.data.db.converters

import androidx.room.TypeConverter
import com.davedecastro.cartrackexam.data.db.entities.Geo
import com.davedecastro.cartrackexam.utils.fromJson
import com.davedecastro.cartrackexam.utils.toJson

class GeoConverter {
    @TypeConverter
    fun stringToMeasurements(json: String?): List<Geo> = json?.fromJson() ?: listOf()

    @TypeConverter
    fun measurementsToString(list: List<Geo?>?): String = list.toJson()
}