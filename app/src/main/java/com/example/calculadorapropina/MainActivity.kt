package com.example.calculadorapropina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calculadorapropina.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalcular.setOnClickListener{
            calcularPropina()
        }

    }

    private fun calcularPropina() {
        val costo = binding.etCostoServicio.text.toString().toDoubleOrNull()
        if(costo == null){
            binding.tvResultadoPropina.text = ""
            mostrarPropina(0.0)
            return
        }
        val porcentajePropina = when(binding.rgOpcionesPropina.checkedRadioButtonId){
            R.id.rbPropinaVeintePorCiento -> 0.20
            R.id.rbPropinaDieciochoPorCiento -> 0.18
            else -> 0.15
        }
        var propina = porcentajePropina * costo

        if(binding.swithRedondearPropina.isChecked){
            propina = kotlin.math.ceil(propina)
        }
        mostrarPropina(propina)
    }
    private fun mostrarPropina(propina: Double){
        val formatearPropina = NumberFormat.getCurrencyInstance().format(propina)
        binding.tvResultadoPropina.text = getString(R.string.tip_amount, formatearPropina)
    }
}