package br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy

class Ruim : Opiniao {
    override fun valor() = "Ruim"

    //override no equals e no hascode para conseguir acessar os valores da viewModel na finalizarActivity
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Opiniao) return false
        return this::class == other::class
    }

    override fun hashCode(): Int {
        return this::class.hashCode()
    }
}