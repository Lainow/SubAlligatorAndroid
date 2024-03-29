package com.example.projetandroidss.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.Skill
import com.example.projetandroidss.entities.Status
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SkillViewModel (app: Application) : AndroidViewModel(app) {
    private val bdd = BDD.getInstance(app)

    class SkillTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val Api = retrofit.create(ApiService::class.java)
            val apiGet = Api.getSkill().execute().body()
            return Gson().toJson(apiGet)
        }
    }


    fun getJsonString(): String? {
        return SkillTask().execute().get()
    }

    fun getDataApi() : List<Skill> {
        val jsonString = getJsonString()
        if (jsonString != null) {
            val gson = Gson()
            return gson.fromJson(jsonString, Array<Skill>::class.java).toList()
        }
        else {
            return listOf()
        }

    }

    fun insertDataApi(lists: List<Skill>) {
        Thread() {
            for (data in lists) {
                bdd.skillDao().insertOne(data)
            }
        }.start()
    }

    fun insertOne(data: Skill) {
        Thread() { bdd.skillDao().insertOne(data) }.start()
    }

    fun getAll(): List<Skill>? {
        var liste: List<Skill>? = null;
        var thread = Thread() { liste = bdd.skillDao().getAllSkill() }
        thread.start()
        thread.join()
        return liste
    }
}