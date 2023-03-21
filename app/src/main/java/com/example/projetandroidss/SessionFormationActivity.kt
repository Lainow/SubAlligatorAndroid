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

class SessionFormationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            ProjetAndroidSSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val idSess = intent.getSerializableExtra("idSession") as Int
                    val sessionForm = SessionViewModel(application).getById(idSess)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        val idStudent = intent.getSerializableExtra("idStudent") as? Int
                        val student = StudentViewModel(application).getById(idStudent!!)

                        var modifRow = Modifier
                            .fillMaxWidth()
                            .border(1.dp, Color.Gray)
                        var modifRow2 = Modifier
                            .fillMaxWidth()
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = modifRow2,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Liste des élèves de la scéances",
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
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                            ) {
                                if (sessionForm != null) {
                                    Row(
                                        modifier = modifRow.background(Color.LightGray),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth(0.5f)
                                                .padding(end = 5.dp)
                                        ) {
                                            Text(
                                                text = "ELEVES",
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(16.dp)
                                            )


                                        }
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(end = 5.dp)
                                        ) {
                                            Text(
                                                text = "EVALUATION",
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(16.dp)
                                            )
                                        }
                                        if (sessionForm != null){
                                            var formStudent = StudentViewModel(application).getById(formation.studentId)
                                        }
                                        var partiStudent =
                                            ParticipationViewModel(application).getByStudentAndContentId(
                                                student.id,
                                                content.id
                                            )
                                        if (partiStudent != null) {
                                            for (parti in partiStudent) {
                                                var statusForm =
                                                    StatusViewModel(application).getById(
                                                        parti.statusId
                                                    )
                                            if (statusForm != null && statusForm.id > 2) {
                                                Row(
                                                    modifier = Modifier.fillMaxWidth()
                                                        .border(
                                                            1.dp,
                                                            Color.Gray
                                                        )
                                                ) {

                                                    Column(
                                                        modifier = Modifier.fillMaxWidth(
                                                            0.5f
                                                        )
                                                            .padding(start = 10.dp),
                                                        horizontalAlignment = Alignment.CenterHorizontally
                                                    ) {
                                                        Text(
                                                            text = student.name
                                                        )
                                                    }
                                                    var color = ""
                                                    if (statusForm.color.startsWith(
                                                            "#"
                                                        )
                                                    ) {
                                                        color =
                                                            statusForm.color
                                                    } else {
                                                        color =
                                                            "#" + statusForm.color
                                                    }
                                                    val objetCouleur = Color(
                                                        android.graphics.Color.parseColor(
                                                            color
                                                        )
                                                    )
                                                    Column(
                                                        modifier = Modifier.fillMaxWidth(0.5f)
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
                                                            text = statusForm.name,
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


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview6() {
        ProjetAndroidSSTheme {
        }
    }
}