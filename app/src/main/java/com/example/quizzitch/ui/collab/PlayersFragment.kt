package com.example.quizzitch.ui.collab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.quizzitch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PlayersFragment : Fragment() {
    val mauth: FirebaseAuth = FirebaseAuth.getInstance()
    val uid: String = mauth.currentUser!!.uid
    val store: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val roomcodeTV: TextView = view.findViewById(R.id.textView57)
        val roomcode: String? = requireArguments().getString("roomcode")
        val playerMap: HashMap<String,Any> = hashMapOf()
        playerMap["player1"] = view.findViewById<TextView>(R.id.player1)
        playerMap["player2"] = view.findViewById<TextView>(R.id.player2)
        playerMap["player3"] = view.findViewById<TextView>(R.id.player3)
        playerMap["player4"] = view.findViewById<TextView>(R.id.player4)
        val startBt: Button = view.findViewById(R.id.button8)


        if(roomcode!=null)
        {
            roomcodeTV.text = roomcode
        }

        val hostUid: String? = requireArguments().getString("hostuid")
        if(hostUid!=uid)
        {
            startBt.visibility = View.GONE
        }
        //Toast.makeText(requireContext(), hostUid, Toast.LENGTH_LONG).show()
        if(hostUid!=null)
        {
            store.collection("games").document(hostUid).addSnapshotListener{it, e->
                e?.let {
                    Toast.makeText(requireContext(), "Snapshot error occured", Toast.LENGTH_LONG).show()
                }
                it?.let {
                    val userData: HashMap<String, Any> = it.data as HashMap<String, Any>
                    val gameData: HashMap<String, Any> = userData[roomcode] as HashMap<String, Any>
                    val otherPlayers: HashMap<String, Any> = gameData["otherPlayers"] as HashMap<String, Any>
                    (playerMap["player1"] as TextView).text = (gameData["host"] as HashMap<String, Any>)["name"].toString()
                    var j = 2
                    for(i in otherPlayers)
                    {
                        if(i.key!="no")
                        {
                            (playerMap["player$j"]as TextView).text = (i.value as HashMap<String, Any>)["name"].toString()
                            j++
                        }
                    }
                }
            }
        }
    }
}