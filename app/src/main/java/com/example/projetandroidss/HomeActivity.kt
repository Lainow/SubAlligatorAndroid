package com.example.projetandroidss

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.projetandroidss.dao.AptitudeDao
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
                    InsertionDonnees(application = application)
                    val list: List<Formation>? = FormationViewModel(application).getAll()
                    val initiateur = InitiatorViewModel(application).getById(1)

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row (modifier = Modifier.fillMaxWidth()){
                            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                                val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
                                if (initiateur != null) {
                                    intent.putExtra("idInitiateur", initiateur.id)
                                    startActivity(intent)
                                }
                            }) {
                                Text("Profil")
                            }
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Text(text = "Liste des Formations",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                            Column() {
                                list?.forEach { data ->
                                    Text(text = data.name)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InsertionDonnees(application: Application) {
    if (AptitudeViewModel(application).count() == 0) {
        //Desinstaller application a chaque 'Run app'
        val listLvlApi = LevelViewModel(application).getLevelsApi()
        val listStsApi = StatusViewModel(application).getDataApi()
        val listFormApi = FormationViewModel(application).getDataApi()
        val listInitApi = InitiatorViewModel(application).getDataApi()
        val listStudApi = StudentViewModel(application).getDataApi()
        val listSkillApi = SkillViewModel(application).getDataApi()
        Thread {
            var lvlViewModel = LevelViewModel(application)
            lvlViewModel.insertLevelApi(listLvlApi)
            var lvlFormModel = FormationViewModel(application)
            lvlFormModel.insertDataApi(listFormApi)
            var lvlStsModel = StatusViewModel(application)
            lvlStsModel.insertDataApi(listStsApi)
        }.start()
        Thread.sleep(2000)
        Thread {
            var lvlInitModel = InitiatorViewModel(application)
            lvlInitModel.insertDataApi(listInitApi)
            var lvlStdModel = StudentViewModel(application)
            lvlStdModel.insertDataApi(listStudApi)
            var lvlSkillModel = SkillViewModel(application)
            lvlSkillModel.insertDataApi(listSkillApi)
        }.start()
        Thread.sleep(2000)
        val listAptiApi = AptitudeViewModel(application).getDataApi()
        Thread {
            var lvlAptiModel = AptitudeViewModel(application)
            lvlAptiModel.insertDataApi(listAptiApi)
        }.start()
        //Thread.sleep(2000)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjetAndroidSSTheme {

    }
}