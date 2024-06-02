package com.example.mapsapi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AddMarkerActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var latInput: EditText
    private lateinit var lngInput: EditText
    private lateinit var addMarkerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_marker_activity)

        nameInput = findViewById(R.id.name_input)
        latInput = findViewById(R.id.lat_input)
        lngInput = findViewById(R.id.lng_input)
        addMarkerButton = findViewById(R.id.add_marker_button)

        addMarkerButton.setOnClickListener {
            val name = nameInput.text.toString()
            val lat = latInput.text.toString().toDoubleOrNull()
            val lng = lngInput.text.toString().toDoubleOrNull()

            if (name.isBlank() || lat == null || lng == null) {
                Toast.makeText(this, "Por favor, introduce datos válidos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val coordinates = LatLng(lat, lng)
                val resultIntent = Intent()
                resultIntent.putExtra("name", name)
                resultIntent.putExtra("lat", lat)
                resultIntent.putExtra("lng", lng)
                setResult(RESULT_OK, resultIntent)
                Toast.makeText(this, "Marcador '$name' añadido en '$lat' , '$lng'" , Toast.LENGTH_SHORT).show()
                finish() // Cierra la actividad después de agregar el marcador
            }
        }
    }
}
