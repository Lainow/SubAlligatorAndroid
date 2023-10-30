package com.example.projetandroidss

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetandroidss.entities.Student
import com.example.projetandroidss.ui.theme.ProjetAndroidSSTheme
import com.example.projetandroidss.ui.theme.bgGray
import com.example.projetandroidss.viewModel.InitiatorViewModel
import com.example.projetandroidss.viewModel.StudentViewModel

class StudentListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetAndroidSSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = bgGray
                ) {
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    val idInit = intent.getSerializableExtra("idInitLogin") as Int
                    val initiateur = InitiatorViewModel(application).getById(idInit)
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            if (initiateur != null) {
                                TopNavigation(initiateur.id, this@StudentListActivity)
                            }
                        }
                        var listStu: List<Student>? = StudentViewModel(application).getAll()
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    end = 25.dp,
                                    start = 25.dp
                                )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp)
                            ) {
                                Text(
                                    text = "Liste des Eleves",
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = deepBlue,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                            listStu?.forEach { data ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .border(1.dp, Color.Gray).background(Color.White),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.fillMaxWidth(0.65f)) {
                                        Text(
                                            text = data.name,
                                            modifier = Modifier.padding(end = 16.dp, start = 16.dp)
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 5.dp)
                                    ) {
                                        Button(modifier = Modifier.fillMaxWidth(), onClick = {
                                            val intentStu = Intent(
                                                this@StudentListActivity,
                                                StudentInfoActivity::class.java
                                            )
                                            intentStu.putExtra("idStudent", data.id)
                                            if (initiateur != null) {
                                                intentStu.putExtra("idInitLogin", initiateur.id)
                                            }
                                            startActivity(intentStu)
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

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    ProjetAndroidSSTheme {
        Greeting("Android")
    }
}