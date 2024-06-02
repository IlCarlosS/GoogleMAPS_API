package com.example.mapsapi

<<<<<<< HEAD
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
=======
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
>>>>>>> origin/main
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

<<<<<<< HEAD

private lateinit var map:GoogleMap
private val markers = ArrayList<MarkerData>()
private val ADD_MARKER_REQUEST_CODE = 1
private const val LOCATION_PERMISSION_REQUEST_CODE = 2

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var addMarkerButton: Button
    private lateinit var listMarkersButton: Button
    private lateinit var mapTypeSpinner: Spinner
    private lateinit var anotherButton: Button
    private lateinit var fusedLocationClient: FusedLocationProviderClient
=======
private lateinit var map:GoogleMap
class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var latInput: EditText
    private lateinit var lngInput: EditText
    private lateinit var addMarkerButton: Button
>>>>>>> origin/main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragment()
<<<<<<< HEAD

        addMarkerButton = findViewById(R.id.add_marker_button)
        listMarkersButton = findViewById(R.id.list_markers_button)
        mapTypeSpinner = findViewById(R.id.map_type_spinner)
        anotherButton = findViewById(R.id.another_button)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setupMapTypeSpinner()

        addMarkerButton.setOnClickListener {
            val intent = Intent(this, AddMarkerActivity::class.java)
            startActivityForResult(intent, ADD_MARKER_REQUEST_CODE)
        }

        listMarkersButton.setOnClickListener {
            val intent = Intent(this, MarkersListActivity::class.java)
            intent.putExtra("markers", markers)
            startActivity(intent)
        }

        anotherButton.setOnClickListener {
            getLocation()
=======
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
>>>>>>> origin/main
        }
    }

    private fun createFragment(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

<<<<<<< HEAD
    private fun setupMapTypeSpinner() {
        val mapTypes = arrayOf("Normal", "Satelite", "Terreno", "Hibrido")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, mapTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mapTypeSpinner.adapter = adapter

        mapTypeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> map.mapType = GoogleMap.MAP_TYPE_NORMAL
                    1 -> map.mapType = GoogleMap.MAP_TYPE_SATELLITE
                    2 -> map.mapType = GoogleMap.MAP_TYPE_TERRAIN
                    3 -> map.mapType = GoogleMap.MAP_TYPE_HYBRID
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No action needed
            }
        }
    }

    private fun getLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        val currentLatLng = LatLng(it.latitude, it.longitude)
                        addMarkerAndAnimateCamera("Current Location", currentLatLng)
                    }
                }
        }
    }

=======
>>>>>>> origin/main
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
        map.setOnMapClickListener { latLng ->
            addMarker(latLng)
        }
<<<<<<< HEAD

    }

    private fun createMarker(){
        val coordinates = LatLng(21.915284,-102.313997)
        val marker = MarkerOptions().position(coordinates).title ("UAA")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 12f),
            3000,
=======
    }

    private fun createMarker(){
        val coordinates = LatLng(21.9127852,-102.3165875)
        val marker = MarkerOptions().position(coordinates).title ("UAA EDIFICIO 58")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 10f),
            4000,
>>>>>>> origin/main
            null
        )
    }

    private fun addMarker(latLng: LatLng) {
        val markerOptions = MarkerOptions().position(latLng).title("Nuevo marcador")
        map.addMarker(markerOptions)
<<<<<<< HEAD
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_MARKER_REQUEST_CODE && resultCode == RESULT_OK) {
            val name = data?.getStringExtra("name")
            val lat = data?.getDoubleExtra("lat", 0.0)
            val lng = data?.getDoubleExtra("lng", 0.0)
            if (name != null && lat != null && lng != null) {
                val coordinates = LatLng(lat, lng)
                addMarkerAndAnimateCamera(name, coordinates)
                markers.add(MarkerData(name, lat, lng))
            }
        }
    }

    private fun addMarkerAndAnimateCamera(name: String, coordinates: LatLng) {
        val markerOptions = MarkerOptions().position(coordinates).title(name)
        map.addMarker(markerOptions)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 12f),
            4000,
            null
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            }
        }
    }

=======
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
    }
>>>>>>> origin/main
}