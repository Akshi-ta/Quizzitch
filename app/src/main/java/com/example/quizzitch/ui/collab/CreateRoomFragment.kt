package com.example.quizzitch.ui.collab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.quizzitch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CreateRoomFragment : Fragment() {
    val mauth: FirebaseAuth = FirebaseAuth.getInstance()
    val store: FirebaseFirestore = FirebaseFirestore.getInstance()
    val uid: String = mauth.currentUser!!.uid
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val generateBt: Button = view.findViewById(R.id.generateBt)
        val playerNo: EditText = view.findViewById(R.id.playerNo)
        val bets: EditText = view.findViewById(R.id.bets)
        generateBt.setOnClickListener{
            if(playerNo.text.toString().toInt()<0)
            {
                Toast.makeText(requireContext(), "Invalid player No", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }else if(bets.text.toString().toInt()<100)
            {
                Toast.makeText(requireContext(), "Invalid bet amount", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            //check if you do not have a document, then create it
            store.collection("games").document(uid).get().addOnSuccessListener {
                if(it.data==null)
                {
                    val map: HashMap<String, Any> = hashMapOf()
                    map["anyGameActive"] = -1
                    store.collection("games").document(uid).set(map).addOnSuccessListener {
                        //Toast.makeText(requireContext(), "Was empty before", Toast.LENGTH_LONG).show()
                    }
                    //Toast.makeText(requireContext(), "Was empty before", Toast.LENGTH_LONG).show()
                }
            }

            //now you will always have a document in database, now create a new game...
            //for that first get gamesCount, that will be room No.
            store.collection("gamesCount").document("i").get().addOnSuccessListener {
                val roomCode: String = (it.data as HashMap<String, Any>)["i"].toString()
                val data: HashMap<String, Any> = hashMapOf()
                val newGame: HashMap<String, Any> = hashMapOf()
                newGame["bets"] = bets.text.toString()
                val host: HashMap<String, Any> = hashMapOf()
                store.collection("desc").document(uid).get().addOnSuccessListener {
                    host["name"] = it["displayName"].toString()
                    host["uid"] = uid
                    newGame["host"] = host
                    newGame["maxPlayers"] = playerNo.text.toString()
                    val otherPlayers:HashMap<String, Any> = hashMapOf()
                    otherPlayers["no"] = 1
                    newGame["otherPlayers"] = otherPlayers
                    val test: HashMap<String, Any> = hashMapOf()
                    test["test"] = "test"
                    newGame["responses"] = test
                    data[roomCode] = newGame
                    data["anyGameActive"] = roomCode.toInt()
                    store.collection("games").document(uid).update(data).addOnSuccessListener {
                        Toast.makeText(requireContext(), "Room Created", Toast.LENGTH_LONG).show()
                        val m: HashMap<String, Any> = hashMapOf()
                        m["i"] = roomCode.toInt()+1
                        store.collection("gamesCount").document("i").update(m)

                        val cons: ConstraintLayout = view.findViewById(R.id.spareCreate)
                        cons.visibility = View.GONE
                        val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                        val bundle = Bundle()
                        bundle.putString("roomcode", roomCode)
                        bundle.putString("hostuid", uid)
                        bundle.putString("i", roomCode)
                        val fragment: Fragment = PlayersFragment()
                        fragment.arguments = bundle
                        transaction.replace(R.id.createRoom, fragment)
                        transaction.addToBackStack("players")
                        transaction.commit()
                    }
                }
            }




        }
    }
    

}