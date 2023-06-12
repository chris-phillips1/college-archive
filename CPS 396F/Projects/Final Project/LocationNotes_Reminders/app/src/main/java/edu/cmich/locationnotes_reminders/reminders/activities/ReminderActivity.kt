package edu.cmich.locationnotes_reminders.reminders.activities

import App.MainApp
import edu.cmich.locationnotes_reminders.reminders.models.ReminderModel
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
import edu.cmich.locationnotes_reminders.activities.MapsActivity
import edu.cmich.locationnotes_reminders.R
import edu.cmich.locationnotes_reminders.activities.MainActivity
import edu.cmich.locationnotes_reminders.models.Location
import kotlinx.android.synthetic.main.reminder_main.*
import kotlinx.android.synthetic.main.reminder_main.toolbarAdd
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import java.util.*

class ReminderActivity : AppCompatActivity(), AnkoLogger {

    var app : MainApp? = null
    var reminder = ReminderModel()
    var reminderStatus = R.string.toast_reminderAdded
    var edit = false
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    val LOCATION_REQUEST = 2
    var loc = Location()

    val PERMISSION_ID = 42

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reminder_main)
        app = application as MainApp
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        toolbarAdd.title = "Add Reminder"
        setSupportActionBar(toolbarAdd)
        loc.zoom = 15f
        getLastLocation()

        textView2.setText(("Location: "))



        if (intent.hasExtra("reminder_edit")) {
            edit = true
            toolbarAdd.title = "Edit Reminder"
            reminder = intent.extras?.getParcelable<ReminderModel>("reminder_edit")!!
            reminderTitle.setText(reminder.title)
            reminderDscp.setText(reminder.dscp)
            LocationAdd.setText(R.string.button_updateLocation)
            textView2.text = "Location: ${reminder.lat}, ${reminder.lng}"
            btnAdd.setText(R.string.button_saveReminder)
            reminderStatus = R.string.toast_reminderUpdated
        }


        //button used for adding the reminders.
        btnAdd.setOnClickListener(){
            reminder.title = reminderTitle.text.toString()
            reminder.dscp = reminderDscp.text.toString()
            reminder.date = Date()


            if(reminder.title.isEmpty()){
                toast(R.string.toast_emptyTitle)
            }
            else{
                if(reminder.dscp.isEmpty()){
                    toast(R.string.toast_emptyDscp)
                }
                else if(edit){
                    app!!.reminders.update(reminder.copy())
                    setResult((AppCompatActivity.RESULT_OK))
                    finish()
                }
                else{
                    app!!.reminders.create(reminder.copy())
                    setResult(AppCompatActivity.RESULT_OK)
                    finish()
                    toast(R.string.toast_reminderAdded)

                }
            }

            toast(reminderStatus)
        }

        LocationAdd.setOnClickListener() {


            if(reminder.lat != 0.0){
                loc.lat = reminder.lat
                loc.lng = reminder.lng
                loc.zoom = reminder.zoom
            }
            startActivityForResult(intentFor<MapsActivity>().putExtra("location", loc), LOCATION_REQUEST)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.cancel_note -> {
                finish()
            }
            R.id.item_delete -> {
                toast(R.string.toast_reminderDeleted)
                app!!.reminders.delete(reminder)
                app!!.reminders.delete(reminder)
                //ReminderListActivity().loadReminders()
                startActivity(intentFor<MainActivity>())
                finish()

            }
        }
        return super.onOptionsItemSelected(item!!)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if(intent.hasExtra("reminder_edit")){
            menuInflater.inflate(R.menu.reminder_menu2, menu)
            return super.onCreateOptionsMenu(menu)
        }
        else{
            menuInflater.inflate(R.menu.reminder_menu, menu)
            return super.onCreateOptionsMenu(menu)

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras?.getParcelable<Location>("location")!!
                    reminder.lat = location.lat
                    reminder.lng = location.lng
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun obtainLocation() {
//        fusedLocationClient.lastLocation.addOnSuccessListener { location: android.location.Location ->
//            textView2.setText("Location: ${location.latitude}, ${location.longitude}")
//            toast("Location: ${location.latitude}, ${location.longitude}")
//
//        }
    }

    override fun onRestart() {
        super.onRestart()
        if(reminder.lat != 0.0)
            textView2.setText("Location: ${reminder.lat}, ${reminder.lng}")
        else
            textView2.setText("Location: ${loc.lat}, ${loc.lng}")
            LocationAdd.setText(R.string.button_updateLocation)
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
