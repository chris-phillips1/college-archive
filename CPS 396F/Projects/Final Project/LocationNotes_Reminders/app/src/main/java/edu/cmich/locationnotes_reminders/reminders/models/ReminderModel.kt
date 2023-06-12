package edu.cmich.locationnotes_reminders.reminders.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class ReminderModel (
    var id: Long = 0,
    var date: Date? = null,
    var title: String = "",
    var dscp: String = "",
    var lat: Double = 0.0,
    var lng: Double = 0.0,
    var zoom: Float = 15f) : Parcelable
