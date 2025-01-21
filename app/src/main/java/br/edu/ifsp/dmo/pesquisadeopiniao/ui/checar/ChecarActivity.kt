package br.edu.ifsp.dmo.pesquisadeopiniao.ui.checar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityChecarBinding

class ChecarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChecarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChecarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}