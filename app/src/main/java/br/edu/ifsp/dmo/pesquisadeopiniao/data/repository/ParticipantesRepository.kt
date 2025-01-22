package br.edu.ifsp.dmo.pesquisadeopiniao.data.repository

import android.content.Context
import br.edu.ifsp.dmo.pesquisadeopiniao.data.database.DatabaseHelper
import br.edu.ifsp.dmo.pesquisadeopiniao.data.database.ParticipantesDao
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Participante

class ParticipantesRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private val dao = ParticipantesDao(dbHelper)

    fun insert(participante: Participante) = dao.insert(participante)

    fun getAll() = dao.getAll()

    fun getByProntuario(prontuario: String) = dao.getByProntuario(prontuario)

    fun getTotalParticipantes() = dao.countParticipantes()
}