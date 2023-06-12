package edu.cmich.locationnotes_reminders.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.cmich.locationnotes_reminders.R
import edu.cmich.locationnotes_reminders.notes.models.NoteListener
import edu.cmich.locationnotes_reminders.notes.models.NoteModel
import edu.cmich.locationnotes_reminders.reminders.models.ReminderListener
import edu.cmich.locationnotes_reminders.reminders.models.ReminderModel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.home_card.view.*





class HomePageAdapter constructor(private var reminders: ArrayList<ReminderModel>?, private var notes: ArrayList<NoteModel>?,
                                  private val Rlistener: ReminderListener, private val Nlistener: NoteListener
) :
    RecyclerView.Adapter<HomePageAdapter.MainHolder>(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    var Coptions = CircleOptions()
        .radius(100.0)
        .fillColor(R.color.map_circleColor)

    var c: Circle? = null
    var reminder = ReminderModel()
    var note = NoteModel()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {

        return MainHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.home_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        if(notes == null) {
            reminder = reminders!![holder.adapterPosition]
            holder.Rbind(reminder, Rlistener)
            holder.mapView.onCreate(null)
            holder.mapView.getMapAsync(this)
        }
        else if(reminders == null){
            note = notes!![holder.adapterPosition]
            holder.Nbind(note, Nlistener)
            holder.mapView.onCreate(null)
            holder.mapView.getMapAsync(this)
        }
    }

    override fun getItemCount(): Int{
        if(notes == null){
            return reminders!!.size
        }
        else if(reminders == null){
            return notes!!.size
        }
        return 0
    }

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mapView = itemView.findViewById(R.id.map) as MapView
        fun Rbind(reminder: ReminderModel, listener: ReminderListener) {
            itemView.homeCardTitle.text = reminder.title
            itemView.homeCardDscp.text = reminder.dscp

            //itemView.reminderLocationCard.text = "Location: ${reminder.lat}, ${reminder.lng}"
            itemView.setOnClickListener{listener.onReminderClick(reminder)}
        }

        fun Nbind(note: NoteModel, listener: NoteListener){
            itemView.homeCardTitle.text = note.title
            itemView.homeCardDscp.text = note.text
            itemView.setOnClickListener{listener.onNoteClick(note)}
        }
    }

    override fun onMapReady(map: GoogleMap){
        mMap = map
        var loc : LatLng = LatLng(0.0,0.0)
        if(reminders == null){
            loc = LatLng(note.lat, note.lng)
        }
        else if(notes == null) {
            loc = LatLng(reminder.lat, reminder.lng)
        }
        val options = MarkerOptions()
            .title("Location")
            .snippet("GPS : " + loc.toString())
            .draggable(false)
            .position(loc)

        //Coptions.center(loc)

        //c =  mMap.addCircle(Coptions)

        mMap.addMarker(options)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15f))
    }
}