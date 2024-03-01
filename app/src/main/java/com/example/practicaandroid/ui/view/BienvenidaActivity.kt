package com.example.practicaandroid.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaandroid.databinding.ActivityBienvenidaBinding
import com.example.practicaandroid.ui.view.listadofacturas.ListadoFacturasActivity
import com.example.practicaandroid.ui.view.smartsolar.SmartsolarActivity

class BienvenidaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBienvenidaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBienvenidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnListeners()
    }

    private fun setOnListeners() {

        var retromockActivado = false
        binding.btnListado.setOnClickListener {
            val intent = Intent(this, ListadoFacturasActivity::class.java)
            intent.putExtra("retromockactivado", retromockActivado)
            startActivity(intent)
        }

        binding.btnSmartSolar.setOnClickListener {
            val intent = Intent(this, SmartsolarActivity::class.java)
            startActivity(intent)
        }
        binding.switchRetromock.setOnCheckedChangeListener { _, isChecked ->
            retromockActivado = if (isChecked) {
                Toast.makeText(this, "Retromock activado", Toast.LENGTH_SHORT).show()
                true
            } else {
                Toast.makeText(this, "Retromock desactivado", Toast.LENGTH_SHORT).show()
                false
            }
        }
    }
}