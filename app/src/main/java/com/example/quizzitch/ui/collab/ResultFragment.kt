package com.example.quizzitch.ui.collab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.quizzitch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ResultFragment : Fragment() {
    val mauth: FirebaseAuth = FirebaseAuth.getInstance()
    val uid: String = mauth.currentUser!!.uid
    val store: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val roomcode: Int = requireArguments().getString("roomcode")!!.toInt()
        val player1name: TextView = view.findViewById(R.id.player1result)
        val player2name: TextView = view.findViewById(R.id.player2result)
        val player3name: TextView = view.findViewById(R.id.player3result)
        val player4name: TextView = view.findViewById(R.id.player4result)
        val players: HashMap<String, Any> = hashMapOf()
        players["player1name"] = player1name; players["player2name"] = player2name
        players["player3name"] = player3name;players["player4name"] = player4name
        val player1score: TextView = view.findViewById(R.id.player1score)
        val player2score: TextView = view.findViewById(R.id.player2score)
        val player3score: TextView = view.findViewById(R.id.player3score)
        val player4score: TextView = view.findViewById(R.id.player4score)

        store.collection("games").document(uid).addSnapshotListener{it, e->
            e?.let {
                Toast.makeText(requireContext(), "An Error Occured", Toast.LENGTH_SHORT).show()
            }
            it?.let {
                val map:HashMap<String, Any> = it.data as HashMap<String, Any>
                val quesions: HashMap<String, Any> = (map[roomcode.toString()]as HashMap<String, Any>)["questions"] as HashMap<String, Any>
                val responses: HashMap<String, Any> = (map[roomcode.toString()]as HashMap<String, Any>)["responses"] as HashMap<String, Any>
                var iterator = 1
                for(i in responses)
                {
                    store.collection("desc").document(i.key).get().addOnSuccessListener {
                        (players["player"+i.toString()+"name"]as TextView).text = it["displayName"].toString()
                        iterator++
                    }
                }
            }
        }
    }
}