package com.example.projetandroidss

import android.content.Context
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetandroidss.entities.*
import com.example.projetandroidss.ui.theme.ProjetAndroidSSTheme
import com.example.projetandroidss.ui.theme.bgGray
import com.example.projetandroidss.viewModel.*

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetAndroidSSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = bgGray

                    ) {
                    val idInit = intent.getSerializableExtra("idInitLogin") as Int
                    val initiateur = InitiatorViewModel(application).getById(idInit)
                    val listFo: List<Formation>? = FormationViewModel(application).getAll()
                    val deepBlue = Color(0xFF004794)

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                if (initiateur != null) {
                                    TopNavigation(initiateur.id, this@HomeActivity)
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = "Liste des Formations",
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = deepBlue,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 25.dp, end = 25.dp)
                            ) {
                                listFo?.forEach { data ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .border(1.dp, Color.Gray).background(Color.White),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.fillMaxWidth(0.65f)) {
                                            Text(
                                                text = data.name,
                                                modifier = Modifier.padding(
                                                    end = 10.dp,
                                                    start = 10.dp
                                                )
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(end = 5.dp)
                                        ) {
                                            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                                                val intentSe = Intent(
                                                    this@HomeActivity,
                                                    SessionActivity::class.java
                                                )
                                                intentSe.putExtra("idFormation", data.id)
                                                if (initiateur != null) {
                                                    intentSe.putExtra("idInitLogin", initiateur.id)
                                                }
                                                startActivity(intentSe)
                                            }) {
                                                Text("Seances")
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

@Composable
fun TopNavigation(id : Int, context: Context) {

    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Sub'Alligator",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(start = 16.dp),
                    color = deepBlue
                )
            },
            actions = {
                IconButton(onClick = { val intent =
                    Intent(context, HomeActivity::class.java)
                    intent.putExtra("idInitLogin", id)
                    context.startActivity(intent)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Home"
                    )
                }
                IconButton(onClick = { val intent =
                    Intent(context, StudentListActivity::class.java)
                    intent.putExtra("idInitLogin", id)
                    context.startActivity(intent)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Eleves"
                    )
                }
                IconButton(onClick = { val intent =
                    Intent(context, SessionListActivity::class.java)
                    intent.putExtra("idInitLogin", id)
                    context.startActivity(intent)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Seance"
                    )
                }
                IconButton(onClick = { val intent =
                    Intent(context, ProfileActivity::class.java)
                    intent.putExtra("idInitiateur", id)
                    context.startActivity(intent)
                }) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "Profil"
                    )
                }
            },
            backgroundColor = Color.White,
            elevation = 4.dp
        )
    }
}