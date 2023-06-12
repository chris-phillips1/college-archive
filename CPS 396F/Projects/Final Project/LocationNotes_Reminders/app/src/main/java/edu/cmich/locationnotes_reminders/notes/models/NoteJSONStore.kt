package edu.cmich.locationnotes_reminders.notes.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import edu.cmich.locationnotes_reminders.helpers.exists
import edu.cmich.locationnotes_reminders.helpers.read
import edu.cmich.locationnotes_reminders.helpers.write
import org.jetbrains.anko.AnkoLogger
import java.util.*

val JSON_FILE = "notes.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<NoteModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class NoteJSONStore : NoteStore, AnkoLogger {

    val context: Context
    var notes
            = mutableListOf<NoteModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<NoteModel> {
        return notes
    }

    override fun create(note: NoteModel) {
        note.id = generateRandomId()
        notes.add(note)
        serialize()
    }


    override fun update(note: NoteModel) {
        var foundNote: NoteModel? = notes.find { r -> r.id == note.id }
        if (foundNote != null) {
            foundNote.title = note.title
            foundNote.text = note.text
            foundNote.lat = note.lat
            foundNote.lng = note.lng
        }
        serialize()
    }

    override fun delete(note: NoteModel) {
        notes.remove(note)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(notes, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        notes = Gson().fromJson(jsonString, listType)
    }
}