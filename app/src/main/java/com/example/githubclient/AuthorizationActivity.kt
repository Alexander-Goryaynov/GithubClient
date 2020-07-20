package com.example.githubclient

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.Volley
import com.example.githubclient.Logic.AuthorizationLogic
import kotlinx.android.synthetic.main.activity_authorization.*

class AuthorizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        if (AuthorizationLogic.anyCreds(this)) {
            val intent = Intent(this, SearchActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(intent)
        }
        buttonLoginId.setOnClickListener {
            val login = editTextLoginId.text.toString()
            val password = editTextPasswordId.text.toString()
            if (login.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            AuthorizationLogic.checkCreds(login, password, this,
                Volley.newRequestQueue(this))
        }
    }

    override fun onBackPressed() { }
}
