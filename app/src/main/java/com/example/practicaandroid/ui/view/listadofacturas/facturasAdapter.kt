package com.example.practicaandroid.ui.view.listadofacturas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaandroid.R
import com.example.practicaandroid.databinding.FacturaDetailRecyclerviewBinding

class facturasAdapter(
    private val dataSet: Array<String>,
    private val binding: FacturaDetailRecyclerviewBinding,
    private val context: Context
) :
    RecyclerView.Adapter<facturasAdapter.ViewHolder>() {

    class ViewHolder(view: View, binding: FacturaDetailRecyclerviewBinding) :
        RecyclerView.ViewHolder(view) {
        val importeFactura = binding.tvImporteFactura
        val estadoFactura = binding.tvEstadoFactura
        val fechaFactura = binding.tvFechaFactura
        val masDetalles = binding.ivMasDetallesFactura

        fun bind(dato: String, context: Context) {
            importeFactura.text = dato
            estadoFactura.text = dato
            fechaFactura.text = dato
            masDetalles.setOnClickListener {
                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.factura_detail_recyclerview, parent, false)
        return ViewHolder(view, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position], context)
        println(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}