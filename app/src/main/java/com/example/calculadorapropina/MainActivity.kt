package com.example.calculadorapropina

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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

        binding.etCostoServicio.setOnKeyListener{ view, keyCode, _ -> handleKeyEvent(view, keyCode)

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

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}