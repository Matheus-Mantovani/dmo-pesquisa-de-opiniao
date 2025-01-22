package br.edu.ifsp.dmo.pesquisadeopiniao.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import br.edu.ifsp.dmo.pesquisadeopiniao.utils.Constants

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_KEYS.DATABASE_NAME, null, DATABASE_KEYS.DATABASE_VERSION) {

    object DATABASE_KEYS {
        const val DATABASE_NAME = "pesquisa_qualidade.db"
        const val DATABASE_VERSION = 1

        const val TABLE_PARTICIPANTES_NAME = "tb_participantes"
        const val COLUMN_PARTICIPANTES_NOME = "nome"
        const val COLUMN_PARTICIPANTES_PRONTUARIO = "prontuario"

        const val TABLE_VOTOS_NAME = "tb_votos"
        const val COLUMN_VOTOS_ID = "id"
        const val COLUMN_VOTOS_OPINIAO = "opiniao"
    }

    private companion object {
        const val CREATE_TABLE_PARTICIPANTES = "CREATE TABLE ${DATABASE_KEYS.TABLE_PARTICIPANTES_NAME} (" +
                "${DATABASE_KEYS.COLUMN_PARTICIPANTES_PRONTUARIO} TEXT PRIMARY KEY NOT NULL," +
                "${DATABASE_KEYS.COLUMN_PARTICIPANTES_NOME} TEXT NOT NULL)"

        const val CREATE_TABLE_VOTOS = "CREATE TABLE ${DATABASE_KEYS.TABLE_VOTOS_NAME} (" +
                "${DATABASE_KEYS.COLUMN_VOTOS_ID} TEXT PRIMARY KEY NOT NULL, " +
                "${DATABASE_KEYS.COLUMN_VOTOS_OPINIAO} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.i(Constants.KEY_TESTE,"criando tabela participantes")
        db.execSQL(CREATE_TABLE_PARTICIPANTES)
        Log.i(Constants.KEY_TESTE,"criando tabela votos")
        db.execSQL(CREATE_TABLE_VOTOS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}