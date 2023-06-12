package edu.cmich.locationnotes_reminders.reminders.models

import edu.cmich.locationnotes_reminders.reminders.models.ReminderModel

interface ReminderStore {
    fun findAll(): List<ReminderModel>
    fun create(reminder: ReminderModel)
    fun update(reminder: ReminderModel)
    fun delete(reminder: ReminderModel)
}