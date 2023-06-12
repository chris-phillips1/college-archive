package edu.cmich.locationnotes_reminders.reminders.activities

import App.MainApp
import edu.cmich.locationnotes_reminders.notes.activities.NoteListActivity
import edu.cmich.locationnotes_reminders.reminders.models.ReminderModel
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build

import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import edu.cmich.locationnotes_reminders.activities.MainActivity
import edu.cmich.locationnotes_reminders.R
import edu.cmich.locationnotes_reminders.models.Location
import edu.cmich.locationnotes_reminders.reminders.models.ReminderListener
import kotlinx.android.synthetic.main.reminder_list.*
import kotlinx.android.synthetic.main.reminder_card.view.*

import org.jetbrains.anko.intentFor

import org.jetbrains.anko.startActivityForResult



val PERMISSION_ID = 42
val CHANNEL_ID = "ID"
class ReminderListActivity : AppCompatActivity(),
    ReminderListener, NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var app: MainApp
    var loc = Location()

    lateinit var mFusedLocationClient: FusedLocationProviderClient



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reminder_list)
//        loc.lat = 37.422
//        loc.lng = -122.084
        app = application as MainApp
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //loc.zoom = 15f
        toolbar.title = "Reminders"
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadReminders()
        init()

        val fab: View = findViewById(R.id.reminder_fab)
        fab.setOnClickListener { _ ->
            startActivityForResult<ReminderActivity>(0)
        }
    }
  override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.home -> {
                startActivity(intentFor<MainActivity>())

                return true
            }
            R.id.notes -> {
                startActivity(intentFor<NoteListActivity>())
                return true
            }
        }
        reminder_list.closeDrawer(GravityCompat.START)
        return true
   }

    private fun init(){
        val toggle = ActionBarDrawerToggle(Activity(), reminder_list, toolbar, R.string.nav_open, R.string.nav_closed)
        reminder_list.addDrawerListener(toggle)
        toggle.syncState()
        navigation_view2.setNavigationItemSelectedListener (this)
    }

    override fun onReminderClick(reminder: ReminderModel) {
        startActivityForResult(intentFor<ReminderActivity>().putExtra("reminder_edit", reminder), 0)
       // startActivityForResult(intentFor<MainActivity>(), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadReminders()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadReminders(){

        showReminders(app.reminders.findAll())
    }

    fun showReminders(reminders: List<ReminderModel>){
        recyclerView.adapter =
            ReminderAdapter(reminders, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

}


