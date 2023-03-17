package com.example.projetandroidss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projetandroidss.entities.Initiator
import com.example.projetandroidss.entities.Student
import com.example.projetandroidss.ui.theme.ProjetAndroidSSTheme
import com.example.projetandroidss.viewModel.InitiatorViewModel
import com.example.projetandroidss.viewModel.StudentViewModel

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
                    val idStudent = intent.getSerializableExtra("idStudent") as? Int
                    val infoStudent= StudentViewModel(application).getById(idStudent!!)
                    InfoStudent(student = infoStudent)
                }
            }
        }
    }
}

@Composable
fun InfoStudent(student : Student?) {

    val deepBlue = Color(0xFF004794)
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Column() {
            if (student != null) {
                Text(
                    text = "Détails sur " + student.name,
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
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ProjetAndroidSSTheme {

    }
}