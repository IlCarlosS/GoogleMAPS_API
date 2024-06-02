package com.example.mapsapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.google.android.gms.maps.model.Marker

class MarkersListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_markers_list)

        val markers = intent.getSerializableExtra("markers") as? ArrayList<MarkerData>
        val markersTextView = findViewById<TextView>(R.id.markers_text_view)

        markersTextView.text = markers?.joinToString("\n") {
            "${it.name}: (${it.latitude}, ${it.longitude})"
        } ?: "No markers available"
    }
}

data class MarkerData(val name: String, val latitude: Double, val longitude: Double) : java.io.Serializable