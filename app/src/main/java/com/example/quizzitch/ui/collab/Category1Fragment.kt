package com.example.quizzitch.ui.collab

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentTransaction
import com.example.quizzitch.R

class Category1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category1 , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val historyI: ImageButton = view.findViewById(R.id.historyI)
        val politicsi: ImageButton = view.findViewById(R.id.politicsi)
        val dancei: ImageButton = view.findViewById(R.id.dancei)
        val bollyi: ImageButton = view.findViewById(R.id.bollyi)
        val sportsi: ImageButton = view.findViewById(R.id.sporti)
        val culturei: ImageButton = view.findViewById(R.id.culturei)
        val sciencei: ImageButton = view.findViewById(R.id.sciencei)
        val computersi: ImageButton = view.findViewById(R.id.computersi)
        val mythologyi: ImageButton = view.findViewById(R.id.mythologyi)
        val art: ImageButton = view.findViewById(R.id.arti)
        val geo: ImageButton = view.findViewById(R.id.geoi)
        val animals: ImageButton = view.findViewById(R.id.animalsi)
        val comic: ImageButton = view.findViewById(R.id.comicsi)
        val anime: ImageButton = view.findViewById(R.id.animei)
        val gadgets: ImageButton = view.findViewById(R.id.gadgetsi)
        val coding: ImageButton = view.findViewById(R.id.codingi)
        val cartoon: ImageButton = view.findViewById(R.id.cartooni)
        val gk: ImageButton = view.findViewById(R.id.gki)
        val covid: ImageButton = view.findViewById(R.id.covid19i)
        val marvel: ImageButton = view.findViewById(R.id.marveli)

        historyI.setOnClickListener { operator(view, 23) } //23
        politicsi.setOnClickListener { operator(view, 24) } //24
        dancei.setOnClickListener { operator(view, 13) } //
        bollyi.setOnClickListener { operator(view, 11) }
        sportsi.setOnClickListener { operator(view, 21) }
        culturei.setOnClickListener { operator(view, 10) }
        sciencei.setOnClickListener { operator(view, 17) }
        computersi.setOnClickListener { operator(view, 18) }
        mythologyi.setOnClickListener { operator(view, 20) }
        art.setOnClickListener { operator(view, 25) }
        geo.setOnClickListener { operator(view, 22) }
        animals.setOnClickListener { operator(view, 27) }
        comic.setOnClickListener { operator(view, 29) }
        anime.setOnClickListener { operator(view, 31) }
        gadgets.setOnClickListener { operator(view, 30) }
        coding.setOnClickListener { operator(view, 28) }
        cartoon.setOnClickListener { operator(view, 32) }
        gk.setOnClickListener { operator(view, 9) }  //10
        covid.setOnClickListener { operator(view, 19) }
        marvel.setOnClickListener { operator(view, 15) }
    }

    fun operator(view: View,code:Int) {
        val fragment: Fragment = SettingsFragment()
        val bundle = Bundle()
        bundle.putString("category", code.toString())
        bundle.putString("roomcode", requireArguments().getString("roomcode"))
        bundle.putString("hostuid", requireArguments().getString("hostuid"))
        fragment.arguments = bundle
        val cons: ConstraintLayout = view.findViewById(R.id.sparecatergory)
        cons.visibility = View.GONE
        val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.category, fragment)
        transaction.addToBackStack("settings")
        transaction.commit()
    }
}