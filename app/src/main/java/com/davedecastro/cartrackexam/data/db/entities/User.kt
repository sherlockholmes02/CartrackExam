package com.davedecastro.cartrackexam.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var username: String,
    var password: String
)