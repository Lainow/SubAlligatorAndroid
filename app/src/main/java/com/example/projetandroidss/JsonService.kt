package com.example.projetandroidss

import com.example.projetandroidss.entities.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

public object JsonService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dev-restandroid.users.info.unicaen.fr/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApti(): ApiServiceApti {
        return retrofit.create(ApiServiceApti::class.java)
    }
}

interface ApiServiceApti {
    @GET("REST/aptitude/")
    suspend fun getApt(): List<Aptitude>
}

interface ApiServiceContainSkill {
    @GET("REST/containSkill/")
    suspend fun getCS(): List<ContainSkill>
}

interface ApiServiceContent {
    @GET("REST/content/")
    suspend fun getAPt(): List<Content>
}
