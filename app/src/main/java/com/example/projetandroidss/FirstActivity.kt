package com.example.projetandroidss

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.projetandroidss.ui.theme.ProjetAndroidSSTheme
import com.example.projetandroidss.viewModel.*

class FirstActivity : ComponentActivity() {
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
                    Button(modifier = Modifier.fillMaxWidth(), onClick = {
                        val intent = Intent(this@FirstActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    ) {
                        Text("Connexion Sub'Alligators",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp,
                            color = Color.White)
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
        Thread.sleep(1500)
        Thread {
            var lvlInitModel = InitiatorViewModel(application)
            lvlInitModel.insertDataApi(listInitApi)
            var lvlStdModel = StudentViewModel(application)
            lvlStdModel.insertDataApi(listStudApi)
            var lvlSkillModel = SkillViewModel(application)
            lvlSkillModel.insertDataApi(listSkillApi)
        }.start()
        Thread.sleep(1500)
        val listAptiApi = AptitudeViewModel(application).getDataApi()
        val listContainSkillApi = ContainSkillViewModel(application).getDataApi()
        val listSessionViewModel = SessionViewModel(application).getDataApi()
        val listTrainingManagerViewModel = TrainingManagerViewModel(application).getDataApi()
        Thread {
            var lvlAptiModel = AptitudeViewModel(application)
            lvlAptiModel.insertDataApi(listAptiApi)
            var lvlContainSkillModel = ContainSkillViewModel(application)
            lvlContainSkillModel.insertDataApi(listContainSkillApi)
            var lvlSessionModel = SessionViewModel(application)
            lvlSessionModel.insertDataApi(listSessionViewModel)
            var lvlTrainingManagerModel = TrainingManagerViewModel(application)
            lvlTrainingManagerModel.insertDataApi(listTrainingManagerViewModel)
        }.start()
        Thread.sleep(1500)
        val listContentViewModel = ContentViewModel(application).getDataApi()
        Thread {
            var lvlContentModel = ContentViewModel(application)
            lvlContentModel.insertDataApi(listContentViewModel)
        }.start()
        Thread.sleep(1500)
        val listParticipationViewModel = ParticipationViewModel(application).getDataApi()
        Thread {
            var lvlParticipationModel = ParticipationViewModel(application)
            lvlParticipationModel.insertDataApi(listParticipationViewModel)
        }.start()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ProjetAndroidSSTheme {
    }
}