package com.example.quizzitch.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.quizzitch.R
import com.example.quizzitch.ui.home.setData.score
import kotlinx.android.synthetic.main.activity_result.*

class Result : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setFragmentResultListener("requestKey") { key, bundle ->
            // Any type can be passed via to the bundle
            val result = bundle.getString("key")
            // Do something with the result...
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.levelscreen, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), "Check your Result", Toast.LENGTH_SHORT).show()

      //  val score=intent.getStringExtra(setData.score)
      //  val totalQuestion=intent.getStringExtra("total size")

       // Score.text="${score} / ${totalQuestion}"
//        button.setOnClickListener {
//           // startActivity(Intent(this,HomeFragment::class.java))
//           // finish()
//        }

    }
}