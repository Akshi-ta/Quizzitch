package com.example.quizzitch.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.quizzitch.R

class Result : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.levelscreen, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), "Check your Result", Toast.LENGTH_SHORT).show()

        val scores = requireArguments().getInt("data")

        val score: TextView = view.findViewById(R.id.Score)
        score.text = "${scores} / 10"
        val finish: Button = view.findViewById(R.id.finish)
        finish.setOnClickListener {
            val fragment: Fragment = Result()
            val cons: ConstraintLayout = view.findViewById(R.id.resultr)
            cons.visibility = View.GONE
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.resultR, fragment)
            transaction.addToBackStack("result")
            transaction.commit()
        }

    }
}