package com.example.projetandroidss.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.Content
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContentViewModel (app: Application) : AndroidViewModel(app) {
    private val bdd = BDD.getInstance(app)

    class ContentTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val Api = retrofit.create(ApiService::class.java)
            val apiGet = Api.getContent().execute().body()
            return Gson().toJson(apiGet)
        }
    }


    fun getJsonString(): String? {
        return ContentTask().execute().get()
    }

    fun getDataApi() : List<Content> {
        val jsonString = getJsonString()
        if (jsonString != null) {
            val gson = Gson()
            return gson.fromJson(jsonString, Array<Content>::class.java).toList()
        }
        else {
            return listOf()
        }

    }

    fun insertDataApi(lists: List<Content>) {
        Thread() {
            for (data in lists) {
                bdd.contentDao().insertOne(data)
            }
        }.start()
    }

    fun insertOne(data: Content) {
        Thread() { bdd.contentDao().insertOne(data) }.start()
    }

    fun getAll(): List<Content>? {
        var liste: List<Content>? = null;
        var thread = Thread() { liste = bdd.contentDao().getAllContent() }
        thread.start()
        thread.join()
        return liste
    }

    fun getBySessionId(sessionId: Int): List<Content>? {
        var liste: List<Content>? = null;
        var thread = Thread() { liste = bdd.contentDao().getBySessionId(sessionId) }
        thread.start()
        thread.join()
        return liste
    }

}