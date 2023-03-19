package com.example.projetandroidss.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.Session
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SessionViewModel  (app: Application) : AndroidViewModel(app) {
    private val bdd = BDD.getInstance(app)

    class SessionTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val Api = retrofit.create(ApiService::class.java)
            val apiGet = Api.getSession().execute().body()
            return Gson().toJson(apiGet)
        }
    }


    fun getJsonString(): String? {
        return SessionTask().execute().get()
    }

    fun getDataApi() : List<Session> {
        val jsonString = getJsonString()
        if (jsonString != null) {
            val gson = Gson()
            return gson.fromJson(jsonString, Array<Session>::class.java).toList()
        }
        else {
            return listOf()
        }

    }

    fun insertDataApi(lists: List<Session>) {
        Thread() {
            for (data in lists) {
                bdd.sessionDao().insertOne(data)
            }
        }.start()
    }

    fun insertOne(data: Session) {
        Thread() { bdd.sessionDao().insertOne(data) }.start()
    }

    fun getAll(): List<Session>? {
        var liste: List<Session>? = null;
        var thread = Thread() { liste = bdd.sessionDao().getAllSession() }
        thread.start()
        thread.join()
        return liste
    }

    fun getByFormationId(formationId: Int): List<Session>? {
        var liste: List<Session>? = null;
        var thread = Thread() { liste = bdd.sessionDao().getByFormationId(formationId) }
        thread.start()
        thread.join()
        return liste
    }
}