package br.edu.ifsp.dmo.pesquisadeopiniao.data.database

import android.content.ContentValues
import android.util.Log
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Opiniao
import br.edu.ifsp.dmo.pesquisadeopiniao.utils.Constants


class VotosDao(private val dbHelper: DatabaseHelper) {

    fun insert(voto: Voto) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_ID, voto.id)
            Log.i(Constants.KEY_TESTE, "DAO VOTO: " + voto.id)
            put(DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_OPINIAO, voto.opiniao.valor())
            Log.i(Constants.KEY_TESTE, "DAO VOTO: " + (voto.opiniao).valor())
        }

        db.insert(DatabaseHelper.DATABASE_KEYS.TABLE_VOTOS_NAME, null, values)
    }

    fun getAll(): List<Voto> {
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_ID,
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_OPINIAO
        )

        val cursor = db.query(DatabaseHelper.DATABASE_KEYS.TABLE_VOTOS_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )
        val votos = mutableListOf<Voto>()

        cursor.use {
            while (it.moveToNext()) {
                votos.add(
                    Voto(
                        Opiniao.toOpiniao(it.getString(0)), //metodo estatico toOpiniao para transformar a string do banco em uma classe Opiniao
                        it.getString(1))
                )
            }
        }

        return votos
    }

    fun getById(id: String): Voto? {
        val voto: Voto?
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_OPINIAO,
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_ID
        )

        val where = "${DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_ID} = ?"
        val whereArgs = arrayOf(id)

        val cursor = db.query(DatabaseHelper.DATABASE_KEYS.TABLE_VOTOS_NAME,
            columns,
            where,
            whereArgs,
            null,
            null,
            null
        )

        cursor.use {
            voto = if(cursor.moveToNext()) {
                Log.i(Constants.KEY_TESTE, "DAO GETBYID VOTO VALORES CURSOR OPINIAO: " + cursor.getString(0))
                Log.i(Constants.KEY_TESTE, "DAO GETBYID VOTO VALORES CURSOR ID: " + cursor.getString(1))
                Voto(
                    Opiniao.toOpiniao(cursor.getString(0)),
                    cursor.getString(1))
            } else {
                null
            }
        }

        if (voto != null) {
            Log.i(Constants.KEY_TESTE, "DAO VOTO GETBYID: " + voto.id)
            Log.i(Constants.KEY_TESTE, "DAO VOTO GETBYID: " + voto.opiniao)
        }

        return voto
    }

    fun countByOpiniao(opiniao: Opiniao): Int {
        val db = dbHelper.readableDatabase
        val sql = "SELECT COUNT(*) FROM ${DatabaseHelper.DATABASE_KEYS.TABLE_VOTOS_NAME} WHERE ${DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_OPINIAO} = ?"
        val cursor = db.rawQuery(sql, arrayOf(opiniao.valor()))

        val count = cursor.use {
            cursor.getInt(0)
        }

        return count
    }
}