package br.com.zenith.oceantechapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Relatorio : Serializable {
    @SerializedName("id")
    val id: Int = 0

    @SerializedName("data")
    val data: String = ""

    @SerializedName("idPorto")
    val idPorto: Int = 0
}