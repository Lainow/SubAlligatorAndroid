package com.example.projetandroidss.repositories

import android.app.Application
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.projetandroidss.ApiService
import com.example.projetandroidss.BDD
import com.example.projetandroidss.dao.LevelDao
import com.example.projetandroidss.entities.Level
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LevelRepository internal constructor(application: Application) {

    private val levelDao: LevelDao
    private val allLevels: List<Level>
    val db = BDD.getInstance(application)
    init {

        levelDao = db.levelDao()
        allLevels = levelDao.getAllLevel()
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("https://dev-restandroid.users.info.unicaen.fr/REST/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val aptitudeApi = retrofit.create(ApiService::class.java)

    fun getAllLevel(): List<Level>?{
        var liste: List<Level>? = null
        var thread = Thread() { liste = db.levelDao().getAllLevel() }
        thread.start()
        thread.join()
        return liste
     }


    fun insertListDataAsync(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            var levelDao = BDD.getInstance(context).levelDao()
            val listLevel = aptitudeApi.getLevel()
            for (level in listLevel) {
                levelDao.insertLevel(level)
            }
        }
    }

}