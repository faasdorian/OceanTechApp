package br.com.zenith.oceantechapp.service

import br.com.zenith.oceantechapp.model.Indicador
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface IndicadorService {

    @GET("/relatorios/{idRelatorio}")
    fun getAllIndicadores(@Path("idRelatorio") idRelatorio: Int): Call<List<Indicador>>
}

class IndicadorServiceImpl {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.21:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(IndicadorService::class.java)
}