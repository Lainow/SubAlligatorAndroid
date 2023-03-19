package com.example.projetandroidss


import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginBt = findViewById<Button>(R.id.loginBt)
        loginBt.setOnClickListener {
            if(emailLogin.text.isNotEmpty() && mdpLogin.text.isNotEmpty()) {
                val toast = Toast.makeText(this, "C'est bon", Toast.LENGTH_SHORT)
                toast.show()
            }else {
                val toast = Toast.makeText(this, "Veuillez remplir les champs", Toast.LENGTH_SHORT)
                toast.show()
            }
        }


    }


}