package edu.cmich.locationnotes_reminders.models

import App.MainApp
import android.Manifest
import android.annotation.SuppressLint
import android.app.IntentService
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import edu.cmich.locationnotes_reminders.R
import edu.cmich.locationnotes_reminders.notes.models.NoteModel
import edu.cmich.locationnotes_reminders.reminders.activities.CHANNEL_ID
import edu.cmich.locationnotes_reminders.reminders.models.ReminderModel
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast


class NotificationSender : IntentService("NotificationSender"){
    private val TAG = "LocationService"
    lateinit var app: MainApp
    var loc = edu.cmich.locationnotes_reminders.models.Location()
    var locationManager: LocationManager? = null

    @SuppressLint("MissingPermission")
    override fun onHandleIntent(intent: Intent?) {

    }

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        app = application as MainApp

        // Acquire a reference to the system Location Manager
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // Define a listener that responds to location updates
        //toast("Location: ${loc.lat}, ${loc.lng}")

// Register the listener with the Location Manager to receive location updates
        if(checkPermissions()) {
            locationManager?.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER ,
                0,
                0f,
                locationListener
            )
        }
        super.onCreate()

    }

    var locationListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            //toast("Location Updated")
            loc.lat = location.latitude
            loc.lng = location.longitude
            checkReminders()

        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        }

        override fun onProviderEnabled(provider: String) {
        }

        override fun onProviderDisabled(provider: String) {
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
    }

    fun checkReminders(){

        //toast("Loc = ${loc.lat}, ${loc.lng}")



        var r = app.reminders.findAll()

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

            if(distance <= 75)

                sendReminderNotification(i)

        }

    }

    fun sendReminderNotification(reminder: ReminderModel){
        //toast("Remidner: ${reminder.title} x")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "Reminder Notification"
            val descriptionText = "Notifications For Reminders"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        // Create an explicit intent for an Activity in your app
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notifications_active_black_18dp)
            .setContentTitle(reminder.title)
            .setContentText(reminder.dscp)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(123, builder.build())
        }
    }

    fun checkNotes(){

        //toast("Loc = ${loc.lat}, ${loc.lng}")



        var n = app.notes.findAll()

        for(i in n){

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

            if(distance <= 75)

                sendNoteNotification(i)

        }

    }
    fun sendNoteNotification(note: NoteModel){
        //toast("Remidner: ${reminder.title} x")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "Note Notification"
            val descriptionText = "Notifications For Notes"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        // Create an explicit intent for an Activity in your app
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notes_black_18dp)
            .setContentTitle(note.title)
            .setContentText(note.text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(123, builder.build())
        }
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.i(TAG, "Service onStartCommand " + startId)
        return Service.START_STICKY
    }

    // when destroyed, remove the listener
    override fun onDestroy() {
        Log.i(TAG, "Service onDestroy")
        locationManager?.removeUpdates(locationListener)
        // could schedule a self-restart here

    }
}