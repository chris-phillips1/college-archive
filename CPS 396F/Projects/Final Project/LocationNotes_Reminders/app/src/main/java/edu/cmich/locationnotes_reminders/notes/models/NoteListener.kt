package edu.cmich.locationnotes_reminders.notes.models

interface NoteListener {
    fun onNoteClick(note: NoteModel)
}
