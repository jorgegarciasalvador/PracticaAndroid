package com.example.practicaandroid.ui.view.listadofacturas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaandroid.data.FacturaModel
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
        val datos = arrayOf(
            FacturaModel("hoy", true, 100.0),
            FacturaModel("mañana", false, 120.0),
            FacturaModel("ayer", true, 50.0)
        )
        val facturasAdapter = facturasAdapter(datos, this)

        val recyclerView: RecyclerView = binding.rvListadoFacturas
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = facturasAdapter
    }
}