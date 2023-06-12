package edu.cmich.locationnotes_reminders.notes.activities

import App.MainApp
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import edu.cmich.locationnotes_reminders.activities.MainActivity
import edu.cmich.locationnotes_reminders.R
import edu.cmich.locationnotes_reminders.notes.models.NoteListener
import edu.cmich.locationnotes_reminders.notes.models.NoteModel
import edu.cmich.locationnotes_reminders.reminders.activities.ReminderListActivity
import kotlinx.android.synthetic.main.note_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult

class NoteListActivity : AppCompatActivity(), NoteListener, NavigationView.OnNavigationItemSelectedListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_list)
        app = application as MainApp

        note_list_toolbar.title = "Notes"
        setSupportActionBar(note_list_toolbar)

        val layoutManager = LinearLayoutManager(this)
        note_list_recyclerView.layoutManager = layoutManager
        loadNotes()
        init()

        val fab: View = findViewById(R.id.note_fab)
        fab.setOnClickListener { _ ->
            startActivityForResult<NoteActivity>(0)
        }
    }

    private fun init(){
        val toggle = ActionBarDrawerToggle(Activity(), note_list, note_list_toolbar, R.string.nav_open, R.string.nav_closed)
        note_list.addDrawerListener(toggle)
        toggle.syncState()
        note_navigationView.setNavigationItemSelectedListener(this)
    }

    private fun loadNotes() {
        showNotes(app.notes.findAll())
    }

    fun showNotes (notes: List<NoteModel>) {
        note_list_recyclerView.adapter = NoteAdapter(notes, this)
        note_list_recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.home -> {
                startActivity(intentFor<MainActivity>())
            }
            R.id.reminders ->{
                startActivity(intentFor<ReminderListActivity>())
            }
        }
        note_list.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onNoteClick(note: NoteModel) {
        startActivityForResult(intentFor<NoteActivity>().putExtra("edit_note", note), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadNotes()
        super.onActivityResult(requestCode, resultCode, data)
    }
}