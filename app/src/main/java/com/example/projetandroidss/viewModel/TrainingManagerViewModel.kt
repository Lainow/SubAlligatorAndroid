package com.example.projetandroidss.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.TrainingManager
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TrainingManagerViewModel  (app: Application) : AndroidViewModel(app) {
    private val bdd = BDD.getInstance(app)

    class TrainingManagerTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val Api = retrofit.create(ApiService::class.java)
            val apiGet = Api.getTrainingManager().execute().body()
            return Gson().toJson(apiGet)
        }
    }


    fun getJsonString(): String? {
        return TrainingManagerTask().execute().get()
    }

    fun getDataApi() : List<TrainingManager> {
        val jsonString = getJsonString()
        if (jsonString != null) {
            val gson = Gson()
            return gson.fromJson(jsonString, Array<TrainingManager>::class.java).toList()
        }
        else {
            return listOf()
        }

    }

    fun insertDataApi(lists: List<TrainingManager>) {
        Thread() {
            for (data in lists) {
                bdd.trainingManagerDao().insertOne(data)
            }
        }.start()
    }

    fun insertOne(data: TrainingManager) {
        Thread() { bdd.trainingManagerDao().insertOne(data) }.start()
    }

    fun getAll(): List<TrainingManager>? {
        var liste: List<TrainingManager>? = null;
        var thread = Thread() { liste = bdd.trainingManagerDao().getAllTrainingManager() }
        thread.start()
        thread.join()
        return liste
    }
}