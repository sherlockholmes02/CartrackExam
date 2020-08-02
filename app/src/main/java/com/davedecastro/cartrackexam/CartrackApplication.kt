package com.davedecastro.cartrackexam

import android.app.Application
import com.davedecastro.cartrackexam.data.db.CartrackDatabase

class CartrackApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CartrackDatabase.init(this)
    }
}