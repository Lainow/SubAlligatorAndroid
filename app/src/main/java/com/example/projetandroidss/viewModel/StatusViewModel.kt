package com.example.projetandroidss.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projetandroidss.BDD
import com.example.projetandroidss.entities.Level
import com.example.projetandroidss.entities.Status
import kotlinx.coroutines.launch

class StatusViewModel (app: Application) : AndroidViewModel(app) {

    private val bdd = BDD.getInstance(app)

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
}