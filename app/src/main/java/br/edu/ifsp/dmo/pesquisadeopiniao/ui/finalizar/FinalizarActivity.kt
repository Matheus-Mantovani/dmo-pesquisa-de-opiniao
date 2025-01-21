package br.edu.ifsp.dmo.pesquisadeopiniao.ui.finalizar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityFinalizarBinding

class FinalizarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinalizarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalizarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}