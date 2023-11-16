package com.example.chitchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var name: EditText
    private lateinit var signupbutton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()

        auth=FirebaseAuth.getInstance()
        email=findViewById(R.id.email)
        password=findViewById(R.id.password)
        name=findViewById(R.id.name)
        signupbutton=findViewById(R.id.signupbutton)

        signupbutton.setOnClickListener{
            val edtemail=email.text.toString()
            val edtpassword=password.text.toString()
            val edtname=name.text.toString()

            signupfun(edtname,edtemail,edtpassword)
        }
    }
    private fun signupfun(edtname:String,edtemail:String,edtpassword:String)
    {
        auth.createUserWithEmailAndPassword(edtemail,edtpassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                adduserToDb(edtname,edtemail,auth.currentUser?.uid!!)
                    val intent=Intent(this,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"some error occured",Toast.LENGTH_SHORT).show()

                }
            }

    }
    private fun adduserToDb(name:String,email:String,uid:String)
    {
        dbRef = FirebaseDatabase.getInstance().reference
        dbRef.child("user").child(uid).setValue(User(name,email,uid))
    }


}