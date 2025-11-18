package com.example.habitats

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AvataresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avatares)

        val avatarIDS = listOf(
            R.id.avatar1,
            R.id.avatar2,
            R.id.avatar3,
            R.id.avatar4
                              )

        avatarIDS.forEachIndexed { index, id ->
            findViewById<ImageView>(id).setOnClickListener {
                val seleccionAvatar = when (index) {
                    0 -> R.drawable.avatar1
                    1 -> R.drawable.avatar2
                    2 -> R.drawable.avatar3
                    else -> R.drawable.avatar4
                }

                val prefs = getSharedPreferences("user_data", MODE_PRIVATE)
                prefs.edit().putInt("avatarSeleccionado", seleccionAvatar).apply()

                val intent = Intent(this, Pag1Activity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
