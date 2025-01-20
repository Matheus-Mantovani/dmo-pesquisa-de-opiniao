package br.edu.ifsp.dmo.pesquisadeopiniao.data.model.strategy

interface Opiniao {
    companion object {
        fun toOpiniao(string: String): Opiniao {
            return when(string) {
                "Otimo" -> Otimo()
                "Bom" -> Bom()
                "Regular" -> Regular()
                "Ruim" -> Ruim()
                else -> throw IllegalArgumentException("Opinião inválida: $string")
            }
        }
    }

    fun valor(): String
}