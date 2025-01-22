package br.edu.ifsp.dmo.pesquisadeopiniao.ui.finalizar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Bom
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Otimo
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Regular
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Ruim
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityFinalizarBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.main.MainActivity

class FinalizarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinalizarBinding
    private lateinit var viewModel: FinalizarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalizarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[FinalizarViewModel::class.java]

        configTextView()
        configOnClickListener()
    }

    private fun configOnClickListener() {
        binding.buttonVoltar.setOnClickListener { voltarParaTelaInicial() }
    }

    private fun voltarParaTelaInicial() {
        val mIntent = Intent(this, MainActivity::class.java)
        startActivity(mIntent)
        finish()
    }

    private fun configTextView() {
        binding.textviewNumeroParticipantes.text = viewModel.qtdParticipantes.value.toString()
        binding.textviewQtdOtimo.text = viewModel.opinioes.value!![Otimo()].toString()
        binding.textviewQtdBom.text = viewModel.opinioes.value!![Bom()].toString()
        binding.textviewQtdRegular.text = viewModel.opinioes.value!![Regular()].toString()
        binding.textviewQtdRuim.text = viewModel.opinioes.value!![Ruim()].toString()
    }
}