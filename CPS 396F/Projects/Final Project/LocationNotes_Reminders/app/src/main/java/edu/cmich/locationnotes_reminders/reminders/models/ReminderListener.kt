package edu.cmich.locationnotes_reminders.reminders.models

interface ReminderListener {
    fun onReminderClick(reminder: ReminderModel)

}