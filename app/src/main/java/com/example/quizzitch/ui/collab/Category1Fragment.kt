package com.example.quizzitch.ui.collab

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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

        historyI.setOnClickListener { operator() } //23
        politicsi.setOnClickListener { operator() } //24
        dancei.setOnClickListener { operator() } //
        bollyi.setOnClickListener { operator() }
        sportsi.setOnClickListener { operator() }
        culturei.setOnClickListener { operator() }
        sciencei.setOnClickListener { operator() }
        computersi.setOnClickListener { operator() }
        mythologyi.setOnClickListener { operator() }
        art.setOnClickListener { operator() }
        geo.setOnClickListener { operator() }
        animals.setOnClickListener { operator() }
        comic.setOnClickListener { operator() }
        anime.setOnClickListener { operator() }
        gadgets.setOnClickListener { operator() }
        coding.setOnClickListener { operator() }
        cartoon.setOnClickListener { operator() }
        gk.setOnClickListener { operator() }  //10
        covid.setOnClickListener { operator() }
        marvel.setOnClickListener { operator() }
    }

    fun operator() {

    }
}