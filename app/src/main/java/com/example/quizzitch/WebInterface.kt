package com.example.quizzitch

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast

class WebInterface(private val context: Context) {

    @JavascriptInterface
    fun showToast(){
        Toast.makeText(context, "This is web Interface", Toast.LENGTH_SHORT).show()
    }
}