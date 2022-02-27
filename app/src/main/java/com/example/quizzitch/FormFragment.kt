package com.example.quizzitch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient


class FormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val form: WebView = view.findViewById(R.id.webView)
        form.webViewClient = WebViewClient()
        val webSetting: WebSettings = form.settings
        webSetting.javaScriptEnabled = true
        form.addJavascriptInterface(WebInterface(requireContext()), "Android")
        form.loadUrl("http://www.google.com")

//        val driver = ChromeDriver()
//        driver.get("https://selenium.dev")
    }
}