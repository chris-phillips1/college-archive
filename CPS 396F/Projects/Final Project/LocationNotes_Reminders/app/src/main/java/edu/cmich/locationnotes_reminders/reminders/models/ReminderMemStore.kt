package edu.cmich.locationnotes_reminders.reminders.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*
import kotlin.collections.ArrayList

var lastId: Long = 0

internal fun getId(): Long{
    return lastId++
}

class ReminderMemStore: ReminderStore, AnkoLogger {

    val reminders = ArrayList<ReminderModel>()

    override fun findAll(): List<ReminderModel> {
        return reminders
    }

    override fun create(reminder: ReminderModel) {
        reminders.add(reminder)
        logAll()
    }

    fun logAll(){
        reminders.forEach{info("$it")}
    }

    override fun update(reminder: ReminderModel) {
        var foundReminder: ReminderModel? = reminders.find { r -> r.id == reminder.id }
        if (foundReminder != null) {
            foundReminder.title = reminder.title
            foundReminder.dscp = reminder.dscp
            foundReminder.date = Date()
            foundReminder.lat = reminder.lat
            foundReminder.lng = reminder.lng
            logAll()
        }
    }

    override fun delete(reminder: ReminderModel){
//        var foundReminder: ReminderModel? = reminders.find { r -> r.id == reminder.id }
//        if(foundReminder != null)
            reminders.remove(reminder)
        }


}