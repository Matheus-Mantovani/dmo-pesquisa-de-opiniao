package br.edu.ifsp.dmo.pesquisadeopiniao.ui.participar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.ParticipantesRepository

class ParticiparViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ParticipantesRepository = ParticipantesRepository(application)

    private val _nome = MutableLiveData<String>()
    val nome: LiveData<String> = _nome

    private val _prontuario = MutableLiveData<String>()
    val prontuario: LiveData<String> = _prontuario


    fun setNome(nome: String) {
        _nome.value = nome
    }

    fun setProntuario(prontuario: String) {
        _prontuario.value = prontuario
    }

    //retorna false caso o prontuario esteja null ou blank
    fun dadosValidos(): Boolean {
        if(_prontuario.value.isNullOrBlank()) {
            return false
        }

        //retorna false caso tenha achado esse prontuario no banco
        if(repository.getByProntuario(_prontuario.value!!) != null) {
            return false
        }

        return true
    }
}