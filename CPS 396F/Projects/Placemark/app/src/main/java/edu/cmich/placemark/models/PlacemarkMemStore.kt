package edu.cmich.placemark.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PlacemarkMemStore : PlacemarkStore, AnkoLogger {

    val placemarks = ArrayList<PlacemarkModel>()

    override fun findAll(): List<PlacemarkModel> {
        return placemarks
    }

    override fun create(placemark: PlacemarkModel) {
        placemarks.add(placemark)
        logAll()
    }

    fun logAll() {
        placemarks.forEach{ info("${it}") }
    }

    override fun update(placemark: PlacemarkModel) {
        var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == placemark.id }
        if (foundPlacemark != null) {
            foundPlacemark.title = placemark.title
            foundPlacemark.description = placemark.description
            logAll()
        }
    }


}