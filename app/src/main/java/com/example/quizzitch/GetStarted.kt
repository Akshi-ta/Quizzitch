package com.example.quizzitch

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GetStarted: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.getstarted1)
        val next: Button = findViewById(R.id.button123)
        next.setOnClickListener{
            val intent = Intent(this, Dash::class.java)
            startActivity(intent)
            finish()
        }
    }
}