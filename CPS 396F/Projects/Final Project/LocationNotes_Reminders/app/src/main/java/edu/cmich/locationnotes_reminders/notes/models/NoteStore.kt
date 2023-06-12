package edu.cmich.locationnotes_reminders.notes.models

interface NoteStore {
    fun findAll(): List<NoteModel>
    fun create(note: NoteModel)
    fun update(note: NoteModel)
    fun delete(note: NoteModel)
}