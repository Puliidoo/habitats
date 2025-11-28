package com.example.habitats

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Pag1Activity : AppCompatActivity() {

    private var puntos: Int = 0
    private lateinit var marcador: TextView
    private var puntosSumados = false // control para sumar solo una vez

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag1)

        val mark1: ImageView = findViewById(R.id.mark1)
        val mark2: ImageView = findViewById(R.id.mark2)
        val mark3: ImageView = findViewById(R.id.mark3)

        marcador = findViewById(R.id.marcador)
        actualizarMarcador()

        findViewById<ImageView>(R.id.avatar1).setOnClickListener {
            ocultarMarcas(mark1, mark2, mark3)
            mark1.setImageResource(R.drawable.tick)
            mark1.visibility = ImageView.VISIBLE

            if (!puntosSumados) {
                puntos += 10
                puntosSumados = true
                actualizarMarcador()

                Toast.makeText(this, "¡ENHORABUENA!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, Pag2Activity::class.java)
                intent.putExtra("puntos", puntos)
                startActivity(intent)
                finish()

            }
        }

        findViewById<ImageView>(R.id.avatar2).setOnClickListener {
            mostrarIncorrectoYReiniciar(mark2, mark1, mark2, mark3)
        }

        findViewById<ImageView>(R.id.avatar3).setOnClickListener {
            mostrarIncorrectoYReiniciar(mark3, mark1, mark2, mark3)
        }
    }

    private fun mostrarIncorrectoYReiniciar(
        targetMark: ImageView,
        m1: ImageView,
        m2: ImageView,
        m3: ImageView
                                           ) {
        ocultarMarcas(m1, m2, m3)
        targetMark.setImageResource(R.drawable.x_roja)
        targetMark.visibility = ImageView.VISIBLE

        puntos = 0
        puntosSumados = false
        actualizarMarcador()

        Toast.makeText(this, "Incorrecto", Toast.LENGTH_SHORT).show()
        reiniciarJuego(m1, m2, m3)
    }

    private fun ocultarMarcas(m1: ImageView, m2: ImageView, m3: ImageView) {
        m1.visibility = ImageView.GONE
        m2.visibility = ImageView.GONE
        m3.visibility = ImageView.GONE
    }

    private fun reiniciarJuego(m1: ImageView, m2: ImageView, m3: ImageView) {
        m1.postDelayed({
                           ocultarMarcas(m1, m2, m3)
                           puntosSumados = false
                       }, 1000)
    }

    private fun actualizarMarcador() {
        marcador.text = "Puntos: $puntos"
    }
}
