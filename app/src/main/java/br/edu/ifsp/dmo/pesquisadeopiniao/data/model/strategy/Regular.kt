package br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy

class Regular : Opiniao {
    override fun valor() = "Regular"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Opiniao) return false
        return this::class == other::class
    }

    override fun hashCode(): Int {
        return this::class.hashCode()
    }
}