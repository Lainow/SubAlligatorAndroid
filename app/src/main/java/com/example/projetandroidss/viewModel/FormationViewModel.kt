package com.example.projetandroidss.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.Formation
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FormationViewModel(app: Application) : AndroidViewModel(app) {

    private val bdd = BDD.getInstance(app)


    class FormationsTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(ApiService::class.java)
            val datas = api.getFormation().execute().body()
            return Gson().toJson(datas)
        }
    }

    fun getJsonString(): String? {
        return FormationsTask().execute().get()
    }

    fun getDataApi() : List<Formation> {
        val jsonString = getJsonString()
        if (jsonString != null) {
            val gson = Gson()
            return gson.fromJson(jsonString, Array<Formation>::class.java).toList()
        }
        else {
            return listOf()
        }

    }

    fun insertOne(obj: Formation) {
        Thread() { bdd.formationDao().insertOne(obj) }.start()
    }


    fun insert(vararg data: Formation) {
        viewModelScope.launch {
            bdd.formationDao().insert(*data)
        }
    }

    fun insertDataApi(lists: List<Formation>) {
        Thread() {
            for (data in lists) {
                bdd.formationDao().insertOne(data)
            }
        }.start()
    }
    fun insert(lists: List<Formation>) {
        viewModelScope.launch {
            bdd.formationDao().insert(*lists.toTypedArray())
        }
    }

    // Cette fonction permet de récupérer tous les niveaux dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): List<Formation>? {
        var liste: List<Formation>? = null;
        var thread = Thread() { liste = bdd.formationDao().getAllFormation() }
        thread.start()
        thread.join()
        return liste
    }
}