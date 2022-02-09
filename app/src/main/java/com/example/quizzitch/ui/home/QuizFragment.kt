package com.example.quizzitch.ui.home

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.quizzitch.R
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class QuizFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.take_quiz, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv: TextView = view.findViewById(R.id.quiz)
        tv.visibility = View.GONE
        val pgbar: ProgressBar = view.findViewById(R.id.progressBar1)
        pgbar.visibility = View.VISIBLE

        val rd: RadioGroup = view.findViewById(R.id.radio)
        rd.setOnClickListener {
            fun onRadioButtonClicked(view: View) {
                if (view is RadioButton) {
                    // Is the button now checked?
                    val checked = view.isChecked

                    // Check which radio button was clicked
                    when (view.getId()) {
                        R.id.option1 ->
                            if (checked) {
                                // This is option 1
                            }
                        R.id.option2 ->
                            if (checked) {
                                // This is option 2
                            }
                        R.id.option3 ->
                            if (checked) {
                                // This is option 3
                            }
                        R.id.option4 ->
                            if (checked) {
                                // This is option 4
                            }
                    }
                }
            }

            val bt: Button = view.findViewById(R.id.imageView2)
            bt.setOnClickListener {
                val url =
                    URL("https://opentdb.com/api.php?amount=10&category=23&difficulty=easy&type=multiple")
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.setRequestProperty("accept", "application/json");
                val int = Build.VERSION.SDK_INT
                if (int > 8) {
                    val policy = StrictMode.ThreadPolicy.Builder()
                        .permitAll().build()
                    StrictMode.setThreadPolicy(policy)

                }
                val stream: InputStream = connection.inputStream
                val mapper: ObjectMapper = ObjectMapper()
                val node: JsonNode = mapper.readTree(stream)
                val ques = node.path("results")
                var i = 0
                tv.text = ques[i].path("question").asText()
                pgbar.visibility = View.GONE
                tv.visibility = View.VISIBLE

                val next: Button = view.findViewById(R.id.next)
                next.setOnClickListener {
                    if (i >= 9) {
                        Toast.makeText(context, "It is the End!", Toast.LENGTH_SHORT).show()
                    } else {
                        i++
                        tv.text = ques[i].path("question").asText()
                    }
                }

                val prev: Button = view.findViewById(R.id.previous)
                prev.setOnClickListener {
                    if (i <= 0) {
                        Toast.makeText(context, "It is the starting question.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        i--
                        tv.text = ques[i].path("question").asText()
                    }
                }

            }

        }


    }
}