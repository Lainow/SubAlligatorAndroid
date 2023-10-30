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

    @GET("skill/")
    fun getSkill(): Call<List<Skill>>

    @GET("participation/")
    fun getParticipation(): Call<List<Participation>>

    @GET("containSkill/")
    fun getContainSkill(): Call<List<ContainSkill>>

    @GET("content/")
    fun getContent(): Call<List<Content>>

    @GET("session/")
    fun getSession(): Call<List<Session>>

    @GET("trainingManager/")
    fun getTrainingManager(): Call<List<TrainingManager>>

    @GET("aptitude/")
    fun getAptitude(): Call<List<Aptitude>>
}