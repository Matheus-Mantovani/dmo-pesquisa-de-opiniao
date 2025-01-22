package br.edu.ifsp.dmo.pesquisadeopiniao.ui.votar

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.dmo.pesquisadeopiniao.data.database.DatabaseHelper
import br.edu.ifsp.dmo.pesquisadeopiniao.data.database.ParticipantesDao
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Participante
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Opiniao
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.ParticipantesRepository
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.VotosRepository
import br.edu.ifsp.dmo.pesquisadeopiniao.utils.Constants

class VotarViewModel(application: Application) : AndroidViewModel(application) {
    private val votosRepository: VotosRepository = VotosRepository(application)
    private val participantesRepository: ParticipantesRepository = ParticipantesRepository(application)

    private val dbHelper = DatabaseHelper(application)
    private val participantesDao: ParticipantesDao = ParticipantesDao(dbHelper)

    private val _opiniao = MutableLiveData<Opiniao>()
    val opiniao: LiveData<Opiniao> = _opiniao

    private val _prontuario = MutableLiveData<String>()
    val prontuario: LiveData<String> = _prontuario

    private val _nome = MutableLiveData<String>()
    val nome: LiveData<String> = _nome

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    init {
        _id.value = gerarId()
    }

    fun setOpiniao(opiniao: Opiniao) {
        _opiniao.value = opiniao
    }

    fun setProntuario(prontuario: String) {
        _prontuario.value = prontuario
    }

    fun setNome(nome: String) {
        _nome.value = nome
    }

    fun adicionarVoto() {
        votosRepository.insert(
            Voto(_opiniao.value!!, id.value!!)
        )

        val participantesAntes = participantesRepository

        participantesRepository.insert(
            Participante(_prontuario.value!!, _nome.value!!)
        )


        val participantesDepois = participantesDao.countParticipantes()
        Log.i(Constants.KEY_TESTE, "Participantes antes: $participantesAntes, depois: $participantesDepois")

    }

    private fun gerarId(): String {
        val caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        var id: String

        do {
            id = (1..10)
                .map { caracteres.random() }
                .joinToString("")
        } while (idEmUso(id))

        return id
    }

    private fun idEmUso(id: String): Boolean {
        return votosRepository.getById(id) != null
    }
}