package com.example.trabalho_taep

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.menujogos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var txtEmail = findViewById<EditText>(R.id.txtEmailLogin)
        var txtSenha = findViewById<EditText>(R.id.txtSenhaLogin)
        var btCadastro = findViewById<Button>(R.id.bttCadastro)
        var btLogin = findViewById<Button>(R.id.bttEntrar)
        val ir_menu = Intent (this, MenuJogos::class.java)
        val ir_cadastro = Intent (this, TelaCadastro::class.java)

        lateinit var auth: FirebaseAuth
        auth = FirebaseAuth.getInstance()

        btCadastro.setOnClickListener{
            startActivity(ir_cadastro)
        }

        btLogin.setOnClickListener {
            auth.signInWithEmailAndPassword(txtEmail.text.toString(), txtSenha.text.toString())
                .addOnSuccessListener {
                    Toast.makeText(this,"Login Realizado",Toast.LENGTH_SHORT).show()
                    startActivity(ir_menu)
                }

                .addOnFailureListener() {
                    Toast.makeText(this,"Login falhou",Toast.LENGTH_SHORT).show()
                }

        }
    }
}