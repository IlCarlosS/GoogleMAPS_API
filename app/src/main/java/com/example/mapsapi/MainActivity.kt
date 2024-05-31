package com.example.mapsapi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

private lateinit var map:GoogleMap
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var latInput: EditText
    private lateinit var lngInput: EditText
    private lateinit var addMarkerButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragment()
        latInput = findViewById(R.id.lat_input)
        lngInput = findViewById(R.id.lng_input)
        addMarkerButton = findViewById(R.id.add_marker_button)

        addMarkerButton.setOnClickListener {
            val lat = latInput.text.toString().toDoubleOrNull()
            val lng = lngInput.text.toString().toDoubleOrNull()

            if (lat != null && lng != null) {
                val coordinates = LatLng(lat, lng)
                addMarker(coordinates)
            } else {
                Toast.makeText(this, "Por favor, introduce coordenadas válidas", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createFragment(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
        map.setOnMapClickListener { latLng ->
            addMarker(latLng)
        }
    }

    private fun createMarker(){
        val coordinates = LatLng(21.9127852,-102.3165875)
        val marker = MarkerOptions().position(coordinates).title ("UAA EDIFICIO 58")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 10f),
            4000,
            null
        )
    }

    private fun addMarker(latLng: LatLng) {
        val markerOptions = MarkerOptions().position(latLng).title("Nuevo marcador")
        map.addMarker(markerOptions)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
    }
}