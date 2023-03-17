package com.example.projetandroidss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
                    val profilInitiator = InitiatorViewModel(application).getById(idInit!!)
                    AffichageProfil(profilInitiator)
                }
            }
        }
    }
}

@Composable
fun AffichageProfil(initiator : Initiator?) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Column() {
            Text(text = "Résumé de votre Profil",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(deepBlue)
                    .padding(16.dp)
            )
            if (initiator != null) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth().background(Color(0xFFEFEFEF)).border(1.dp, Color.Gray)
                ) {
                    item {
                        Box(modifier =
                            Modifier.border(1.dp, Color.Gray)) {
                            affichageTexte("Nom :")
                        }
                    }
                    item {
                        affichageInfo(texte = initiator.name)
                    }
                    item {
                        Box(modifier =
                        Modifier.border(1.dp, Color.Gray)) {
                            affichageTexte("Email :")
                        }
                    }
                    item {
                        affichageInfo(texte = initiator.email)
                    }
                    item {
                        Box(modifier =
                        Modifier.border(1.dp, Color.Gray)) {
                            affichageTexte("Mot de passe :")
                        }
                    }
                    item {
                        affichageInfo(texte = "**********")
                    }
                    item {
                        Box(modifier =
                        Modifier.border(1.dp, Color.Gray)) {
                            affichageTexte("Status :")
                        }
                    }
                    item {
                        if (initiator.director) {
                            affichageInfo(texte = "Directeur")
                        } else {
                            affichageInfo(texte = "Initiateur")
                        }
                    }
                    item {
                        Box(modifier =
                        Modifier.border(1.dp, Color.Gray)) {
                            affichageTexte("Niveau de plongée :")
                        }
                    }
                    item {
                            affichageInfo(texte = initiator.levelId.toString())
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
    Box(
        Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(text = texte,
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun affichageInfo(texte: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(
            text = texte,
            modifier = Modifier.fillMaxSize().background(Color.White),
            textAlign = TextAlign.Left,
        )
    }
}