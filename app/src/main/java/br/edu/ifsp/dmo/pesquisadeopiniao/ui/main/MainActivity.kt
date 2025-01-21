package br.edu.ifsp.dmo.pesquisadeopiniao.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.R
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.checar.ChecarActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.finalizar.FinalizarActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.participar.ParticiparActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configOnClickListeners()
    }

    private fun configOnClickListeners() {
        binding.buttonParticipar.setOnClickListener { abrirTela("participar") }
        binding.buttonChecar.setOnClickListener { abrirTela("checar") }
        binding.buttonFinalizar.setOnClickListener { abrirTela("finalizar") }
    }

    private fun abrirTela(string: String) {
        val mIntent = when(string) {
            "participar" -> Intent(this, ParticiparActivity::class.java)
            "checar" -> Intent(this, ChecarActivity::class.java)
            "finalizar" -> Intent(this, FinalizarActivity::class.java)
            else -> Intent(this, ParticiparActivity::class.java)
        }
        startActivity(mIntent)
    }
}