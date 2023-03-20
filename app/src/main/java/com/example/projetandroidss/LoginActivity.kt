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
        val listInitApi = InitiatorViewModel(application).getDataApi()
        button2.setOnClickListener {
            listInitApi.forEach { data ->
                if(emailLogin.text.toString() == data.email && passwordLogin.text.toString() == data.password){
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }
}