package br.edu.ifsp.dmo.pesquisadeopiniao.data.database

import android.content.ContentValues
import android.util.Log
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Participante
import br.edu.ifsp.dmo.pesquisadeopiniao.utils.Constants

class ParticipantesDao(private val dbHelper: DatabaseHelper) {

    fun insert(participante: Participante) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.DATABASE_KEYS.COLUMN_PARTICIPANTES_PRONTUARIO, participante.prontuario)
            Log.i(Constants.KEY_TESTE,"DAO: " + participante.prontuario)
            put(DatabaseHelper.DATABASE_KEYS.TABLE_PARTICIPANTES_NAME, participante.nome)
            Log.i(Constants.KEY_TESTE,"DAO: " + participante.nome)
        }

        db.insert(DatabaseHelper.DATABASE_KEYS.TABLE_PARTICIPANTES_NAME, null, values)
    }

    fun getAll(): List<Participante> {
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            DatabaseHelper.DATABASE_KEYS.COLUMN_PARTICIPANTES_PRONTUARIO,
            DatabaseHelper.DATABASE_KEYS.COLUMN_PARTICIPANTES_NOME
        )

        val cursor = db.query(DatabaseHelper.DATABASE_KEYS.TABLE_PARTICIPANTES_NAME,
            columns,
            null,
            null,
            null,
            null,
            null)
        val participantes = mutableListOf<Participante>()

        cursor.use {
            while (it.moveToNext()) {
                participantes.add(
                    Participante(
                        prontuario = it.getString(0),
                        nome = it.getString(1)
                    )
                )
            }
        }

        return participantes
    }

    fun getByProntuario(prontuario: String): Participante? {
        val participante: Participante?
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            DatabaseHelper.DATABASE_KEYS.COLUMN_PARTICIPANTES_PRONTUARIO,
            DatabaseHelper.DATABASE_KEYS.COLUMN_PARTICIPANTES_NOME
        )

        val where = "${DatabaseHelper.DATABASE_KEYS.COLUMN_PARTICIPANTES_PRONTUARIO} = ?"
        val whereArgs = arrayOf(prontuario)

        val cursor = db.query(
            DatabaseHelper.DATABASE_KEYS.TABLE_PARTICIPANTES_NAME,
            columns,
            where,
            whereArgs,
            null,
            null,
            null
        )

        cursor.use {
            participante = if(cursor.moveToNext()) {
                Log.i(Constants.KEY_TESTE, "PARTICIPANTE ENCONTRADO: ")
                Participante(cursor.getString(0), cursor.getString(1))
            } else {
                null
            }
        }

        if (participante != null) {
            Log.i(Constants.KEY_TESTE, "DAO PARTICIPANTE: " + participante.nome)
            Log.i(Constants.KEY_TESTE, "DAO PARTICIPANTE: " + participante.prontuario)
        } else {
            Log.i(Constants.KEY_TESTE,"DAO PARTICIPANTES NULLdasd")
        }

        return participante
    }
}