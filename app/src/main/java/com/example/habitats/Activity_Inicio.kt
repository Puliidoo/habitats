package com.example.habitats

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Activity_Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicios)

        val editNombre = findViewById<EditText>(R.id.editNombre)
        val botonJugar = findViewById<Button>(R.id.boton_jugar)

        botonJugar.setOnClickListener {
            val nombreUsuario = editNombre.text.toString().trim()

            if (nombreUsuario.isNotEmpty()) {
                val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
                prefs.edit().putString("nombreUsuario", nombreUsuario).apply()

                val intent = Intent(this, AvataresActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                editNombre.error = "Por favor escribe tu nombre"
            }
        }
    }
}
