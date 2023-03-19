package com.example.projetandroidss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.example.projetandroidss.ui.theme.ProjetAndroidSSTheme
import com.example.projetandroidss.viewModel.*
import java.text.SimpleDateFormat

class StudentInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetAndroidSSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        val idStudent = intent.getSerializableExtra("idStudent") as? Int
                        val student = StudentViewModel(application).getById(idStudent!!)
                        val deepBlue = Color(0xFF004794)
                        if (student != null) {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Column() {

                                        Text(
                                            text = "Progression de " + student.name,
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 20.sp,
                                            color = Color.White,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(deepBlue)
                                                .padding(16.dp)
                                        )
                                    }
                                }
                                Row(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    ) {
                                        var sessionStudent =
                                            SessionViewModel(application).getByFormationId(student.formationId)
                                        var formationStudent =
                                            FormationViewModel(application).getById(student.formationId)
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = "Formation",
                                                textAlign = TextAlign.Center,
                                                fontSize = 17.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Row(
                                            modifier = Modifier.fillMaxWidth().padding(10.dp),
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = formationStudent.name,
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Row(
                                            modifier = Modifier.fillMaxWidth()
                                                .border(
                                                    1.dp,
                                                    Color.Gray
                                                ).background(Color.LightGray),
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column(
                                                modifier = Modifier.fillMaxWidth(
                                                    0.25f
                                                )
                                                    .padding(
                                                        top = 10.dp,
                                                        bottom = 10.dp,
                                                        start = 10.dp
                                                    )
                                            ) {
                                                Text(
                                                    text = "Date",
                                                    textAlign = TextAlign.Center,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                            Column(
                                                modifier = Modifier.fillMaxWidth(
                                                    0.65f
                                                )
                                                    .padding(start = 10.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Text(
                                                    text = "Aptitude",
                                                    textAlign = TextAlign.Center,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                            Column(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Text(
                                                    text = "Status",
                                                    textAlign = TextAlign.Center,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                        if (sessionStudent != null) {
                                            for (session in sessionStudent) {
                                                val parties = (session.date).split("-")
                                                val annee = parties[0]
                                                val mois = parties[1]
                                                val jourT = parties[2]
                                                val partiesJ = (jourT).split("T")
                                                val jour = partiesJ[0]
                                                var contentStudent =
                                                    ContentViewModel(application).getBySessionId(
                                                        session.id
                                                    )
                                                if (contentStudent != null) {
                                                    for (content in contentStudent) {
                                                        var partiStudent =
                                                            ParticipationViewModel(application).getByStudentAndContentId(
                                                                student.id,
                                                                content.id
                                                            )
                                                        var aptitudeSession =
                                                            AptitudeViewModel(application).getById(
                                                                content.aptitudeId
                                                            )
                                                        if (partiStudent != null) {
                                                            for (parti in partiStudent) {
                                                                var statusSession =
                                                                    StatusViewModel(application).getById(
                                                                        parti.statusId
                                                                    )
                                                                if (statusSession != null && statusSession.id > 2) {
                                                                    Row(
                                                                        modifier = Modifier.fillMaxWidth()
                                                                            .border(
                                                                                1.dp,
                                                                                Color.Gray
                                                                            )
                                                                    ) {
                                                                        Column(
                                                                            modifier = Modifier.fillMaxWidth(
                                                                                0.25f
                                                                            ).background(
                                                                                Color(0xFFEFEFEF)
                                                                            )
                                                                                .padding(
                                                                                    top = 10.dp,
                                                                                    bottom = 10.dp,
                                                                                    start = 10.dp
                                                                                )
                                                                        ) {
                                                                            Text(
                                                                                text = jour + "/" + mois + " " + annee
                                                                            )
                                                                        }
                                                                        Column(
                                                                            modifier = Modifier.fillMaxWidth(
                                                                                0.65f
                                                                            )
                                                                                .padding(start = 10.dp),
                                                                            horizontalAlignment = Alignment.CenterHorizontally
                                                                        ) {
                                                                            Text(
                                                                                text = aptitudeSession.name
                                                                            )
                                                                        }
                                                                        var color = ""
                                                                        if (statusSession.color.startsWith(
                                                                                "#"
                                                                            )
                                                                        ) {
                                                                            color =
                                                                                statusSession.color
                                                                        } else {
                                                                            color =
                                                                                "#" + statusSession.color
                                                                        }
                                                                        val objetCouleur = Color(
                                                                            android.graphics.Color.parseColor(
                                                                                color
                                                                            )
                                                                        )
                                                                        Column(
                                                                            modifier = Modifier.fillMaxWidth()
                                                                                .background(
                                                                                    objetCouleur
                                                                                )
                                                                                .padding(
                                                                                    top = 20.dp,
                                                                                    bottom = 20.dp
                                                                                ),
                                                                            horizontalAlignment = Alignment.CenterHorizontally
                                                                        ) {
                                                                            Text(
                                                                                text = statusSession.name,
                                                                                textAlign = TextAlign.Center,
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
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ProjetAndroidSSTheme {

    }
}