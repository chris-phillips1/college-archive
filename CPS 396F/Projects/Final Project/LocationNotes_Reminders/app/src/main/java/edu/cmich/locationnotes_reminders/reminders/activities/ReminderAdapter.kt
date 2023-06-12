package edu.cmich.locationnotes_reminders.reminders.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.cmich.locationnotes_reminders.R
import edu.cmich.locationnotes_reminders.reminders.models.ReminderListener
import edu.cmich.locationnotes_reminders.reminders.models.ReminderModel
import kotlinx.android.synthetic.main.reminder_card.view.*

class ReminderAdapter constructor(private var reminders: List<ReminderModel>,
                                  private val listener: ReminderListener
) :
    RecyclerView.Adapter<ReminderAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.reminder_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val reminder = reminders[holder.adapterPosition]
        holder.bind(reminder, listener)
    }

    override fun getItemCount(): Int = reminders.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(reminder: ReminderModel, listener: ReminderListener) {
            itemView.reminderTitleCard.text = reminder.title
            itemView.reminderDscpCard.text = reminder.dscp

            itemView.reminderLocationCard.text = "Location: ${reminder.lat}, ${reminder.lng}"
            itemView.setOnClickListener{listener.onReminderClick(reminder)}

        }
    }
}