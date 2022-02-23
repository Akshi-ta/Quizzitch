package com.example.quizzitch.ui.collab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentTransaction
import com.example.quizzitch.R

class BuzzerRoundFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buzzer_round, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val createRoomTV: TextView = view.findViewById(R.id.createRoomTV)
        createRoomTV.setOnClickListener{

            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.buzzer, CreateRoomFragment())
            transaction.addToBackStack("createRoom")
            transaction.commit()
        }

        val joinRoomTV: TextView = view.findViewById(R.id.joinRoomTV)
        joinRoomTV.setOnClickListener{

            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.buzzer, JoinRoomFragment())
                .addToBackStack("joinRoom")
            transaction.commit()
        }
    }


}