package com.example.quizzitch

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzitch.ui.slideshow.splashscreen
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.core.View

class SignIn : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)

        if (intent.getStringArrayExtra("bool").toString()=="true")
        {
            Snackbar.make(findViewById(R.id.button),"You were logget out",1000)
                .setAction("Action",null).show()
        }
        val signinBt: Button = findViewById(R.id.button)
        val enterMail: EditText = findViewById(R.id.textView3)
        val enterPass: EditText = findViewById(R.id.signinEditPassword)

        signinBt.setOnClickListener {
            when {
                TextUtils.isEmpty(enterMail.text.toString().trim { it <= ' ' }) -> {

                    Snackbar.make(findViewById(R.id.signinEditPassword), "Enter mail please", 2000)
                        .setAction("action", null).show()
                }
                TextUtils.isEmpty(enterPass.text.toString().trim { it <= ' ' }) -> {
//                    Toast.makeText(
//                        this,
//                        "Enter Password please",
//                        Toast.LENGTH_SHORT
//                    ).show()

                    Snackbar.make(findViewById(R.id.signinEditPassword), "Enter Password please", 2000)
                        .setAction("action", null).show()
                }
                else -> {
                    val mail: String = enterMail.text.toString()
                    val password: String = enterPass.text.toString()
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(mail, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->
                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(this, "LOGGED IN", Toast.LENGTH_SHORT).show()

//                                    Snackbar.make(findViewById(R.id.textView5), "LOGGED IN", 2000)
//                                        .setAction("action", null).show()

                                    val intent = Intent(this, Dash::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("User_id", firebaseUser.uid)
                                    intent.putExtra("email-id", firebaseUser.email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    //Toast.makeText(this, task.exception!!.toString(), Toast.LENGTH_SHORT).show()

                                    Snackbar.make(
                                        findViewById(R.id.signinEditPassword),
                                        task.exception!!.toString(),
                                        Snackbar.LENGTH_LONG
                                    )
                                        .setAction("action", null).show()
                                }

                            }
                        )
                }
            }
        }
        val signup: TextView = findViewById(R.id.textView6)
        signup.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }

        val guest: Button = findViewById(R.id.button2)
        guest.setOnClickListener{
            val intent = Intent(this, Dash::class.java)
            startActivity(intent)
            finish()
        }

    }
}