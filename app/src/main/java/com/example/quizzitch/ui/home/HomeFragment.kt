package com.example.quizzitch.ui.home

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizzitch.R
import com.example.quizzitch.databinding.FragmentHomeBinding
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tv :TextView = view.findViewById(R.id.quiz)
        tv.visibility = View.GONE
        val pgbar: ProgressBar = view.findViewById(R.id.progressBar)
        pgbar.visibility = View.VISIBLE


        val bt: Button = view.findViewById(R.id.imageView2)
        bt.setOnClickListener {
            val url = URL("https://opentdb.com/api.php?amount=10&category=23&difficulty=easy&type=multiple")
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}