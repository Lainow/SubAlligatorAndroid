package com.example.projetandroidss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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
                    var context = LocalContext.current
                    Greeting("Android")
                    Text(text = "BBBBB " + BDD.getInstance(context))
                    //val lvlRepository = LevelRepository(application)
                    //var level = bdd.levelDao().get(12)
                    //textView.text = level.name
                    Thread.sleep(1000)
                    BDD.getInstance(context).levelDao().insertOne(Level(id = 12, name = "Lvl12", deleted = false))
                    //lvlViewModel.insert(Level(id = 13, name = "Lvl13", deleted = false))
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