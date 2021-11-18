package br.com.zenith.oceantechapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Porto: Serializable {
    @SerializedName("id")
    val id: Int = 0

    @SerializedName("nome")
    val nome: String = ""
}