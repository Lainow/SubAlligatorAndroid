package com.example.projetandroidss.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.Aptitude
import com.example.projetandroidss.entities.ContainSkill
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ContainSkillViewModel (app: Application) : AndroidViewModel(app) {
    private val bdd = BDD.getInstance(app)

    class ConSkillTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val Api = retrofit.create(ApiService::class.java)
            val apiGet = Api.getContainSkill().execute().body()
            return Gson().toJson(apiGet)
        }
    }


    fun getJsonString(): String? {
        return ConSkillTask().execute().get()
    }

    fun getDataApi() : List<ContainSkill> {
        val jsonString = getJsonString()
        if (jsonString != null) {
            val gson = Gson()
            return gson.fromJson(jsonString, Array<ContainSkill>::class.java).toList()
        }
        else {
            return listOf()
        }

    }

    fun insertDataApi(lists: List<ContainSkill>) {
        Thread() {
            for (data in lists) {
                bdd.containSkillDao().insertOne(data)
            }
        }.start()
    }

    fun insertOne(data: ContainSkill) {
        Thread() { bdd.containSkillDao().insertOne(data) }.start()
    }

    fun getAll(): List<ContainSkill>? {
        var liste: List<ContainSkill>? = null;
        var thread = Thread() { liste = bdd.containSkillDao().getAllContainSkill() }
        thread.start()
        thread.join()
        return liste
    }
}