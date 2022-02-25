package com.example.quizzitch.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
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

        val fragment: Fragment = LevelScreen()
        val bundle =  Bundle()

        history.setOnClickListener {
                val topic = "history"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "History is selected", Toast.LENGTH_SHORT).show()
            }
            politics.setOnClickListener {
                val topic = "politics"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Politics is selected", Toast.LENGTH_SHORT).show()
            }
            dance.setOnClickListener {
                val topic = "dance"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Dance is selected", Toast.LENGTH_SHORT).show()
            }
            bollywood.setOnClickListener {
                val topic = "bollywood"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Bollywood is selected", Toast.LENGTH_SHORT).show()
                }

            sports.setOnClickListener {
                val topic = "sports"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Sports is selected", Toast.LENGTH_SHORT).show()
                }
            culture.setOnClickListener {
                val topic = "culture"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Culture is selected", Toast.LENGTH_SHORT).show()
                }

            science.setOnClickListener {
                val topic = "science"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Science is selected", Toast.LENGTH_SHORT).show()
                }

            computers.setOnClickListener {
                val topic = "computers"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Computers is selected", Toast.LENGTH_SHORT).show()
                }

            mythology.setOnClickListener {
                val topic = "mythology"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "History is selected", Toast.LENGTH_SHORT).show()
                }

            art.setOnClickListener {
                val topic = "art"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Art is selected", Toast.LENGTH_SHORT).show()
                }

            geography.setOnClickListener {
                val topic = "geography"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Geography is selected", Toast.LENGTH_SHORT).show()
                }

            animals.setOnClickListener {
                val topic = "animals"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Animals is selected", Toast.LENGTH_SHORT).show()
                }

            comics.setOnClickListener {
                val topic = "comics"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Comics is selected", Toast.LENGTH_SHORT).show()
                }

            anime.setOnClickListener {
                val topic = "anime"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Anime is selected", Toast.LENGTH_SHORT).show()
                }

            gadgets.setOnClickListener {
                val topic = "gadgets"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Gadgets is selected", Toast.LENGTH_SHORT).show()
                }

            coding.setOnClickListener {
                val topic = "coding"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Coding is selected", Toast.LENGTH_SHORT).show()
                }

            cartoon.setOnClickListener {
                val topic = "cartoon"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Cartoon is selected", Toast.LENGTH_SHORT).show()
                }

            gk.setOnClickListener {
                val topic = "gk"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "GK is selected", Toast.LENGTH_SHORT).show()
                }

            covid19.setOnClickListener {
                val topic = "covid19"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "COVID 19 is selected", Toast.LENGTH_SHORT).show()
                }

            marvel.setOnClickListener {
                val topic = "marvel"
                bundle.putString("data", topic)
                fragment.arguments = bundle
                val cons: ConstraintLayout = view.findViewById(R.id.homer)
                cons.visibility = View.GONE
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.homeR, fragment)
                transaction.addToBackStack("category")
                transaction.commit()
                    Toast.makeText(activity, "Marvel is selected", Toast.LENGTH_SHORT).show()
                }


        }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
