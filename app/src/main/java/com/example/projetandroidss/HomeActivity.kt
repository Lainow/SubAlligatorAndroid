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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.SAVED_STATE_REGISTRY_OWNER_KEY
import com.example.projetandroidss.entities.Level
import com.example.projetandroidss.ui.theme.ProjetAndroidSSTheme
import com.example.projetandroidss.viewModel.LevelViewModel

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
                    val lvlViewModel = LevelViewModel(application)
                    var list : List<Level>? = lvlViewModel.getAll()
                    /*Thread {
                        val lvlViewModel = LevelViewModel(application)
                        lvlViewModel.insertOne(Level(id = 17, name = "Lvl16", deleted = false))
                    }.start()*/
                    Row() {
                        Column() {
                            Text(text = "ID")
                            for (lvl in list!!) {
                                Text(text = lvl.id.toString())
                            }
                        }
                        Column() {
                            Text(text = "Name")
                            for (lvl in list!!) {
                                Text(text = lvl.name)
                            }
                        }
                    }


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