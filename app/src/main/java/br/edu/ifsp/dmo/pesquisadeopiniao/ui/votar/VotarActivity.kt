package br.edu.ifsp.dmo.pesquisadeopiniao.ui.votar

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.pesquisadeopiniao.R
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Bom
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Otimo
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Regular
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Ruim
import br.edu.ifsp.dmo.pesquisadeopiniao.databinding.ActivityVotarBinding
import br.edu.ifsp.dmo.pesquisadeopiniao.ui.main.MainActivity
import br.edu.ifsp.dmo.pesquisadeopiniao.utils.Constants

class VotarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVotarBinding
    private lateinit var viewModel: VotarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVotarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[VotarViewModel::class.java]

        openBundle()
        configOnClickListener()
    }


    private fun configOnClickListener() {
        Log.i(Constants.KEY_TESTE, "configOnClickListener")
        binding.buttonEnviar.setOnClickListener {
            Log.i(Constants.KEY_TESTE, "dentro de setOnClickListener")
            if(opiniaoSelecionada()) {
                val opiniao = when(binding.radiogroupVotar.checkedRadioButtonId) {
                    R.id.radiobutton_otimo -> Otimo()
                    R.id.radiobutton_bom -> Bom()
                    R.id.radiobutton_regular -> Regular()
                    R.id.radiobutton_ruim -> Ruim()
                    else -> throw IllegalArgumentException(getString(R.string.opiniao_invalida))
                }

                Log.i(Constants.KEY_TESTE, "valor activity " + opiniao.valor())

                viewModel.setOpiniao(opiniao)

                Log.i(Constants.KEY_TESTE, "valor viewmodel " + (viewModel.opiniao.value).toString())

                viewModel.adicionarVoto()

                mostrarId()

                Log.i(Constants.KEY_TESTE, "id: " + viewModel.id.value.toString())

            } else {
                Log.i(Constants.KEY_TESTE, "opcao nao selecionada")
                Toast.makeText(
                    this,
                    getString(R.string.selecione_uma_das_opcoes),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun voltarParaTelaInicial() {
        val mIntent = Intent(this, MainActivity::class.java)
        Log.i(Constants.KEY_TESTE, "voltar pra tela inicial ")
        startActivity(mIntent)
        finish()
    }

    private fun mostrarId() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.seu_id))
            .setMessage(viewModel.id.value)
            .setPositiveButton(getString(R.string.fechar)) { dialog, _ ->
                dialog.dismiss()
            }
            .setNeutralButton(getString(R.string.copiar_id)) { _, _ ->
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText(getString(R.string.id_copiado), viewModel.id.value)
                clipboard.setPrimaryClip(clip)
            }
            .setOnDismissListener { voltarParaTelaInicial() }
            .show()
        Log.i(Constants.KEY_TESTE, "dialog na tela ")
    }

    private fun opiniaoSelecionada(): Boolean {
        val opiniao = binding.radiogroupVotar.checkedRadioButtonId
        return opiniao > -1
    }

    private fun openBundle() {
        val extras = intent.extras
        if(extras != null) {
            val nome = extras.getString(Constants.KEY_NOME)
            val prontuario = extras.getString(Constants.KEY_PRONTUARIO)
            if(nome != null && prontuario != null) {
                viewModel.setNome(nome)
                viewModel.setProntuario(prontuario)

                Log.i(Constants.KEY_TESTE, "nome votar: " + viewModel.nome.value!!)
                Log.i(Constants.KEY_TESTE, "prontuario votar: " + viewModel.prontuario.value!!)
            }
        } else {
            Toast.makeText(
                this,
                getString(R.string.erro_ao_recuperar_nome_prontuario),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}