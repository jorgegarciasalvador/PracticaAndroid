package com.example.practicaandroid.ui.view.listadofacturas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getString
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaandroid.R
import com.example.practicaandroid.data.FacturaModel
import com.example.practicaandroid.databinding.FacturaDetailRecyclerviewBinding

class facturasAdapter(
    private val dataSet: Array<FacturaModel>,
    private val context: Context
) :
    RecyclerView.Adapter<facturasAdapter.ViewHolder>() {

    class ViewHolder(binding: FacturaDetailRecyclerviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val importeFactura = binding.tvImporteFactura
        val estadoFactura = binding.tvEstadoFactura
        val fechaFactura = binding.tvFechaFactura
        val masDetalles = binding.ivMasDetallesFactura

        fun bind(factura: FacturaModel, context: Context) {
            importeFactura.text = factura.importe.toString()
            fechaFactura.text = factura.fecha
            if (factura.estado) {
                estadoFactura.text = getString(context, R.string.pendienteDePago_text)
                estadoFactura.visibility = View.VISIBLE
            } else {
                estadoFactura.text = ""
                estadoFactura.visibility = View.INVISIBLE
            }

            masDetalles.setOnClickListener {
                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FacturaDetailRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position], context)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}