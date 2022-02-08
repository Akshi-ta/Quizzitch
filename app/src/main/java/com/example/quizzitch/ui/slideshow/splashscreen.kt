package com.example.quizzitch.ui.slideshow

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.telecom.Call
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.quizzitch.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class splashscreen  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val a = 3000
        if(FirebaseAuth.getInstance().currentUser==null){
            Handler().postDelayed({
                val intent = Intent(this, SignIn ::class.java)
                startActivity(intent)
                finish()
            }, a.toLong())
        }else
        {
            val db = FirebaseFirestore.getInstance()
            val mauth = FirebaseAuth.getInstance()
            db.collection("desc").document(mauth.uid.toString()).get().addOnSuccessListener{
                if (!it.contains("displayName"))
                {
                    Handler().postDelayed({
                        val intent = Intent(this, SigninDetails:: class.java)
                        startActivity(intent)
                        finish()
                    }, a.toLong())
                }else
                {
                    Handler().postDelayed({
                        val intent:Intent = Intent(this, Dash::class.java)
                        startActivity(intent)
                        finish()
                    }, a.toLong())
                }
            }
        }

    }
    }
