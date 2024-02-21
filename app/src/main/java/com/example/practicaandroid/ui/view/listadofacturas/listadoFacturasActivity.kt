package com.example.practicaandroid.ui.view.listadofacturas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaandroid.model.FacturaModel
import com.example.practicaandroid.databinding.ActivityListadofacturasBinding
import com.example.practicaandroid.databinding.FacturaDetailRecyclerviewBinding
import com.example.practicaandroid.ui.viewmodel.ViewModelFacturas

class ListadoFacturasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListadofacturasBinding
    private lateinit var detailBinding: FacturaDetailRecyclerviewBinding
    private val facturasViewModel: ViewModelFacturas by viewModels()
    private lateinit var facturasAdapter: facturasAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadofacturasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailBinding = FacturaDetailRecyclerviewBinding.inflate(layoutInflater)

        facturasViewModel.onCreate()
        facturasAdapter = facturasAdapter(emptyList(),this)
        facturasViewModel.facturas.observe(this) {
            facturasAdapter = facturasAdapter(it,this)

            val recyclerView: RecyclerView = binding.rvListadoFacturas
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = facturasAdapter
        //setUpRecyclerView(it)
        }

        binding.ivFilterIcon.setOnClickListener {
            Toast.makeText(
                this,
                "Esto te manda a la pagina de filtros que voy a dejar para el ultimo por pereza",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setUpRecyclerView(facturas:List<FacturaModel>) {


    }
}