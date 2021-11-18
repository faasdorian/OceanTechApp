package br.com.zenith.oceantechapp.service

import br.com.zenith.oceantechapp.model.Porto
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PortoService {

    @GET("/portos/lista")
    fun getAllPortos(): Call<List<Porto>>
}

class PortoServiceImpl {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.21:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(PortoService::class.java)
}