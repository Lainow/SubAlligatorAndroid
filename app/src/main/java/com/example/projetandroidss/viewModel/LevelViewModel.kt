package com.example.projetandroidss.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.Level
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LevelViewModel(app: Application) : AndroidViewModel(app) {

    // On crée une instance de la base de données
    private val bdd = BDD.getInstance(app)


    class LevelsTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val levelApi = retrofit.create(ApiService::class.java)
            val levels = levelApi.getLevels().execute().body()
            return Gson().toJson(levels)
        }
    }

    fun getLevelsJsonString(): String? {
        return LevelsTask().execute().get()
    }

    fun getLevelsApi() : List<Level> {
        val jsonString = getLevelsJsonString()
        if (jsonString != null) {
            val gson = Gson()
            return gson.fromJson(jsonString, Array<Level>::class.java).toList()
        }
        else {
            return listOf()
        }

    }

    fun insertOne(level: Level) {
        Thread() { bdd.levelDao().insertOne(level) }.start()
    }


    fun insert(vararg levels: Level) {
        viewModelScope.launch {
            bdd.levelDao().insert(*levels)
        }
    }

    fun insertLevelApi(levels: List<Level>) {
        Thread() {
            for (level in levels) {
                bdd.levelDao().insertOne(level)
            }
        }.start()
    }
    fun insert(levels: List<Level>) {
        viewModelScope.launch {
            bdd.levelDao().insert(*levels.toTypedArray())
        }
    }

    // Cette fonction permet de récupérer tous les niveaux dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): List<Level>? {
        var liste: List<Level>? = null;
        var thread = Thread() { liste = bdd.levelDao().getAllLevel() }
        thread.start()
        thread.join()
        return liste
    }
}
