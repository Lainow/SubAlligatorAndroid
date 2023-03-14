package com.example.projetandroidss.viewModel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.Student
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentViewModel(app: Application) : AndroidViewModel(app) {

    private val bdd = BDD.getInstance(app)


    class StudentsTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String? {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(ApiService::class.java)
            val datas = api.getStudent().execute().body()
            return Gson().toJson(datas)
        }
    }

    fun getJsonString(): String? {
        return StudentsTask().execute().get()
    }

    fun getDataApi() : List<Student> {
        val jsonString = getJsonString()
        if (jsonString != null) {
            val gson = Gson()
            return gson.fromJson(jsonString, Array<Student>::class.java).toList()
        }
        else {
            return listOf()
        }

    }

    fun insertOne(obj: Student) {
        Thread() { bdd.studentDao().insertOne(obj) }.start()
    }


    fun insert(vararg data: Student) {
        viewModelScope.launch {
            bdd.studentDao().insert(*data)
        }
    }

    fun insertDataApi(lists: List<Student>) {
        Thread() {
            for (data in lists) {
                bdd.studentDao().insertOne(data)
            }
        }.start()
    }
    fun insert(lists: List<Student>) {
        viewModelScope.launch {
            bdd.studentDao().insert(*lists.toTypedArray())
        }
    }

    // Cette fonction permet de récupérer tous les niveaux dans la base de données
    // Elle utilise une Thread pour exécuter la requête, car les requêtes sur le thread principal sont interdites
    // Elle retourne un objet LiveData, qui permet de recevoir des mises à jour automatiques lorsque la base de données change
    fun getAll(): List<Student>? {
        var liste: List<Student>? = null;
        var thread = Thread() { liste = bdd.studentDao().getAllStudent() }
        thread.start()
        thread.join()
        return liste
    }
}