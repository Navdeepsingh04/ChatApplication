package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity() {


    private lateinit var edittext1:EditText
    private lateinit var edittext2:EditText
    private lateinit var btnlogin:Button
    private lateinit var btnsignup:Button
    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edittext1 = findViewById(R.id.edittext1)
        edittext2 = findViewById(R.id.edittext2)
        btnlogin = findViewById(R.id.btnlogin)
        btnsignup = findViewById(R.id.btnsignup)

         btnsignup.setOnClickListener(){
             val intent = Intent(this,signUpActivity::class.java)
             startActivity(intent)
         }
        btnlogin.setOnClickListener(){
            var email = edittext1.text.toString()
            var password  = edittext2.text.toString()
            login(email,password)
        }
    }

    private fun login(email:String,password:String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@loginActivity,MainActivityy::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                   Toast.makeText(this@loginActivity,"User not Found,Try again",Toast.LENGTH_SHORT).show()
                }
            }

    }
}