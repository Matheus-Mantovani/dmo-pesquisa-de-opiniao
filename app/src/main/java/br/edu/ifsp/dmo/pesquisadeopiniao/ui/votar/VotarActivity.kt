package br.edu.ifsp.dmo.pesquisadeopiniao.ui.votar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityVotarBinding

class VotarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVotarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVotarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}