package App

import android.app.Application
import edu.cmich.locationnotes_reminders.notes.models.NoteJSONStore
import edu.cmich.locationnotes_reminders.reminders.models.ReminderStore
import edu.cmich.locationnotes_reminders.notes.models.NoteStore
import edu.cmich.locationnotes_reminders.reminders.models.ReminderJSONStore

class MainApp: Application() {

    lateinit var reminders: ReminderStore
    lateinit var  notes: NoteStore

    override fun onCreate() {
        super.onCreate()
        reminders = ReminderJSONStore(applicationContext)
        notes = NoteJSONStore(applicationContext)
    }
}