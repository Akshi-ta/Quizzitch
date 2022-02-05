package com.example.quizzitch

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

class SignIn : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)

        if (intent.getStringArrayExtra("bool").toString()=="true")
        {
            Snackbar.make(findViewById(R.id.button),"You were logget out",1000)
                .setAction("Action",null).show()
        }
        val signin: Button = findViewById(R.id.button)

    }
}