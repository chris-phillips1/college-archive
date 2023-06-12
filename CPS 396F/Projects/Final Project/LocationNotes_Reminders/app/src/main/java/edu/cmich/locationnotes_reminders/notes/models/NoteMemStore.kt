package edu.cmich.locationnotes_reminders.notes.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class NoteMemStore : NoteStore, AnkoLogger{
    val notes = ArrayList<NoteModel>()

    override fun findAll(): List<NoteModel> {
        return notes
    }

    override fun create(note: NoteModel) {
        notes.add(note)
        logAll()
    }

    override fun update(note: NoteModel) {
        var foundNote: NoteModel? = notes.find { p -> p.id == note.id }
        if (foundNote != null) {
            foundNote.title = note.title
            foundNote.text = note.text
            foundNote.lat = note.lat
            foundNote.lng = note.lng
            foundNote.zoom = note.zoom
            logAll()
        }
    }

    override fun delete(note: NoteModel) {
        notes.remove(note)
    }

    private fun logAll() {
        notes.forEach{ info("$it") }
    }
}