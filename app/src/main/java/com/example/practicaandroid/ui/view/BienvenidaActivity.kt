package com.example.practicaandroid.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practicaandroid.databinding.ActivityBienvenidaBinding
import com.example.practicaandroid.ui.view.listadofacturas.ListadoFacturasActivity
import com.example.practicaandroid.ui.view.smartsolar.SmartsolarActivity

class BienvenidaActivity : AppCompatActivity() {
    lateinit var binding: ActivityBienvenidaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBienvenidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnListeners()
    }

    private fun setOnListeners() {
        binding.btnListado.setOnClickListener {
            val intent = Intent(this, ListadoFacturasActivity::class.java)
            startActivity(intent)
        }

        binding.btnSmartSolar.setOnClickListener {
            val intent = Intent(this, SmartsolarActivity::class.java)
            startActivity(intent)
        }
    }
}