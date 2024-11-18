package mx.com.quetzalnoctuno.myapplicationforimccalculator

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {
    private lateinit var tvEstatura: TextView
    private lateinit var rsEstatura: RangeSlider
    private lateinit var btnImc: Button
    private lateinit var etPeso: EditText
    private lateinit var tvImcStatus: TextView
    private lateinit var tvImc: TextView
    private var estatura: Double = 1.20
    private var peso: Double = 0.0
    private var imc: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        tvEstatura = findViewById<TextView>(R.id.tv_estaura)
        rsEstatura = findViewById<RangeSlider>(R.id.rs_estatura)
        etPeso = findViewById<EditText>(R.id.et_peso)
        tvImcStatus = findViewById<TextView>(R.id.tv_imc_status)
        tvImc = findViewById<TextView>(R.id.tv_imc)
        btnImc = findViewById<Button>(R.id.btn_imc)

        rsEstatura.setValues(120f)

        rsEstatura.addOnChangeListener { _, value, _ ->
            // Formateado de decimales
            val mts = value / 100.0 // Valores en cm a mts
            estatura = mts
            tvEstatura.text = "$mts m"
        }

        btnImc.setOnClickListener {
            if (etPeso.text.isEmpty()) {
                Toast.makeText(this, R.string.tst_message, Toast.LENGTH_SHORT).show()
            } else {
                peso = etPeso.text.toString().toDouble()
                val res = peso / (estatura * estatura)
                imc = Math.round(res.toFloat() * 10) / 10.0
                tvImcStatus.text = when (imc) {
                    in 0.0..18.4 -> "Bajo"
                    in 18.5..24.9 -> "Normal"
                    in 25.0..26.9 -> "Algo Llenito" // Sobrepeso tipo I
                    in 30.0..34.9 -> "Sobrepeso" // Sobrepeso tipo II
                    in 35.0..39.9 -> "Obsidad tipo I"
                    in 40.0..49.9 -> "Obsidad tipo II"
                    else -> "Obsidad tipo III"
                }
                tvImc.text = imc.toString()
            }
        }


    }
}