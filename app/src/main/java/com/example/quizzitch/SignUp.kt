package com.example.quizzitch

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUp: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        val back : ImageButton = findViewById(R.id.imageButton)
        back.setOnClickListener{
            val intent = Intent(this,SignIn::class.java)
            startActivity(intent)
            finish()
        }
        val signupbt: Button = findViewById(R.id.button3)
        val enterMail : EditText = findViewById(R.id.textView8)
        val enterPass : EditText = findViewById(R.id.editPassword)
        signupbt.setOnClickListener {
            when {
                TextUtils.isEmpty(enterMail.text.toString().trim { it <= ' '}) -> {
                    Toast.makeText(
                        this,
                        "Please enter a password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = enterMail.text.toString().trim { it<= ' '}
                    val password: String = enterPass.text.toString().trim { it <= ' ' }
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> {task ->
                                if (task.isSuccessful) {
                                    val firebaseUser: FirebaseUser = task.result!!.user!!
                                    Toast.makeText(
                                        this,
                                        "You were registered successfully.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent = Intent(this, SigninDetails::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("User_id", firebaseUser.uid)
                                    intent.putExtra("email-id", firebaseUser.email)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    Toast.makeText(
                                        this,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }


                        )
                }
            }
        }
    }
}