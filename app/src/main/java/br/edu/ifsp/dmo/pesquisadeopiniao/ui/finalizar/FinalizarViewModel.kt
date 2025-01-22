package br.edu.ifsp.dmo.pesquisadeopiniao.ui.finalizar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Bom
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Opiniao
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Otimo
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Regular
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Ruim
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.VotosRepository

class FinalizarViewModel(application: Application) : AndroidViewModel(application) {
    private val votosRepository: VotosRepository = VotosRepository(application)

    private val _qtdParticipantes = MutableLiveData<Int>()
    val qtdParticipantes: LiveData<Int> = _qtdParticipantes

    private val _opinioes = MutableLiveData<HashMap<Opiniao, Int>>()
    val opinioes: LiveData<HashMap<Opiniao, Int>> = _opinioes

    init {
        carregarDados()
    }

    private fun carregarDados() {
        _qtdParticipantes.value = votosRepository.getAll().size

        _opinioes.value = hashMapOf(
            Pair(Otimo(), votosRepository.countByOpiniao(Otimo())),
            Pair(Bom(), votosRepository.countByOpiniao(Bom())),
            Pair(Regular(), votosRepository.countByOpiniao(Regular())),
            Pair(Ruim(), votosRepository.countByOpiniao(Ruim()))
        )
    }
}