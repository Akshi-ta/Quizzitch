package com.example.quizzitch.ui.collab

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.quizzitch.R
import com.google.android.material.snackbar.Snackbar
import com.google.common.hash.HashCode
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

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

        val createTextView: TextView = view.findViewById(R.id.textView28)
        val joinTextView: TextView = view.findViewById(R.id.textView29)
        val createRoomCard: CardView = view.findViewById(R.id.createRoomCard)
        val joinRoomCard: CardView = view.findViewById(R.id.joinRoomCard)
        val generateRoom: Button = view.findViewById(R.id.button5)
        val maxPlayers: EditText = view.findViewById(R.id.editTextMaxPlayers)
        val bets: EditText = view.findViewById(R.id.editTextBets)
        val leaveBt: Button = view.findViewById(R.id.leaveBt)
        val joinBt: Button = view.findViewById(R.id.joinBt)
        store.collection("games").document(uid).get().addOnSuccessListener {
            if(it["anyGameActive"]==null)
            {
                val userGames: HashMap<String, Any> = hashMapOf()
                userGames["anyGameActive"] = -1
                basicCreate(view)
                store.collection("games").document(uid).update(userGames)
            }else
            {
                if(it["anyGameActive"].toString().toInt()==-1)
                {
                    basicCreate(view)
                }else
                {
                    basicJoin(view,1)
                }
            }
        }

        joinTextView.setOnClickListener{
            basicJoin(view,0)
        }
        createTextView.setOnClickListener{
            basicCreate(view)
        }

        generateRoom.setOnClickListener{
            val players: String = maxPlayers.text.toString()
            val bet: String = bets.text.toString()
            if(players.isEmpty())
            {
                Toast.makeText(requireContext(), "Enter Players", Toast.LENGTH_LONG).show()
            }else if(players.toInt()>4)
            {
                Toast.makeText(requireContext(), "Max Players allowed are 4", Toast.LENGTH_LONG).show()
            }else if(bet.isEmpty()|| bet.toInt()<100)
            {
                Toast.makeText(requireContext(), "Bet amount cannot be less than 100", Toast.LENGTH_LONG).show()
            }else
            {
                generateRoom.isClickable = false
                createRoom(view, players, bet)
            }
        }

        leaveBt.setOnClickListener{
            leaveRoom()
        }

        joinBt.setOnClickListener{
            basicJoin(view,2)
        }



    }

    private fun basicCreate(view: View){
        val createTextView: TextView = view.findViewById(R.id.textView28)
        val joinTextView: TextView = view.findViewById(R.id.textView29)
        val createRoomCard: CardView = view.findViewById(R.id.createRoomCard)
        val joinRoomCard: CardView = view.findViewById(R.id.joinRoomCard)
        createTextView.isClickable = false
        joinTextView.isClickable = true
        createRoomCard.visibility = View.VISIBLE
        joinRoomCard.visibility = View.GONE
    }

    private fun basicJoin(view: View, switch: Int){
        val createTextView: TextView = view.findViewById(R.id.textView28)
        val joinTextView: TextView = view.findViewById(R.id.textView29)
        val createRoomCard: CardView = view.findViewById(R.id.createRoomCard)
        val joinRoomCard: CardView = view.findViewById(R.id.joinRoomCard)
        val enterRoomCode: EditText = view.findViewById(R.id.enterRoomCode)
        val joinBt: Button = view.findViewById(R.id.joinBt)
        val leaveBt: Button = view.findViewById(R.id.leaveBt)
        val roomCode: TextView = view.findViewById(R.id.roomCode)
        createTextView.isClickable = true
        joinTextView.isClickable = false
        createRoomCard.visibility = View.GONE
        joinRoomCard.visibility = View.VISIBLE

        if(switch==0)
        {
            enterRoomCode.visibility = View.VISIBLE
            joinBt.visibility = View.VISIBLE
            leaveBt.visibility = View.GONE
            roomCode.visibility = View.GONE
        }else if(switch==1) {
            //Toast.makeText(requireContext(), "1 was called", Toast.LENGTH_LONG).show()
            createTextView.isClickable = false
            createRoomCard.visibility = View.GONE
            joinRoomCard.visibility = View.VISIBLE
            enterRoomCode.visibility = View.GONE
            joinBt.visibility = View.GONE
            leaveBt.visibility = View.VISIBLE
            roomCode.visibility = View.VISIBLE
        }else if(switch==2)
        {
            val i: Int = enterRoomCode.text.toString().toInt()
            store.collection("games").get().addOnSuccessListener {
                var hostUid: String =""
                for(s in it)
                {
                    val person: HashMap<String, Any> = s.data as HashMap<String, Any>
                    for(t in person)
                    {
                        if(t.key==i.toString()){
                            val game: HashMap<String, Any> = t.value as HashMap<String, Any>
                            val host: HashMap<String, Any> = game["host"] as HashMap<String, Any>
                            hostUid = host["uid"].toString()
                        }
                    }
                }
                if(hostUid=="")
                {
                    Toast.makeText(requireContext(), "Room not found", Toast.LENGTH_LONG).show()
                    return@addOnSuccessListener
                }

                Toast.makeText(requireContext(),"Joining Room", Toast.LENGTH_LONG).show()
                store.collection("games").document(hostUid).get().addOnSuccessListener {
                    val gameData: HashMap<String, Any> = it[i.toString()] as HashMap<String, Any>
                    val otherPlayers: HashMap<String, Any> = gameData["otherPlayers"] as HashMap<String, Any>
                    if(otherPlayers["no"].toString().toInt()>=gameData["maxPlayers"].toString().toInt() && otherPlayers[uid]==null)
                    {
                        Toast.makeText(requireContext(), "Room is already full", Toast.LENGTH_LONG).show()
                        return@addOnSuccessListener
                    }
                    if((gameData["host"] as HashMap<String, Any>)["uid"].toString()==uid)
                    {
                        liveUpdateJoin(uid, i,view)
                        return@addOnSuccessListener
                    }
                    if(otherPlayers[uid]==null)
                        otherPlayers["no"] = otherPlayers["no"].toString().toInt() +1
                    val myData: HashMap<String, Any> = hashMapOf()
                    myData["uid"] = uid
                    store.collection("desc").document(uid).get().addOnSuccessListener {
                        myData["name"] = it["displayName"].toString()
                        otherPlayers[uid] = myData
                        gameData["otherPlayers"] = otherPlayers
                        val up: HashMap<String, Any> = hashMapOf()
                        up[i.toString()] = gameData
                        store.collection("games").document(hostUid).update(up).addOnSuccessListener {
                            Toast.makeText(requireContext(), "Room Joined",Toast.LENGTH_LONG).show()
                            store.collection("games").document(uid).get().addOnSuccessListener {
                                var updateMy: HashMap<String, Any> = hashMapOf()
                                if(it.data!=null)
                                    updateMy= it.data as HashMap<String, Any>
                                updateMy["anyGameActive"] = i
                                liveUpdateJoin(hostUid, i, view)
                                if(it.data!=null)
                                    store.collection("games").document(uid).update(updateMy)
                                else
                                    store.collection("games").document(uid).set(updateMy)
                            }
                        }

                    }

                }


            }
        }
    }

    private fun liveUpdateJoin(hostUid:String, i:Int, view: View){
        val player1: TextView = view.findViewById(R.id.player1)
        val player2: TextView = view.findViewById(R.id.player2)
        val roomCode: TextView = view.findViewById(R.id.roomCode)
        val maxPlayers: TextView = view.findViewById(R.id.maxPlayers)
        val enterRoomCode: EditText = view.findViewById(R.id.enterRoomCode)
        val leaveBt: Button = view.findViewById(R.id.leaveBt)
        val joinBt: Button = view.findViewById(R.id.joinBt)

        leaveBt.visibility = View.VISIBLE
        joinBt.visibility = View.GONE
        enterRoomCode.visibility = View.GONE

        store.collection("games").document(hostUid).get().addOnSuccessListener {
            val gameData: HashMap<String, Any> = (it[i.toString()] as HashMap<String, Any>)
            player1.text = (gameData["host"] as HashMap<String,Any>)["name"].toString()

            val otherPlayers: HashMap<String, Any> = gameData["otherPlayers"] as HashMap<String, Any>
            var player: String = ""
            for(p in otherPlayers)
            {
                if(p.key!="no")
                {
                    val user: HashMap<String, Any> = p.value as HashMap<String, Any>
                    player+=user["name"].toString() + "\n"
                }
            }
            player2.text = player
            roomCode.text = i.toString()
            val s:String = maxPlayers.text.toString()
            maxPlayers.text = "Max Players: -" + gameData["maxPlayers"].toString()
        }

    }

    private fun masterJoin() {

    }

    private fun masterCreate(){

    }

    private fun leaveRoom(){
        Toast.makeText(requireContext(), "Leave fun pending", Toast.LENGTH_LONG).show()

    }

    private fun createRoom(view: View, players: String,bets: String) {
//        store.collection("games").document(uid).get().addOnSuccessListener {
//            if((it["anyGameActive"].toString().toInt())!=-1)
//            {
//                Toast.makeText(requireContext(), "Room already active: Error", Toast.LENGTH_LONG).show()
//                return@addOnSuccessListener
//            }
//        }

        store.collection("gamesCount").document("i").get().addOnSuccessListener {
            val i: Int = it["i"].toString().toInt()
            val userGames: HashMap<String, Any> = hashMapOf()
            val myGame: HashMap<String,Any> = hashMapOf()
            myGame["maxPlayers"] = players
            myGame["bets"] = bets
            val aboutHost: HashMap<String, Any> = hashMapOf()
            val otherPlayers: HashMap<String, Any> = hashMapOf()
            otherPlayers["no"] = 1
            aboutHost["uid"] = uid
            myGame["otherPlayers"] = otherPlayers
            userGames["anyGameActive"] = i
            store.collection("desc").document(uid).get().addOnSuccessListener {
                aboutHost["name"] = it["displayName"].toString()
                myGame["host"] = aboutHost
                userGames[i.toString()] = myGame
                store.collection("games").document(uid).update(userGames).addOnSuccessListener {
                    val newI: HashMap<String, Any> = hashMapOf()
                    newI["i"] = i+1
                    store.collection("gamesCount").document("i").update(newI)
                    Toast.makeText(requireContext(), "Room created", Toast.LENGTH_LONG).show()
                    basicJoin(view,0)
                }
            }
        }
    }
}