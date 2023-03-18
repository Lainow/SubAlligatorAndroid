package com.example.projetandroidss.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.Status
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StatusViewModel (app: Application) : AndroidViewModel(app) {
    private val bdd = BDD.getInstance(app)

    class StatusTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val statusApi = retrofit.create(ApiService::class.java)
            val status = statusApi.getStatus().execute().body()
            return Gson().toJson(status)
        }
    }


    fun getJsonString(): String? {
        return StatusTask().execute().get()
    }

    fun getDataApi() : List<Status> {
        val jsonString = getJsonString()
        if (jsonString != null) {
            val gson = Gson()
            return gson.fromJson(jsonString, Array<Status>::class.java).toList()
        }
        else {
            return listOf()
        }

    }

    fun insertDataApi(lists: List<Status>) {
        Thread() {
            for (data in lists) {
                bdd.statusDao().insertOne(data)
            }
        }.start()
    }

    fun insertOne(status: Status) {
        Thread() { bdd.statusDao().insertOne(status) }.start()
    }

    fun insert(vararg status: Status) {
        viewModelScope.launch {
            bdd.statusDao().insert(*status)
        }
    }

    fun insert(status: List<Status>) {
        viewModelScope.launch {
            bdd.statusDao().insert(*status.toTypedArray())
        }
    }

    fun getAll(): LiveData<List<Status>>? {
        var liste: LiveData<List<Status>>? = null;
        var thread = Thread() { liste = bdd.statusDao().getAllStatus() }
        thread.start()
        thread.join()
        return liste
    }

    fun getById(id: Int): Status? {
        var status: Status? = null;
        var thread = Thread() { status = bdd.statusDao().getById(id) }
        thread.start()
        thread.join()
        return status
    }
}