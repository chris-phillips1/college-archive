package edu.cmich.locationnotes_reminders.notes.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import edu.cmich.locationnotes_reminders.R
import edu.cmich.locationnotes_reminders.notes.models.NoteListener
import edu.cmich.locationnotes_reminders.notes.models.NoteModel
import kotlinx.android.synthetic.main.note_card.view.*

class NoteAdapter constructor(private var notes: List<NoteModel>,
                              private val listener: NoteListener
) :
    RecyclerView.Adapter<NoteAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.note_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val note = notes[holder.adapterPosition]
        holder.bind(note, listener)
    }

    override fun getItemCount(): Int = notes.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: NoteModel, listener: NoteListener) {
            itemView.noteTitleCard.text = note.title
            itemView.noteTextCard.text = note.text
            itemView.noteLocationCard.text = "Location: " + note.lat + ", " + note.lng
            itemView.setOnClickListener{ listener.onNoteClick(note) }
        }
    }
}