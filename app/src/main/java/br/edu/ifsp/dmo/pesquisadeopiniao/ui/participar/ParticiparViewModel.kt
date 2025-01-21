package br.edu.ifsp.dmo.pesquisadeopiniao.ui.participar

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.dmo.pesquisadeopiniao.data.repository.ParticipantesRepository
import br.edu.ifsp.dmo.pesquisadeopiniao.utils.Constants

class ParticiparViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: ParticipantesRepository = ParticipantesRepository(application)

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
            Log.i(Constants.KEY_TESTE, "nulo ou vazio")
            return false
        }

        //retorna false caso tenha achado esse prontuario no banco
        if(repository.getByProntuario(_prontuario.value!!) != null) {
            Log.i(Constants.KEY_TESTE, "repo nulo")
            return false
        }

        return true
    }
}