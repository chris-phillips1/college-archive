package edu.cmich.locationnotes_reminders.notes.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NoteModel(var id: Long = 0,
                     var title: String = "",
                     var text: String = "",
                     var lat: Double = 0.0,
                     var lng: Double = 0.0,
                     var zoom: Float = 0f) : Parcelable