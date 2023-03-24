package com.example.projetandroidss.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.Participation
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ParticipationViewModel (app: Application) : AndroidViewModel(app) {
    private val bdd = BDD.getInstance(app)

    class ParticipationTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val Api = retrofit.create(ApiService::class.java)
            val apiGet = Api.getParticipation().execute().body()
            return Gson().toJson(apiGet)
        }
    }


    fun getJsonString(): String? {
        return ParticipationTask().execute().get()
    }

    fun getDataApi() : List<Participation> {
        val jsonString = getJsonString()
        if (jsonString != null) {
            val gson = Gson()
            return gson.fromJson(jsonString, Array<Participation>::class.java).toList()
        }
        else {
            return listOf()
        }

    }

    fun insertDataApi(lists: List<Participation>) {
        Thread() {
            for (data in lists) {
                bdd.participationDao().insertOne(data)
            }
        }.start()
    }

    fun insertOne(data: Participation) {
        Thread() { bdd.participationDao().insertOne(data) }.start()
    }

    fun getAll(): List<Participation>? {
        var liste: List<Participation>? = null;
        var thread = Thread() { liste = bdd.participationDao().getAllParticipation() }
        thread.start()
        thread.join()
        return liste
    }

    fun getByStudentAndContentId(studentId: Int, contentId: Int): List<Participation>? {
        var liste: List<Participation>? = null;
        var thread = Thread() { liste = bdd.participationDao().getByStudentAndContentId(studentId, contentId) }
        thread.start()
        thread.join()
        return liste
    }

    fun getByContentId(contentId: Int): List<Participation>? {
        var liste: List<Participation>? = null;
        var thread = Thread() { liste = bdd.participationDao().getByContentId(contentId) }
        thread.start()
        thread.join()
        return liste
    }


}