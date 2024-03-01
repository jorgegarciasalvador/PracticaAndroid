package com.example.practicaandroid.ui.view.smartsolar

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.practicaandroid.R
import com.example.practicaandroid.databinding.FragmentDetallesBinding
import com.example.practicaandroid.ui.viewmodel.ViewModelDetallesFacturas

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetallesFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentDetallesBinding
    private val detallesFacturasViewModel: ViewModelDetallesFacturas by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        binding = FragmentDetallesBinding.inflate(layoutInflater)
        binding.ivMasInfo.setOnClickListener {
            val customDialog = AlertDialog.Builder(this.context)
            val inflater = LayoutInflater.from(this.context)
            val view = inflater.inflate(R.layout.popup_info_autoconsumo, null)
            customDialog.setView(view)
            val dialog = customDialog.create()
            dialog.show()
            val boton: Button = view.findViewById(R.id.btn_cerrar)
            boton.setOnClickListener { dialog.dismiss() }
        }

        detallesFacturasViewModel.detallesFacturas_.observe(this) {
            binding.tvCau.text = it.codigoAutoconsumo
            binding.tvCompensacion.text = it.codigoAutoconsumo
            binding.tvEstado.text = it.estadoSolicitud
            binding.tvTipo.text = it.tipo
            binding.tvPotencia.setText(it.potencia)
        }

        detallesFacturasViewModel.onCreate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetallesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}