package br.edu.ifsp.dmo.pesquisadeopiniao.ui.checar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.VotosRepository

class ChecarViewModel(application: Application) : AndroidViewModel(application) {
    private val votosRepository: VotosRepository = VotosRepository(application)

    fun buscarId(id: String): Voto? {
        return votosRepository.getById(id)
    }
}