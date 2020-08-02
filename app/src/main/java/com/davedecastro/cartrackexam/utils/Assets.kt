package com.davedecastro.cartrackexam.utils

import android.content.Context
import com.davedecastro.cartrackexam.data.db.entities.Country
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.InputStream
import java.lang.Exception
import java.nio.charset.Charset

fun getCountriesFile(context: Context): List<Country> {
    val json: String? = try {
        val `is`: InputStream = context.assets.open("countries.json")
        val size: Int = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        String(buffer, Charset.defaultCharset())
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }

    return if (json == null) {
        listOf()
    } else {
        val gson = GsonBuilder().create()
        gson.fromJson(json, object : TypeToken<List<Country>>() {}.type)
    }
}