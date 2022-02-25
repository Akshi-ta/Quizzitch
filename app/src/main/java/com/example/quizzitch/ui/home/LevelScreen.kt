package com.example.quizzitch.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.quizzitch.R

class LevelScreen: Fragment() {

    private val args = this.arguments
    private val inputData = args?.get("data")
    private val topic = inputData.toString()

    //var choice: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.levelscreen, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()

        val easy: Button = view.findViewById(R.id.easy)
        easy.setOnClickListener {
                val level = "easy"
            bundle.putString("data", level)
            bundle.putString("key", topic)
            val fragment = QuizFragment()
            fragment.arguments = bundle
            val cons:ConstraintLayout = view.findViewById(R.id.levelR)
            cons.visibility =View.GONE
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.levelR, fragment)
            transaction.addToBackStack("level")
            transaction.commit()
                Toast.makeText(activity, "Easy is selected", Toast.LENGTH_SHORT).show()
            }



        val average: Button = view.findViewById(R.id.avg)
        average.setOnClickListener {
            val level = "avg"
            bundle.putString("data", level)
            bundle.putString("key", topic)
            val fragment = QuizFragment()
            fragment.arguments = bundle
            val cons:ConstraintLayout = view.findViewById(R.id.levelR)
            cons.visibility =View.GONE
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.levelR, fragment)
            transaction.addToBackStack("level")
            transaction.commit()
                Toast.makeText(activity, "Average is selected", Toast.LENGTH_SHORT).show()
            }



        val diff: Button = view.findViewById(R.id.hard)
        diff.setOnClickListener {
            val level = "diff"
            bundle.putString("data", level)
            bundle.putString("key", topic)
            val fragment = QuizFragment()
            fragment.arguments = bundle
            val cons:ConstraintLayout = view.findViewById(R.id.levelR)
            cons.visibility =View.GONE
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.levelR, fragment)
            transaction.addToBackStack("level")
            transaction.commit()
                Toast.makeText(activity, "Difficult is selected", Toast.LENGTH_SHORT).show()
            }

    }

}