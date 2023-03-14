package com.example.projetandroidss

import com.example.projetandroidss.entities.*
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("level/")
    fun getLevels(): Call<List<Level>>

    @GET("status/")
    fun getStatus(): Call<List<Status>>

    @GET("formation/")
    fun getFormation(): Call<List<Formation>>

    @GET("initiator/")
    fun getInitiator(): Call<List<Initiator>>

    @GET("student/")
    fun getStudent(): Call<List<Student>>
}