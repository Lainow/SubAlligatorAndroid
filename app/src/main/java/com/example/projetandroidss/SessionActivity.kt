package com.example.projetandroidss

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import com.example.projetandroidss.entities.Initiator
import com.example.projetandroidss.ui.theme.ProjetAndroidSSTheme
import com.example.projetandroidss.ui.theme.bgGray
import com.example.projetandroidss.viewModel.InitiatorViewModel
import com.example.projetandroidss.viewModel.SessionViewModel

class SessionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            ProjetAndroidSSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = bgGray
                ) {
                    val idFrom = intent.getSerializableExtra("idFormation") as Int
                    val sessionFom = SessionViewModel(application).getByFormationId(idFrom)
                    val idInit = intent.getSerializableExtra("idInitLogin") as Int
                    val initiateur = InitiatorViewModel(application).getById(idInit)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        var modifRow = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray)
                        var modifRow2 = Modifier
                            .fillMaxWidth()
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                if (initiateur != null) {
                                    TopNavigation(initiateur.id, this@SessionActivity)
                                }
                            }

                            Row(modifier = Modifier.fillMaxWidth()) {
                            }
                            Row(
                                modifier = modifRow2,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Liste des scéances de la formation",
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    color = deepBlue,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 25.dp, start = 25.dp)
                            ) {
                                if (sessionFom != null) {
                                    Row(
                                        modifier = modifRow.background(Color.LightGray),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(end = 5.dp)
                                        ) {
                                            Text(
                                                text = "DATE",
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                            )
                                        }
                                    }
                                    if (!sessionFom.isEmpty()) {

                                        sessionFom.forEach { data ->
                                            val parties = (data.date).split("-")
                                            val annee = parties[0]
                                            val mois = parties[1]
                                            val jourT = parties[2]
                                            val partiesJ = (jourT).split("T")
                                            val jour = partiesJ[0]
                                            Row(
                                                modifier = modifRow,
                                                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
                                            ) {
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(end = 5.dp).background(Color.White)
                                                ) {
                                                    Text(
                                                        text = jour + "/" + mois + "/" + annee,
                                                        textAlign = TextAlign.Center,
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .padding(16.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        Row(
                                            modifier = modifRow,
                                            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(end = 5.dp)
                                            ) {
                                                Text(
                                                    text = "Aucune seance disponible pour cette formation",
                                                    textAlign = TextAlign.Center,
                                                    fontWeight = FontWeight.Bold,
                                                    fontSize = 18.sp,
                                                    color = Color.Red,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(16.dp)
                                                )
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
fun DefaultPreview5() {
    ProjetAndroidSSTheme {
    }
}