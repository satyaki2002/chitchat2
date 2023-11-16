package com.example.chitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var loginbutton: Button
    private lateinit var signupbutton: Button
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        email=findViewById(R.id.email)
        password=findViewById(R.id.password)
        loginbutton=findViewById(R.id.loginbutton)
        signupbutton=findViewById(R.id.signupbutton)
        auth=FirebaseAuth.getInstance()

        signupbutton.setOnClickListener{
            val intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        loginbutton.setOnClickListener{
            val edtemail=email.text.toString()
            val edtpassword=password.text.toString()
            loginfun(edtemail,edtpassword)
        }
    }
    private fun loginfun(edtemail:String,edtpasswords:String)
    {
        auth.signInWithEmailAndPassword(edtemail, edtpasswords)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent=Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"some error occured",Toast.LENGTH_SHORT).show()
                }
            }

    }
}