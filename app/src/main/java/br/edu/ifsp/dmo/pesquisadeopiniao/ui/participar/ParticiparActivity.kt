package br.edu.ifsp.dmo.pesquisadeopiniao.ui.participar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.pesquisadeopiniao.R
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityParticiparBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.votar.VotarActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.utils.Constants

class ParticiparActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParticiparBinding
    private lateinit var viewModel: ParticiparViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticiparBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ParticiparViewModel::class.java]

        configOnClickListener()
    }

    private fun configOnClickListener() {
        binding.buttonConfirmar.setOnClickListener {
            atualizarDados()
            if(viewModel.dadosValidos()) {
                abrirTelaVotar()
            } else {
                notificarErro()
            }
        }
    }

    private fun atualizarDados() {
        viewModel.setNome(binding.edixtextNome.text.toString())
        viewModel.setProntuario(binding.edixtextProntuario.text.toString())
    }

    private fun notificarErro() {
        Toast.makeText(
            this,
            getString(R.string.erro_prontuario_usado_ou_vazio),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun abrirTelaVotar() {
        val mIntent = Intent(this, VotarActivity::class.java)
        mIntent.putExtra(Constants.KEY_NOME, viewModel.nome.value)
        mIntent.putExtra(Constants.KEY_PRONTUARIO, viewModel.prontuario.value)

        startActivity(mIntent)
        finish()
    }


}