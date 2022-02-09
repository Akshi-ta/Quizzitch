package com.example.quizzitch.ui.collab

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.quizzitch.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.joinAll

class HostGameFragment : Fragment() {
    val mauth = FirebaseAuth.getInstance()
    val uid: String = mauth.currentUser!!.uid
    val store: FirebaseFirestore = FirebaseFirestore.getInstance()
    val ref = store.collection("games").document(uid)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_host_game, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val createRoomCard: CardView = view.findViewById(R.id.createRoomCard)
        val createText : TextView = view.findViewById(R.id.textView28)
        val joinTextView: TextView = view.findViewById(R.id.textView29)
        val joinRoomCard: CardView = view.findViewById(R.id.joinRoomCard)
        joinRoomCard.visibility = View.GONE
        createText.setOnClickListener{
            joinRoomCard.visibility = View.GONE
            createRoomCard.visibility = View.VISIBLE
        }
        joinTextView.setOnClickListener{
            createRoomCard.visibility = View.GONE
            joinRoomCard.visibility = View.VISIBLE
        }

        val maxPlayerEdit: EditText = view.findViewById(R.id.editTextMaxPlayers)
        val generate: Button = view.findViewById(R.id.button5)
        generate.setOnClickListener{
            val a = maxPlayerEdit.text.toString()
            val bets: String = view.findViewById<EditText>(R.id.editTextBets).text.toString()
            if(a.isEmpty() || bets.isEmpty())
            {
                Snackbar.make(generate,"Enter All Fields First.", Snackbar.LENGTH_LONG).show()
            }else if(a.toInt()>4)
            {
                Snackbar.make(generate,"Max players supported upto 4.", Snackbar.LENGTH_LONG).show()
            }else if(bets.toInt()<100)
            {
                Snackbar.make(generate,"Minimum bet limit is 100", Snackbar.LENGTH_LONG).show()
            }else
            {

                MakeRoom(a, bets, view)
                generate.isClickable = false
                joinRoomCard.visibility = View.VISIBLE
                createRoomCard.visibility = View.GONE
                createText.isClickable = false
            }

        }

    }

    fun MakeRoom(a: String, bets:String, view: View) {
//        store.collection("games").document(uid).get().addOnSuccessListener {
//            val temp: HashMap<String, Any> = it.data as HashMap<String, Any>
//            if(temp["i"]==null) {
//                temp["i"] = 1
//                store.collection("games").document(uid).update(temp)
//            }
//
//            val gameMap: HashMap<String, Any> = hashMapOf()
//
//            temp[temp["i"].toString()] = gameMap
//        }

        store.collection("gamesCount").document("i").get().addOnSuccessListener {
            val i:Int = it["i"].toString().toInt()
            val gamesMap: HashMap<String, Any> = hashMapOf()
            val temp: HashMap<String, Any> = hashMapOf()
            val host: ArrayList<String> = arrayListOf()

            store.collection("desc").document(uid).get().addOnSuccessListener {
                host.add(it["displayName"].toString()); host.add(uid)
                gamesMap["host"] = host
                gamesMap["maxP"] = a.toInt()
                gamesMap["bets"] = bets.toInt()
                val otherPlayers: ArrayList<ArrayList<String>> = arrayListOf()
                gamesMap["otherPlayers"] = otherPlayers
                gamesMap["isActive"] = true
                temp["anyGameActive"] = i

                temp[i.toString()] = gamesMap
                store.collection("games").document(uid).update(temp).addOnSuccessListener {
                    val count: HashMap<String, Any> = hashMapOf()
                    count["i"] = i+1
                    store.collection("gamesCount").document("i").update(count)
                    Snackbar.make(requireView(), "Room has been created", Snackbar.LENGTH_SHORT).show()
                    updateJoinCard(view)
                }.addOnFailureListener{
                    Snackbar.make(requireView(), "Failed to create room.", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }


    fun updateJoinCard(view: View) {

        store.collection("games").document(uid).get().addOnSuccessListener {
            val p1: TextView = view.findViewById(R.id.player1)
            val p2: TextView = view.findViewById(R.id.player2)
//            val p3: TextView = view.findViewById(R.id.player3)
//            val p4: TextView = view.findViewById(R.id.player4)
            ref.addSnapshotListener{snapshot, e ->
                e?.let {
                    Toast.makeText(requireContext(), "An error occured.", Toast.LENGTH_LONG).show()
                }
                snapshot?.let {
                    val i: Int = it["anyGameActive"].toString().toInt()
                    val gameS: HashMap<String, Any> = it[i.toString()] as HashMap<String, Any>
                    p1.text = (gameS["host"] as ArrayList<String>)[0] + " (host)"
                    val arr: ArrayList<ArrayList<String>?>? = it["otherPlayers"] as ArrayList<ArrayList<String>?>?
                    var s: String = ""

                    if(arr!=null)
                    {
                        for(j in arr)
                        {
                            if(j!=null)
                                s+=j[0]+"\n"
                        }
                    }

                    p2.text = s
                }
            }
        }
    }

}