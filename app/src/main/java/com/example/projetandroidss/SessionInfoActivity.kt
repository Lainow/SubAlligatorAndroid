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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetandroidss.ui.theme.ProjetAndroidSSTheme
import com.example.projetandroidss.viewModel.*

class SessionInfoActivity : ComponentActivity() {
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
                    val idSession = intent.getSerializableExtra("idSession") as? Int
                    val session = SessionViewModel(application).getById(idSession!!)
                    val idInit = intent.getSerializableExtra("idInitLogin") as Int
                    val initiateur = InitiatorViewModel(application).getById(idInit)
                    val deepBlue = Color(0xFF004794)
                    if (session != null) {
                        val parties = (session.date).split("-")
                        val annee = parties[0]
                        val mois = parties[1]
                        val jourT = parties[2]
                        val partiesJ = (jourT).split("T")
                        val jour = partiesJ[0]
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                if (initiateur != null) {
                                    TopNavigation(initiateur.id, this@SessionInfoActivity)
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column() {

                                    Text(
                                        text = "Seance du " + jour + "/" + mois + " " + annee,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = deepBlue,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    )
                                }
                            }
                            Row(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    var contentSession =
                                        ContentViewModel(application).getBySessionId(
                                            session.id
                                        )
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
                                                0.40f
                                            )
                                                .padding(
                                                    top = 10.dp,
                                                    bottom = 10.dp,
                                                    start = 10.dp
                                                )
                                        ) {
                                            Text(
                                                text = "Eleve",
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Column(
                                            modifier = Modifier.fillMaxWidth(
                                                0.50f
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
                                    if (contentSession != null) {
                                        for (content in contentSession) {
                                                    var partiSession =
                                                        ParticipationViewModel(application).getByContentId(
                                                            content.id
                                                        )
                                                    var aptitudeSession =
                                                        AptitudeViewModel(application).getById(
                                                            content.aptitudeId
                                                        )
                                                    if (partiSession != null) {
                                                        for (parti in partiSession) {
                                                            var student = StudentViewModel(
                                                                application
                                                            ).getById(parti.studentId)
                                                            if (student != null) {
                                                            var statusSession =
                                                                StatusViewModel(application).getById(
                                                                    parti.statusId
                                                                )
                                                            if (statusSession != null) {
                                                                Row(
                                                                    modifier = Modifier.fillMaxWidth()
                                                                        .border(
                                                                            1.dp,
                                                                            Color.Gray
                                                                        ).background(Color.White)
                                                                ) {
                                                                    Column(
                                                                        modifier = Modifier.fillMaxWidth(
                                                                            0.30f
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
                                                                            text = student.name
                                                                        )
                                                                    }
                                                                    Column(
                                                                        modifier = Modifier.fillMaxWidth(
                                                                            0.60f
                                                                        )
                                                                            .padding(start = 10.dp),
                                                                        horizontalAlignment = Alignment.CenterHorizontally
                                                                    ) {
                                                                        Text(
                                                                            text = aptitudeSession.name
                                                                        )
                                                                    }
                                                                    var color = ""
                                                                    if (statusSession.id == 2){
                                                                        color = "#FF6666"
                                                                    }
                                                                    else {
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

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview8() {
    ProjetAndroidSSTheme {
        Greeting2("Android")
    }
}