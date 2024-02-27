package com.example.practicaandroid.ui.view.smartsolar

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.practicaandroid.R
import com.example.practicaandroid.databinding.FragmentDetallesBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [detallesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class detallesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentDetallesBinding

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
            val boton:Button = view.findViewById(R.id.btn_cerrar)
            boton.setOnClickListener { dialog.dismiss() }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            detallesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}