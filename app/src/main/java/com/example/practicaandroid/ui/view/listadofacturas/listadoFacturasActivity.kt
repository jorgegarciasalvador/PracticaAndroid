package com.example.practicaandroid.ui.view.listadofacturas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaandroid.databinding.ActivityListadofacturasBinding
import com.example.practicaandroid.databinding.FacturaDetailRecyclerviewBinding

class listadoFacturasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadofacturasBinding
    private lateinit var detailBinding: FacturaDetailRecyclerviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadofacturasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailBinding = FacturaDetailRecyclerviewBinding.inflate(layoutInflater)

        setUpRecyclerView()
    }

    fun setUpRecyclerView() {
        val datos = arrayOf("20€", "10€", "25€")
        val facturasAdapter = facturasAdapter(datos, detailBinding, this)

        val recyclerView: RecyclerView = binding.rvListadoFacturas
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = facturasAdapter
    }
}