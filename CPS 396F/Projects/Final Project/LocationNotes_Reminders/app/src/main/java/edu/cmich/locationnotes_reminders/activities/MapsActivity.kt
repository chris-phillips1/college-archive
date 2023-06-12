package edu.cmich.locationnotes_reminders.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import edu.cmich.locationnotes_reminders.R
import edu.cmich.locationnotes_reminders.models.Location
import edu.cmich.locationnotes_reminders.reminders.activities.ReminderActivity

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,  GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap

    var Coptions = CircleOptions()
        .radius(100.0)
        .fillColor(R.color.map_circleColor)

    var c: Circle? = null


    var location = Location()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        location = intent.extras?.getParcelable<Location>("location")!!
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap.setOnMarkerDragListener(this)
        mMap.setOnMarkerClickListener(this)

        var loc : LatLng = LatLng(location.lat, location.lng)

        val options = MarkerOptions()
            .title("Reminder Location")
            .snippet("GPS : " + loc.toString())
            .draggable(true)
            .position(loc)

        Coptions.center(loc)

        c =  mMap.addCircle(Coptions)

        mMap.addMarker(options)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, location.zoom))
    }

    override fun onMarkerDragStart(marker: Marker) {
        c!!.remove()
    }

    override fun onMarkerDrag(marker: Marker){

    }

    override fun onMarkerDragEnd(marker: Marker) {
        location.lat = marker.position.latitude
        location.lng = marker.position.longitude

        Coptions.center(marker.position)
        mMap.addCircle(Coptions)

        location.zoom = mMap.cameraPosition.zoom
    }

    override fun onMarkerClick(marker: Marker): Boolean{
        val loc = LatLng(location.lat, location.lng)
        marker.setSnippet("GPS: "+ loc.toString())
        return false
    }

    override fun onBackPressed() {
        ReminderActivity().reminder.lat = location.lat
        ReminderActivity().reminder.lng = location.lng
        val resultIntent = Intent()
        resultIntent.putExtra("location", location)
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
        super.onBackPressed()
    }
}

