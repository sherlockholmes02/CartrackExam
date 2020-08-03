package com.davedecastro.cartrackexam.data.db.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Geo(
    @SerializedName("lat")
    var latitude: Double,
    @SerializedName("lng")
    var longitude: Double
): Parcelable