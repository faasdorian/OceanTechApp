package br.com.zenith.oceantechapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Indicador: Serializable {

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("nome")
    val nome: String = ""

    @SerializedName("valor")
    val valor: Double = 0.0

    @SerializedName("idRelatorio")
    val idRelatorio: Int = 0

    @SerializedName("parametro")
    val parametro: String = ""

}