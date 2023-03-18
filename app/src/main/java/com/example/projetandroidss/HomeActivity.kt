package com.example.projetandroidss

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    color = MaterialTheme.colors.background,

                ) {
                    val list: List<Formation>? = FormationViewModel(application).getAll()
                    val initiateur = InitiatorViewModel(application).getById(1)
                    val deepBlue = Color(0xFF004794)

                    Box(modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row (modifier = Modifier.fillMaxWidth()) {
                                Button(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(deepBlue), onClick = {
                                    val intent = Intent(this@HomeActivity, ProfileActivity::class.java)
                                    if (initiateur != null) {
                                        intent.putExtra("idInitiateur", initiateur.id)
                                        startActivity(intent)
                                    }
                                }) {
                                    Text("Mon Profil")
                                }
                            }
                            Row (modifier = Modifier
                                .fillMaxWidth()
                                .background(deepBlue)
                                .padding(16.dp)) {
                                Text(
                                    text = "Liste des Formations",
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp), horizontalArrangement = Arrangement.Center) {
                                Column() {
                                    list?.forEach { data ->
                                        Text(text = data.name)
                                    }
                                }
                            }
                            Text(text = "Liste des Eleves",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(deepBlue)
                                    .padding(16.dp)
                            )
                            var listStu: List<Student>? = StudentViewModel(application).getAll()
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .padding(25.dp)) {
                                listStu?.forEach { data ->
                                    Row(modifier = Modifier
                                        .fillMaxWidth()
                                        .border(1.dp, Color.Gray), verticalAlignment = Alignment.CenterVertically) {
                                        Column(modifier = Modifier.fillMaxWidth(0.65f)) {
                                            Text(text = data.name, modifier = Modifier.padding(end = 16.dp, start = 16.dp))
                                        }
                                        Column(modifier = Modifier.fillMaxWidth().padding(end = 5.dp)) {
                                            Button(modifier = Modifier.fillMaxWidth(),onClick = {
                                                val intent = Intent(
                                                    this@HomeActivity,
                                                    StudentInfoActivity::class.java
                                                )
                                                intent.putExtra("idStudent", data.id)
                                                startActivity(intent)
                                            }) {
                                                Text("Details")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProjetAndroidSSTheme {

    }
}