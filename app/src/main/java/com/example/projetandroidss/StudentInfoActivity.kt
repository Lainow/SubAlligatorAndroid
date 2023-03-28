package com.example.projetandroidss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetandroidss.entities.Participation
import com.example.projetandroidss.ui.theme.ProjetAndroidSSTheme
import com.example.projetandroidss.ui.theme.bgGray
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
                    color = bgGray
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        val idStudent = intent.getSerializableExtra("idStudent") as? Int
                        val student = StudentViewModel(application).getById(idStudent!!)
                        val idInit = intent.getSerializableExtra("idInitLogin") as Int
                        val initiateur = InitiatorViewModel(application).getById(idInit)
                        val deepBlue = Color(0xFF004794)
                        if (student != null) {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    if (initiateur != null) {
                                        TopNavigation(initiateur.id, this@StudentInfoActivity)
                                    }
                                }
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
                                            color = deepBlue,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp)
                                        )
                                    }
                                }
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp)) {
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
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp),
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = formationStudent.name,
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .border(
                                                    1.dp,
                                                    Color.Gray
                                                )
                                                .background(Color.LightGray),
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth(
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
                                                modifier = Modifier
                                                    .fillMaxWidth(
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
                                                                if (statusSession != null) {
                                                                    Row(
                                                                        modifier = Modifier
                                                                            .fillMaxWidth()
                                                                            .border(
                                                                                1.dp,
                                                                                Color.Gray
                                                                            )
                                                                            .background(Color.White)
                                                                    ) {
                                                                        Column(
                                                                            modifier = Modifier
                                                                                .fillMaxWidth(
                                                                                    0.25f
                                                                                )
                                                                                .background(
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
                                                                            modifier = Modifier
                                                                                .fillMaxWidth(
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
                                                                        if (statusSession.color.startsWith("#")) color = statusSession.color
                                                                        else color = "#" + statusSession.color
                                                                        var objetCouleur = Color(android.graphics.Color.parseColor(color))
                                                                        if (statusSession.id == 2) {objetCouleur = Color(0xFFFF5555)}
                                                                        Column(
                                                                            modifier = Modifier
                                                                                .fillMaxWidth()
                                                                                .background(
                                                                                    objetCouleur
                                                                                )
                                                                                .padding(
                                                                                    top = 20.dp,
                                                                                    bottom = 20.dp
                                                                                )
                                                                        ) {
                                                                            var selectedItem = remember { mutableStateOf(statusSession) }
                                                                            val options = StatusViewModel(application).getAll()
                                                                            if (options != null) {
                                                                                var dropdownExpanded = remember { mutableStateOf(false) }
                                                                                Box(
                                                                                    modifier = Modifier
                                                                                        .fillMaxWidth()
                                                                                        .clickable {
                                                                                            dropdownExpanded.value =
                                                                                                true
                                                                                        },
                                                                                    contentAlignment = Alignment.Center
                                                                                ) {
                                                                                    Text(text =selectedItem.value.name)
                                                                                    DropdownMenu(
                                                                                        expanded = dropdownExpanded.value,
                                                                                        onDismissRequest = { dropdownExpanded.value = false }
                                                                                    ) {
                                                                                        options.forEach { option ->
                                                                                            DropdownMenuItem(onClick = {
                                                                                                selectedItem.value = option
                                                                                                dropdownExpanded.value = false
                                                                                                ParticipationViewModel(application).updateStatus(
                                                                                                    parti,
                                                                                                    option.id
                                                                                                )
                                                                                            }) {
                                                                                                Text(
                                                                                                    text = option.name,
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