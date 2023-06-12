package edu.cmich.locationnotes_reminders.reminders.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import edu.cmich.locationnotes_reminders.helpers.exists
import edu.cmich.locationnotes_reminders.helpers.read
import edu.cmich.locationnotes_reminders.helpers.write
import org.jetbrains.anko.AnkoLogger

import java.util.*


val JSON_FILE = "reminders.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<ReminderModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}


class ReminderJSONStore: ReminderStore, AnkoLogger {
    val context: Context
    var reminders = mutableListOf<ReminderModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ReminderModel> {
        return reminders
    }

    override fun create(reminder: ReminderModel) {
        reminder.id = generateRandomId()
        reminders.add(reminder)
        serialize()
    }


    override fun update(reminder: ReminderModel) {
        var foundReminder: ReminderModel? = reminders.find { r -> r.id == reminder.id }
        if (foundReminder != null) {
            foundReminder.title = reminder.title
            foundReminder.dscp = reminder.dscp
            foundReminder.date = Date()
            foundReminder.lat = reminder.lat
            foundReminder.lng = reminder.lng
        }
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(reminders,
            listType
        )
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        reminders = Gson().fromJson(jsonString,
            listType
        )
    }

    override fun delete(reminder: ReminderModel) {
        var foundReminder: ReminderModel? = reminders.find { r -> r.id == reminder.id }
        if(foundReminder != null)
            reminders.remove(foundReminder)
        serialize()
    }
}