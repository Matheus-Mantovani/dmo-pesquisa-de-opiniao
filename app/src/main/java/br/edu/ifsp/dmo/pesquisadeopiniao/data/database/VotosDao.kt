package br.edu.ifsp.dmo.pesquisadeopiniao.data.database

import android.content.ContentValues
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.Voto
import br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy.Opiniao


class VotosDao(private val dbHelper: DatabaseHelper) {

    fun insert(voto: Voto) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_ID, voto.id)
            put(DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_OPINIAO, voto.opiniao.valor())
        }

        db.insert(DatabaseHelper.DATABASE_KEYS.TABLE_VOTOS_NAME, null, values)
    }

    fun getAll(): List<Voto> {
        val db = dbHelper.readableDatabase
        val columns = arrayOf(
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_OPINIAO,
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_ID
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
                Voto(
                    Opiniao.toOpiniao(cursor.getString(0)),
                    cursor.getString(1))
            } else {
                null
            }
        }

        return voto
    }

    fun countByOpiniao(opiniao: Opiniao): Int {
        val db = dbHelper.readableDatabase
        val sql = "SELECT COUNT(*) FROM ${DatabaseHelper.DATABASE_KEYS.TABLE_VOTOS_NAME} WHERE ${DatabaseHelper.DATABASE_KEYS.COLUMN_VOTOS_OPINIAO} = ?"
        val cursor = db.rawQuery(sql, arrayOf(opiniao.valor()))

        val count = cursor.use {
            if(cursor.moveToNext()) {
                cursor.getInt(0)
            } else {
                0
            }
        }

        return count
    }
}