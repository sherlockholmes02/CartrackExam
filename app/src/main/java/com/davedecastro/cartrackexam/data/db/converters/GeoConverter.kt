package com.davedecastro.cartrackexam.data.db.converters

import androidx.room.TypeConverter
import com.davedecastro.cartrackexam.data.db.entities.Geo
import com.davedecastro.cartrackexam.utils.fromJson
import com.davedecastro.cartrackexam.utils.toJson

class GeoConverter {
    @TypeConverter
    fun stringToGeo(json: String?): List<Geo> = json?.fromJson() ?: listOf()

    @TypeConverter
    fun geoToString(list: List<Geo?>?): String = list.toJson()
}