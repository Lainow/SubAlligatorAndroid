package com.example.projetandroidss

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import com.example.projetandroidss.viewModel.InitiatorViewModel

val deepBlue = Color(0xFF004794)


class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            ProjetAndroidSSTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val idInit = intent.getSerializableExtra("idInitiateur") as? Int
                    val initiator = InitiatorViewModel(application).getById(idInit!!)
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
                                text = "Résumé de votre Profil",
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
                            if (initiator != null) {
                                var nom = remember { mutableStateOf(initiator.name) }
                                var email = remember { mutableStateOf(initiator.email) }
                                var motdepasse = remember { mutableStateOf(initiator.password) }
                                var isDirector = remember { mutableStateOf(initiator.director) }
                                var level = remember { mutableStateOf(initiator.levelId) }
                                var expanded = remember { mutableStateOf(false) }
                                Row(
                                    modifier = modifRow,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier
                                        .fillMaxWidth(0.35f)
                                        .padding(16.dp)) {
                                        affichageTexte("Nom : ")
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 5.dp)
                                    ) {
                                        TextField(value = nom.value, onValueChange = { nom.value = it })

                                    }
                                }
                                Row(
                                    modifier = modifRow,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier
                                        .fillMaxWidth(0.35f)
                                        .padding(16.dp)) {
                                        affichageTexte("Email : ")
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 5.dp)
                                    ) {
                                        TextField(value = email.value, onValueChange = { email.value = it })
                                    }
                                }
                                Row(
                                    modifier = modifRow,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier
                                        .fillMaxWidth(0.35f)
                                        .padding(16.dp)) {
                                        affichageTexte("Password : ")
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 5.dp)
                                    ) {
                                        TextField(value = motdepasse.value, onValueChange = { motdepasse.value = it })
                                    }
                                }
                                Row(
                                    modifier = modifRow,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier
                                        .fillMaxWidth(0.35f)
                                        .padding(16.dp)) {
                                        affichageTexte("Level : ")
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 5.dp)
                                    ) {
                                        TextButton(
                                            onClick = { expanded.value = true },
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Text("Niveau " + level.value, color = Color.Black)
                                        }
                                        DropdownMenu(
                                            expanded = expanded.value,
                                            onDismissRequest = { expanded.value = false },
                                            modifier = Modifier.fillMaxWidth(0.50f)
                                        ) {
                                            DropdownMenuItem(onClick = {
                                                level.value = 1
                                                expanded.value = false }) {
                                                Text("Niveau 1")
                                            }
                                            DropdownMenuItem(onClick = {
                                                level.value = 2
                                                expanded.value = false
                                            }) {
                                                Text("Niveau 2")
                                            }
                                        }
                                    }
                                }
                                Row(
                                    modifier = modifRow,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(0.30f)
                                            .padding(16.dp)
                                    ) {
                                        affichageTexte("Status : ")
                                    }
                                    Column () {
                                        Checkbox(checked = isDirector.value, onCheckedChange = {isDirector.value = it })
                                    }
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 5.dp)
                                    ) {
                                        if (isDirector.value) {
                                            Text("Directeur" )
                                        } else {
                                            Text("Initiateur")
                                        }
                                    }
                                }
                                Row(
                                    modifier = modifRow.padding(7.dp),
                                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
                                ) {
                                    Button(onClick = {
                                        Thread(){
                                            InitiatorViewModel(application).update(Initiator(idInit,
                                                nom.value, email.value, motdepasse.value, isDirector.value,
                                                initiator.levelId, initiator.deleted))
                                        }.start()
                                        Toast.makeText(applicationContext, "Modification effectuée", Toast.LENGTH_SHORT).show()
                                    } ) {
                                        Text("Modifier")
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
fun DefaultPreview2() {
    ProjetAndroidSSTheme {
    }
}

@Composable
fun affichageTexte(texte: String) {
    Text(text = texte,
        fontWeight = FontWeight.Bold
    )
}