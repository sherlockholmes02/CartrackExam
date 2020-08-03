package com.davedecastro.cartrackexam.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

suspend fun isServerReachable() = withContext(Dispatchers.IO) {
    try {
        val connection = URL("https://jsonplaceholder.typicode.com/")
            .openConnection()
        connection.connect()
        connection.getInputStream().close()
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}