package br.edu.ifsp.dmo.pesquisadeopiniao.ui.participar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityParticiparBinding

class ParticiparActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParticiparBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticiparBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}