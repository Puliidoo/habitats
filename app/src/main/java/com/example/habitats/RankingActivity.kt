package com.example.habitats

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RankingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        val btnVolverAJugar = findViewById<Button>(R.id.btnVolverAJugar)
        btnVolverAJugar.setOnClickListener {
            // Opcional: limpiar puntos si quieres empezar desde cero
            val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
            prefs.edit().putInt("puntos", 0).apply()

            // Volver a la pantalla de inicio o de avatares
            val intent = Intent(this, Activity_Inicio::class.java)
            startActivity(intent)
            finish()
        }


        val contenedor = findViewById<LinearLayout>(R.id.contenedorRanking)
        val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
        val registros = prefs.getStringSet("registros", setOf())!!.toList()

        val listaOrdenada = registros.sortedByDescending {
            it.split("|")[2].toInt()
        }

        for (registro in listaOrdenada) {
            val partes = registro.split("|")
            val nombre = partes[0]
            val avatarResId = partes[1].toInt()
            val puntos = partes[2].toInt()

            val fila = LinearLayout(this)
            fila.orientation = LinearLayout.HORIZONTAL
            fila.gravity = Gravity.CENTER
            fila.setPadding(16, 24, 16, 24)

            val img = ImageView(this)
            img.setImageResource(avatarResId)
            img.layoutParams = LinearLayout.LayoutParams(150, 150)

            val txt = TextView(this)
            txt.text = "$nombre - $puntos puntos"
            txt.setTextColor(Color.BLACK)
            txt.textSize = 24f
            txt.setPadding(24, 0, 0, 0)

            fila.addView(img)
            fila.addView(txt)
            contenedor.addView(fila)
        }
    }
}
