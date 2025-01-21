package br.edu.ifsp.dmo.pesquisadeopiniao.data.repository

import android.content.Context
import br.edu.ifsp.dmo.pesquisadeopiniao.data.database.DatabaseHelper
import br.edu.ifsp.dmo.pesquisadeopiniao.data.database.VotosDao
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Opiniao

class VotosRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private val dao = VotosDao(dbHelper)

    fun insert(voto: Voto) = dao.insert(voto)

    fun getAll() = dao.getAll()

    fun getById(id: String) = dao.getById(id)

    fun countByOpiniao(opiniao: Opiniao) = dao.countByOpiniao(opiniao)
}