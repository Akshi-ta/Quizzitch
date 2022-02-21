package com.example.quizzitch

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SigninDetails: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)

        val save : Button = findViewById(R.id.button4)
        val enterFirstName = findViewById<EditText>(R.id.textView14)
        val enterLastName = findViewById<EditText>(R.id.textView15)
        val enterDisplayName = findViewById<EditText>(R.id.textView17)
        save.setOnClickListener {
            when{
                TextUtils.isEmpty(enterFirstName.text.toString().trim{it<=' '})->{
                    Snackbar.make(save,"Enter Valid First Name", Snackbar.LENGTH_SHORT)
                        .setAction("action", null).show()
                }
                TextUtils.isEmpty(enterLastName.text.toString().trim{it<=' '})-> {
                    Snackbar.make(save,"Enter Valid Last Name", Snackbar.LENGTH_SHORT)
                        .setAction("action", null).show()
                }
                TextUtils.isEmpty(enterDisplayName.text.toString().trim{it<=' '})-> {
                    Snackbar.make(save,"Enter Valid Display Name", Snackbar.LENGTH_SHORT)
                        .setAction("action", null).show()
                }
                else -> {
                    val mp= HashMap<String,String>()
                    val mauth = FirebaseAuth.getInstance()
                    mp["first"] = enterFirstName.text.toString()
                    mp["last"] = enterLastName.text.toString()
                    mp["displayName"] = enterDisplayName.text.toString()
                    mp["mail"] = intent.getStringExtra("email-id").toString()
                    mp["uid"] = mauth.uid.toString()
                    mp["anon"] = "false"
                    val db = FirebaseFirestore.getInstance()
                    db.collection("desc").document(mauth.uid.toString()).set(mp).addOnSuccessListener {
                        Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, GetStarted::class.java)
                        startActivity(intent)
                        finish()

                    }.addOnFailureListener{
                        Snackbar.make(save, "error occured", Snackbar.LENGTH_SHORT)
                            .setAction("action", null).show()
                    }


                }
            }
        }
    }
}