package edu.cmich.locationnotes_reminders.notes.activities

import App.MainApp
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import edu.cmich.locationnotes_reminders.R
import edu.cmich.locationnotes_reminders.activities.MapsActivity
import edu.cmich.locationnotes_reminders.models.Location
import edu.cmich.locationnotes_reminders.notes.models.NoteModel
import edu.cmich.locationnotes_reminders.reminders.activities.PERMISSION_ID
import kotlinx.android.synthetic.main.note_main.*
import org.jetbrains.anko.*

class NoteActivity : AppCompatActivity(), AnkoLogger {

    var note = NoteModel()
    var noteStatus = R.string.toast_noteAdded
    lateinit var app: MainApp
    var edit = false
    val LOCATION_REQUEST = 2
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    var loc = Location()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_main)

        note_toolbar.title = "Add Note"
        setSupportActionBar(note_toolbar)
        loc.zoom = 15f
        info("Note Activity started..")
        app = application as MainApp
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastLocation()

        noteLocation.setText(("Location: "))


//        if (note.lat == 0.0 && note.lng == 0.0) {
//
//        }

        if (intent.hasExtra("edit_note")) {
            edit = true
            note_toolbar.title = "Edit Note"
            note = intent.extras?.getParcelable<NoteModel>("edit_note")!!
            noteTitle.setText(note.title)
            noteText.setText(note.text)
            noteLocation.text = getString(R.string.note_location, note.lat, note.lng)
            btn_add_modify_note.setText(R.string.button_saveNote)
            btn_add_note_location.setText(R.string.button_updateLocation)
            noteStatus = R.string.toast_noteUpdated
        }

        btn_add_modify_note.setOnClickListener() {

            note.title = noteTitle.text.toString()
            note.text = noteText.text.toString()
            if (note.title.isEmpty()) {
                toast(R.string.toast_emptyTitle)
            } else {
                if (edit) {
                    app.notes.update(note.copy())
                } else {
                    app.notes.create(note.copy())
                }
            }
            toast(noteStatus)
            setResult(RESULT_OK)
            finish()
        }

        btn_add_note_location.setOnClickListener {
            //val location = Location(52.245696, -7.139102, 15f)
            if (note.zoom != 0f) {
                loc.lat =  note.lat
                loc.lng = note.lng
                loc.zoom = note.zoom
            }
            info ("Set Location Pressed")
            startActivityForResult (intentFor<MapsActivity>().putExtra("location", loc), LOCATION_REQUEST)
        }
    }

    override fun onRestart() {
        super.onRestart()
        if(note.lat != 0.0)
            noteLocation.setText(getString(R.string.note_location, note.lat, note.lng))
        else
            noteLocation.setText(getString(R.string.note_location, loc.lat, loc.lng))
            btn_add_note_location.setText(R.string.button_updateLocation)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(intent.hasExtra("edit_note")){
            menuInflater.inflate(R.menu.reminder_menu2, menu)
            return super.onCreateOptionsMenu(menu)
        }
        else{
            menuInflater.inflate(R.menu.note_menu, menu)
            return super.onCreateOptionsMenu(menu)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.cancel_note -> {
                finish()
            }

            R.id.delete_note -> {
                app.notes.delete(note)
                toast(R.string.toast_noteDeleted)

                finish()
            }
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras?.getParcelable<Location>("location")!!
                    note.lat = location.lat
                    note.lng = location.lng
                    note.zoom = location.zoom
                }
            }
        }
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
    private fun getLastLocation() {
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