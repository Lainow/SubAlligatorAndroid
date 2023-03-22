package com.example.projetandroidss

import android.app.Activity
import android.app.Application
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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetandroidss.entities.*
import com.example.projetandroidss.ui.theme.ProjetAndroidSSTheme
import com.example.projetandroidss.ui.theme.bgGray
import com.example.projetandroidss.viewModel.*
import java.awt.font.NumericShaper

class SessionListActivity : ComponentActivity() {
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                if (initiateur != null) {
                                    TopNavigation(initiateur.id, this@SessionListActivity)
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp)
                            ) {
                                Text(
                                    text = "Liste des Seances",
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = deepBlue,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                            var listSession: List<Session>? = SessionViewModel(application).getAll()
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 25.dp, end = 25.dp)
                            ) {
                                listSession?.forEach { data ->
                                    val parties = (data.date).split("-")
                                    val annee = parties[0]
                                    val mois = parties[1]
                                    val jourT = parties[2]
                                    val partiesJ = (jourT).split("T")
                                    val jour = partiesJ[0]

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .border(1.dp, Color.Gray).background(Color.White),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.fillMaxWidth(0.55f)) {
                                            Text(
                                                text = jour + "/" + mois + "/" + annee,
                                                modifier = Modifier.padding(
                                                    end = 16.dp,
                                                    start = 16.dp
                                                )
                                            )
                                        }
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(end = 5.dp)
                                        ) {
                                            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                                            }) {
                                                Text("Plus d'info")
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
fun DefaultPreview6() {
    ProjetAndroidSSTheme {
    }
}