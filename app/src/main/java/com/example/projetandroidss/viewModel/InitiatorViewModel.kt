package com.example.projetandroidss.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.Initiator
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class InitiatorViewModel(app: Application) : AndroidViewModel(app) {

    private val bdd = BDD.getInstance(app)

    class InitiatorsTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(ApiService::class.java)
            val datas = api.getInitiator().execute().body()
            return Gson().toJson(datas)
        }
    }

    fun getJsonString(): String? {
        return InitiatorsTask().execute().get()
    }

    fun getDataApi() : List<Initiator> {
        val jsonString = getJsonString()
        if (jsonString != null) {
            val gson = Gson()
            return gson.fromJson(jsonString, Array<Initiator>::class.java).toList()
        }
        else {
            return listOf()
        }

    }

    fun insertOne(obj: Initiator) {
        Thread() { bdd.initiatorDao().insertOne(obj) }.start()
    }


    fun insert(vararg data: Initiator) {
        viewModelScope.launch {
            bdd.initiatorDao().insert(*data)
        }
    }

    fun insertDataApi(lists: List<Initiator>) {
        Thread() {
            for (data in lists) {
                bdd.initiatorDao().insertOne(data)
            }
        }.start()
    }

    fun insert(lists: List<Initiator>) {
        viewModelScope.launch {
            bdd.initiatorDao().insert(*lists.toTypedArray())
        }
    }

    fun update(obj: Initiator) {
        Thread() {
            bdd.initiatorDao().update(obj)}.start()
    }

    fun updateApiData(obj: Initiator) {
        Thread() {
            bdd.initiatorDao().update(obj)}.start()
    }

    // Cette fonction permet de récupérer tous les niveaux dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): List<Initiator>? {
        var liste: List<Initiator>? = null;
        var thread = Thread() { liste = bdd.initiatorDao().getAllInitiator() }
        thread.start()
        thread.join()
        return liste
    }

    fun getByMailPassword(mail: String, password: String): Initiator? {
        var initiator: Initiator? = null;
        var thread = Thread() { initiator = bdd.initiatorDao().getByMailPassword(mail, password) }
        thread.start()
        thread.join()
        return initiator
    }

    fun getById(i: Int): Initiator? {
        var initiator: Initiator? = null;
        var thread = Thread() { initiator = bdd.initiatorDao().getById(i) }
        thread.start()
        thread.join()
        return initiator
    }
}
