package br.edu.ifsp.dmo.pesquisadeopiniao.ui.checar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.pesquisadeopiniao.R
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityChecarBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.main.MainActivity

class ChecarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChecarBinding
    private lateinit var viewModel: ChecarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChecarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ChecarViewModel::class.java]

        configOnClickListener()
    }

    private fun configOnClickListener() {
        binding.buttonChecar.setOnClickListener { checarId() }

        binding.buttonVoltar.setOnClickListener { voltarParaTelaInicial() }
    }

    private fun idValido(id: String): Boolean {
        return viewModel.buscarId(id) != null
    }

    private fun voltarParaTelaInicial() {
        val mIntent = Intent(this, MainActivity::class.java)
        startActivity(mIntent)
        finish()
    }

    private fun checarId() {
        val id = binding.edixtextCodigo.text.toString()
        if(idValido(id)) {
            val voto = viewModel.buscarId(id)
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.sua_opiniao))
                .setMessage(voto!!.opiniao.valor())
                .setNeutralButton(getString(R.string.fechar)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        } else {
            Toast.makeText(
                this,
                getString(R.string.id_nao_encontrado),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}