package com.example.projetandroidss.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.Aptitude
import com.example.projetandroidss.entities.Skill
import com.example.projetandroidss.entities.Status
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AptitudeViewModel (app: Application) : AndroidViewModel(app) {
    private val bdd = BDD.getInstance(app)

    class AptitudeTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val Api = retrofit.create(ApiService::class.java)
            val apiGet = Api.getAptitude().execute().body()
            return Gson().toJson(apiGet)
        }
    }


    fun getJsonString(): String? {
        return AptitudeTask().execute().get()
    }

    fun getDataApi() : List<Aptitude> {
        val jsonString = getJsonString()
        if (jsonString != null) {
            val gson = Gson()
            return gson.fromJson(jsonString, Array<Aptitude>::class.java).toList()
        }
        else {
            return listOf()
        }

    }

    fun insertDataApi(lists: List<Aptitude>) {
        Thread() {
            for (data in lists) {
                bdd.aptitudeDao().insertOne(data)
            }
        }.start()
    }

    fun insertOne(data: Aptitude) {
        Thread() { bdd.aptitudeDao().insertOne(data) }.start()
    }

    fun getAll(): List<Aptitude>? {
        var liste: List<Aptitude>? = null;
        var thread = Thread() { liste = bdd.aptitudeDao().getAllAptitude() }
        thread.start()
        thread.join()
        return liste
    }

    fun count() : Int {
        var count = 0
        var thread = Thread() { count = bdd.aptitudeDao().count() }
        thread.start()
        thread.join()
        return count
    }

    fun getById(id: Int) : Aptitude {
        var aptitude: Aptitude? = null
        var thread = Thread() { aptitude = bdd.aptitudeDao().getById(id) }
        thread.start()
        thread.join()
        return aptitude!!
    }
}