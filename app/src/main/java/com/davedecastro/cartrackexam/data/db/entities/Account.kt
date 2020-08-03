package com.davedecastro.cartrackexam.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account constructor(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var username: String,
    var password: String
)