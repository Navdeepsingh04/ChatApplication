package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class signUpActivity : AppCompatActivity() {

    private lateinit var name:EditText
    private lateinit var edittext1: EditText
    private lateinit var edittext2: EditText
    private lateinit var btnsignup: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        name = findViewById(R.id.nameEdit)
        edittext1 = findViewById(R.id.edittext1)
        edittext2 = findViewById(R.id.edittext2)
        btnsignup = findViewById(R.id.btnsignup)

        btnsignup.setOnClickListener(){
            val email = edittext1.text.toString()
            val password = edittext2.text.toString()
            signUp(email,password)
        }
    }
    private fun signUp(email:String,password:String){
        //logic for creating new user to firebase.
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val intent = Intent(this@signUpActivity,MainActivityy::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@signUpActivity,"Can't create user",Toast.LENGTH_SHORT).show()

                }
            }
    }
}