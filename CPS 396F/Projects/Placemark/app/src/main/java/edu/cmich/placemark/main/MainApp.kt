package edu.cmich.placemark.main

import android.app.Application
import edu.cmich.placemark.models.PlacemarkMemStore
import edu.cmich.placemark.models.PlacemarkModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {

    val placemarks = PlacemarkMemStore()

    override fun onCreate() {
        super.onCreate()
        info("Placemark started")
    }
}