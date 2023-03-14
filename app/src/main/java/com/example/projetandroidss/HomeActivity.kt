package com.example.projetandroidss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.projetandroidss.entities.*
import com.example.projetandroidss.ui.theme.ProjetAndroidSSTheme
import com.example.projetandroidss.viewModel.*

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetAndroidSSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Desinstaller application a chaque 'Run app'
                    val listLvlApi = LevelViewModel(application).getLevelsApi()
                    val listStsApi = StatusViewModel(application).getDataApi()
                    val listFormApi = FormationViewModel(application).getDataApi()
                    val listInitApi = InitiatorViewModel(application).getDataApi()
                    val listStudApi = StudentViewModel(application).getDataApi()
                    Thread {
                        var lvlViewModel = LevelViewModel(application)
                        lvlViewModel.insertLevelApi(listLvlApi)
                        var lvlFormModel = FormationViewModel(application)
                        lvlFormModel.insertDataApi(listFormApi)
                        var lvlStsModel = StatusViewModel(application)
                        lvlStsModel.insertDataApi(listStsApi)
                        var lvlInitModel = InitiatorViewModel(application)
                        lvlInitModel.insertDataApi(listInitApi)
                        var lvlStdModel = StudentViewModel(application)
                        lvlStdModel.insertDataApi(listStudApi)
                    }.start()
                    Thread.sleep(2000)

                    var list : List<Initiator>? = InitiatorViewModel(application).getAll()
                    Thread.sleep(1000)
                    Row() {
                        Column() {
                            Text(text = "ID")
                            for (data in list!!) {
                                Text(text = data.id.toString())
                            }
                        }
                        Column() {
                            Text(text = "Name")
                            for (data in list!!) {
                                Text(text = data.name + " " + data.email)
                            }
                        }
                    }
                    /*Row() {
                        Column() {
                            Text(text = "Level")
                            for (data in listLvlApi) {
                                Text(text = data.id.toString() + data.name)
                            }
                        }
                        Column() {
                            Text(text = "Status")
                            for (data in listStsApi) {
                                Text(text = data.id.toString() + data.name)
                            }
                        }
                        Column() {
                            Text(text = "Formation")
                            for (data in listFormApi) {
                                Text(text = data.id.toString() + data.name)
                            }
                        }
                    }*/


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjetAndroidSSTheme {
        Greeting("Android")
    }
}