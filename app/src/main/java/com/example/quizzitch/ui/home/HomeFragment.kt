package com.example.quizzitch.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizzitch.R
import com.example.quizzitch.databinding.FragmentHomeBinding

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
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topic: String
        val bundle = Bundle()

        val history: ImageButton = view.findViewById(R.id.imageView2)
        val politics: ImageButton = view.findViewById(R.id.imageView3)
        val dance: ImageButton = view.findViewById(R.id.imageView4)
        val bollywood: ImageButton = view.findViewById(R.id.imageView5)
        val sports: ImageButton = view.findViewById(R.id.imageView6)
        val culture: ImageButton = view.findViewById(R.id.imageView7)
        val science: ImageButton = view.findViewById(R.id.imageView8)
        val computers: ImageButton = view.findViewById(R.id.imageView9)
        val mythology: ImageButton = view.findViewById(R.id.imageView10)
        val art: ImageButton = view.findViewById(R.id.imageView11)
        val geography: ImageButton = view.findViewById(R.id.imageView12)
        val animals: ImageButton = view.findViewById(R.id.imageView13)
        val comics: ImageButton = view.findViewById(R.id.imageView14)
        val anime: ImageButton = view.findViewById(R.id.imageView15)
        val gadgets: ImageButton = view.findViewById(R.id.imageView16)
        val coding: ImageButton = view.findViewById(R.id.imageView17)
        val cartoon: ImageButton = view.findViewById(R.id.imageView18)
        val gk: ImageButton = view.findViewById(R.id.imageView19)
        val covid19: ImageButton = view.findViewById(R.id.imageView20)
        val marvel: ImageButton = view.findViewById(R.id.imageView21)

            history.setOnClickListener() {
               val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("history", "topic")
                    Toast.makeText(activity, "History is selected", Toast.LENGTH_SHORT).show()
            }
                startActivity(intent)
            }
            politics.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("politics", "topic")
                    Toast.makeText(activity, "Politics is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            dance.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("dance", "topic")
                    Toast.makeText(activity, "Dance is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            bollywood.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("bollywood", "topic")
                    Toast.makeText(activity, "Bollywood is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            sports.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("sports", "topic")
                    Toast.makeText(activity, "Sports is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            culture.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("culture", "topic")
                    Toast.makeText(activity, "Culture is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            science.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("science", "topic")
                    Toast.makeText(activity, "Science is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            computers.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("computers", "topic")
                    Toast.makeText(activity, "Computers is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            mythology.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("mythology", "topic")
                    Toast.makeText(activity, "History is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            art.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("art", "topic")
                    Toast.makeText(activity, "Art is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            geography.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("geography", "topic")
                    Toast.makeText(activity, "Geography is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            animals.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("animals", "topic")
                    Toast.makeText(activity, "Animals is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            comics.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("comics", "topic")
                    Toast.makeText(activity, "Comics is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            anime.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("anime", "topic")
                    Toast.makeText(activity, "Anime is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            gadgets.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("gadgets", "topic")
                    Toast.makeText(activity, "Gadgets is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            coding.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("coding", "topic")
                    Toast.makeText(activity, "Coding is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            cartoon.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("cartoon", "topic")
                    Toast.makeText(activity, "Cartoon is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            gk.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("gk", "topic")
                    Toast.makeText(activity, "GK is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }

            covid19.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("covid19", "topic")
                    Toast.makeText(activity, "COVID 19 is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
            marvel.setOnClickListener() {
                val intent = Intent(context, LevelScreen::class.java).apply {
                    putExtra("marvel", "topic")
                    Toast.makeText(activity, "Marvel is selected", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }

        }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
