package edu.cmich.locationnotes_reminders.activities

import App.MainApp
import android.Manifest
import android.annotation.SuppressLint
import edu.cmich.locationnotes_reminders.notes.activities.NoteListActivity
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper

import android.provider.Settings

import android.view.MenuItem

import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper

import com.google.android.gms.location.*

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import edu.cmich.locationnotes_reminders.R
import edu.cmich.locationnotes_reminders.models.Location
import edu.cmich.locationnotes_reminders.models.NotificationSender
import edu.cmich.locationnotes_reminders.notes.activities.NoteActivity

import edu.cmich.locationnotes_reminders.notes.models.NoteListener
import edu.cmich.locationnotes_reminders.notes.models.NoteModel

import edu.cmich.locationnotes_reminders.reminders.activities.PERMISSION_ID
import edu.cmich.locationnotes_reminders.reminders.activities.*
import edu.cmich.locationnotes_reminders.reminders.models.ReminderListener
import edu.cmich.locationnotes_reminders.reminders.models.ReminderModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.home_toolbar
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.note_list.*

import kotlinx.android.synthetic.main.reminder_list.*
import kotlinx.android.synthetic.main.reminder_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener, ReminderListener, NoteListener {

    var loc = Location()
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    lateinit var app: MainApp
    val reminderSnapHelper: SnapHelper = PagerSnapHelper()
    val noteSnapHelper: SnapHelper = PagerSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        app = application as MainApp

        val intent = Intent(this, NotificationSender::class.java)
        startService(intent)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        homeRecyclerReminders.layoutManager = layoutManager
        reminderSnapHelper.attachToRecyclerView(homeRecyclerReminders)

        val layoutManager2 = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        homeRecyclerNotes.layoutManager = layoutManager2
        noteSnapHelper.attachToRecyclerView(homeRecyclerNotes)


        home_toolbar.title = "Notes & Reminders"
        setSupportActionBar(home_toolbar)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        init()
        getLastLocation()


    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.reminders -> {
                startActivity(intentFor<ReminderListActivity>())
                finish()
            }
            R.id.notes -> {
                startActivity(intentFor<NoteListActivity>())
                finish()
            }
        }
        //constraintLayout.closeDrawer(GravityCompat.START)
        return true
    }
    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.content, fragment, fragment.javaClass.getSimpleName())
            .commit()
    }
    private fun init(){
        val toggle = ActionBarDrawerToggle(Activity(), constraintLayout, home_toolbar,
            R.string.nav_open,
            R.string.nav_closed
        )
        constraintLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigation_view.setNavigationItemSelectedListener (this)

    }

    override fun onRestart() {
        home_toolbar.title = "Notes & Reminders"
        setSupportActionBar(home_toolbar)
        init()
        loadReminders()
        loadNotes()
        super.onRestart()
    }
    private fun loadReminders(){
        val r = app.reminders.findAll()
        var list = ArrayList<ReminderModel>()
        for(i in r){

            var R = 6371e3
            var la1 = loc.lat * Math.PI / 180
            var la2 = i.lat * Math.PI / 180

            var delta1 = (i.lat - loc.lat) * Math.PI / 180
            var delta2 = (i.lng - loc.lng) * Math.PI / 180

            var a = Math.sin(delta1/2) * Math.sin(delta1/2) +
                    Math.cos(la1) * Math.cos(la2) *
                    Math.sin(delta2/2) * Math.sin(delta2/2)

            var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))

            var distance = R * c

            if(distance <= 1000.00)
                list.add(i.copy())

        }

        showReminders(list)
    }

    fun showReminders(reminders: ArrayList<ReminderModel>) {
    if(reminders.size < 1){
            ReminderTitle.text = "No Reminders to Display"
        }
        else{
            ReminderTitle.text = "Reminders"
            homeRecyclerReminders.adapter = HomePageAdapter(reminders, null, this, this)
            homeRecyclerReminders.adapter?.notifyDataSetChanged()
        }
    }
    private fun loadNotes(){
        val r = app.notes.findAll()
        var list = ArrayList<NoteModel>()
        for(i in r){

            var R = 6371e3
            var la1 = loc.lat * Math.PI / 180
            var la2 = i.lat * Math.PI / 180

            var delta1 = (i.lat - loc.lat) * Math.PI / 180
            var delta2 = (i.lng - loc.lng) * Math.PI / 180

            var a = Math.sin(delta1/2) * Math.sin(delta1/2) +
                    Math.cos(la1) * Math.cos(la2) *
                    Math.sin(delta2/2) * Math.sin(delta2/2)

            var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a))

            var distance = R * c

            if(distance <= 1000.00)
                list.add(i.copy())

        }

        showNotes(list)
    }

    fun showNotes (notes: ArrayList<NoteModel>) {
        if(notes.isEmpty()){
            NoteTitle.text = "No Notes to Display"
        }
        else{
            NoteTitle.text = "Notes"
            homeRecyclerNotes.adapter = HomePageAdapter(null, notes, this, this)
            homeRecyclerNotes.adapter?.notifyDataSetChanged()
        }
    }

    override fun onReminderClick(reminder: ReminderModel) {
        startActivityForResult(intentFor<ReminderActivity>().putExtra("reminder_edit", reminder), 0)
    }


    override fun onNoteClick(note: NoteModel) {
        startActivityForResult(intentFor<NoteActivity>().putExtra("edit_note", note), 0)
    }


    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Granted. Start getting the location information
            }
        }
    }

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    var location: android.location.Location? = task.result
                    if (location == null) {
                        requestNewLocationData()

                    } else {

                        //textView2.setText("Location: ${location.latitude.toString()}, ${location.longitude.toString()}")
                        loc.lat = location.latitude
                        loc.lng = location.longitude
                        //checkReminders()
                        loadReminders()
                        loadNotes()

                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        var mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            var mLastLocation: android.location.Location = locationResult.lastLocation
            loc.lat = mLastLocation.latitude
            loc.lng = mLastLocation.longitude

        }
    }

}
