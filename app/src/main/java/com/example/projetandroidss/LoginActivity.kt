package com.example.projetandroidss

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.projetandroidss.viewModel.InitiatorViewModel

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val button2 = findViewById<Button>(R.id.button)
        val emailLogin = findViewById<EditText>(R.id.emailLogin)
        val passwordLogin = findViewById<EditText>(R.id.passwordLogin)
        val listInit = InitiatorViewModel(application).getAll()
        button2.setOnClickListener {
            var etat = false
            if (listInit != null) {
                listInit.forEach { data ->
                    if(emailLogin.text.toString() == data.email && passwordLogin.text.toString() == data.password){
                        etat = true
                        emailLogin.text.clear()
                        passwordLogin.text.clear()
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        intent.putExtra("idInitLogin", data.id)
                        startActivity(intent)
                    }
                }
            }
            if (etat == false) {
                Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}