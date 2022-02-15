package com.example.quizzitch.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.quizzitch.R

class LevelScreen: Fragment() {
    var choice: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        choice = arguments?.getString("topic")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.levelscreen, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val easy: Button = view.findViewById(R.id.easy)
        easy.setOnClickListener(){
            val intent = Intent(context, QuizFragment::class.java)
            Toast.makeText(activity, "Easy is selected", Toast.LENGTH_SHORT).show()
            when (choice){
                 "history" -> intent.apply {
                              putExtra("easy", "history")
                              }
                 "politics" -> intent.apply {
                     putExtra("easy", "politics")
                 }
                 "dance" -> intent.apply {
                     putExtra("easy", "dance")
                 }
                 "bollywood" -> intent.apply {
                     putExtra("easy", "bollywood")
                 }
                "sports" ->intent.apply {
                    putExtra("easy", "dance")
                }
                "culture" -> intent.apply {
                    putExtra("easy", "dance")
                }
                "science" -> intent.apply {
                    putExtra("easy", "dance")
                }
                "computers" -> intent.apply {
                    putExtra("easy", "computers")
                }
                "mythology" -> intent.apply {
                    putExtra("easy", "mythology")
                }
                "art" -> intent.apply {
                    putExtra("easy", "art")
                }
                "geography" -> intent.apply {
                    putExtra("easy", "geography")
                }
                "animals" -> intent.apply {
                    putExtra("easy", "animals")
                }
                "comics" -> intent.apply {
                    putExtra("easy", "comics")
                }
                "anime" -> intent.apply {
                    putExtra("easy", "anime")
                }
                "gadgets" -> intent.apply {
                    putExtra("easy", "gadgets")
                }
                "coding" -> intent.apply {
                    putExtra("easy", "coding")
                }
                "cartoon" -> intent.apply {
                    putExtra("easy", "cartoon")
                }
                "gk" -> intent.apply {
                    putExtra("easy", "gk")
                }
                "covid19" -> intent.apply {
                    putExtra("easy", "covid19")
                }
                "marvel" -> intent.apply {
                    putExtra("easy", "marvel")
                }
            }
            startActivity(intent)

        }


        val average: Button = view.findViewById(R.id.avg)
        average.setOnClickListener(){
            val intent = Intent(context, QuizFragment::class.java)
            Toast.makeText(activity, "Average is selected", Toast.LENGTH_SHORT).show()
            when (choice){
                "history" -> intent.apply {
                    putExtra("avg", "history")
                }
                "politics" -> intent.apply {
                    putExtra("avg", "politics")
                }
                "dance" -> intent.apply {
                    putExtra("avg", "dance")
                }
                "bollywood" -> intent.apply {
                    putExtra("avg", "bollywood")
                }
                "sports" ->intent.apply {
                    putExtra("avg", "dance")
                }
                "culture" -> intent.apply {
                    putExtra("avg", "dance")
                }
                "science" -> intent.apply {
                    putExtra("avg", "dance")
                }
                "computers" -> intent.apply {
                    putExtra("avg", "computers")
                }
                "mythology" -> intent.apply {
                    putExtra("avg", "mythology")
                }
                "art" -> intent.apply {
                    putExtra("avg", "art")
                }
                "geography" -> intent.apply {
                    putExtra("avg", "geography")
                }
                "animals" -> intent.apply {
                    putExtra("avg", "animals")
                }
                "comics" -> intent.apply {
                    putExtra("avg", "comics")
                }
                "anime" -> intent.apply {
                    putExtra("avg", "anime")
                }
                "gadgets" -> intent.apply {
                    putExtra("avg", "gadgets")
                }
                "coding" -> intent.apply {
                    putExtra("avg", "coding")
                }
                "cartoon" -> intent.apply {
                    putExtra("avg", "cartoon")
                }
                "gk" -> intent.apply {
                    putExtra("avg", "gk")
                }
                "covid19" -> intent.apply {
                    putExtra("avg", "covid19")
                }
                "marvel" -> intent.apply {
                    putExtra("avg", "marvel")
                }
            }
            startActivity(intent)
        }


        val diff: Button = view.findViewById(R.id.hard)
        diff.setOnClickListener(){
            val intent = Intent(context, QuizFragment::class.java)
            Toast.makeText(activity, "Difficult is selected", Toast.LENGTH_SHORT).show()
            when (choice){
                "history" -> intent.apply {
                    putExtra("diff", "history")
                }
                "politics" -> intent.apply {
                    putExtra("diff", "politics")
                }
                "dance" -> intent.apply {
                    putExtra("diff", "dance")
                }
                "bollywood" -> intent.apply {
                    putExtra("diff", "bollywood")
                }
                "sports" ->intent.apply {
                    putExtra("diff", "dance")
                }
                "culture" -> intent.apply {
                    putExtra("diff", "dance")
                }
                "science" -> intent.apply {
                    putExtra("diff", "dance")
                }
                "computers" -> intent.apply {
                    putExtra("diff", "computers")
                }
                "mythology" -> intent.apply {
                    putExtra("diff", "mythology")
                }
                "art" -> intent.apply {
                    putExtra("diff", "art")
                }
                "geography" -> intent.apply {
                    putExtra("diff", "geography")
                }
                "animals" -> intent.apply {
                    putExtra("diff", "animals")
                }
                "comics" -> intent.apply {
                    putExtra("diff", "comics")
                }
                "anime" -> intent.apply {
                    putExtra("diff", "anime")
                }
                "gadgets" -> intent.apply {
                    putExtra("diff", "gadgets")
                }
                "coding" -> intent.apply {
                    putExtra("diff", "coding")
                }
                "cartoon" -> intent.apply {
                    putExtra("diff", "cartoon")
                }
                "gk" -> intent.apply {
                    putExtra("diff", "gk")
                }
                "covid19" -> intent.apply {
                    putExtra("diff", "covid19")
                }
                "marvel" -> intent.apply {
                    putExtra("diff", "marvel")
                }
            }
            startActivity(intent)
        }

    }

}