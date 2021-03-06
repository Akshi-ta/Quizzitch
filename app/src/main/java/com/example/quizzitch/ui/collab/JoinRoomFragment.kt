package com.example.quizzitch.ui.collab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentTransaction
import com.example.quizzitch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class JoinRoomFragment : Fragment() {
    val mauth: FirebaseAuth = FirebaseAuth.getInstance()
    val uid: String = mauth.currentUser!!.uid
    val store: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_join_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val roomCode: EditText = view.findViewById(R.id.editText)
        val joinBtNew: Button = view.findViewById(R.id.joinBtNew)


        joinBtNew.setOnClickListener{
            if(roomCode.text.toString().isEmpty())
            {
                Toast.makeText(requireContext(), "Room Code Empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }else if(roomCode.text.toString().toInt()<0)
            {
                Toast.makeText(requireContext(), "Invalid room Code", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            store.collection("games").get().addOnSuccessListener {
                var hostUid: String = ""
                hostUid = help(it,roomCode)

                if(hostUid=="")
                {
                    Toast.makeText(requireContext(), "Room not found", Toast.LENGTH_LONG).show()
                    return@addOnSuccessListener
                }

                store.collection("games").document(hostUid).get().addOnSuccessListener {
                    val total: HashMap<String, Any> = it.data as HashMap<String, Any>
                    val gameData: HashMap<String, Any> = total[roomCode.text.toString()] as HashMap<String, Any>
                    val maxPlayers: String = gameData["maxPlayers"].toString()
                    //Toast.makeText(requireContext(), "host join case pending", Toast.LENGTH_LONG).show()
                    var b = false
                    if((gameData["host"]as HashMap<String, Any>)["uid"]==uid || (gameData["otherPlayers"]as HashMap<String, Any>)[uid]!=null)
                    {
                        b = true
                    }

                    if(b)
                    {
                        Toast.makeText(requireContext(), "already joined", Toast.LENGTH_LONG).show()
                        val cons: ConstraintLayout = view.findViewById(R.id.spareJoin)
                        cons.visibility = View.GONE
                        val bundle = Bundle()
                        bundle.putString("roomcode", roomCode.text.toString())
                        //Toast.makeText(requireContext(), hostUid, Toast.LENGTH_LONG).show()
                        bundle.putString("hostuid", hostUid)
                        bundle.putString("i", roomCode.text.toString())
                        val fragment: Fragment = PlayersFragment()
                        fragment.arguments = bundle
                        val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                        transaction.replace(R.id.join, fragment)
                        transaction.addToBackStack("joins")
                        transaction.commit()
                        return@addOnSuccessListener
                    }
                    if(maxPlayers.toInt()<=(gameData["otherPlayers"]as HashMap<String, Any>)["no"].toString().toInt())
                    {
                        Toast.makeText(requireContext(), "Room Already full", Toast.LENGTH_LONG).show()
                        return@addOnSuccessListener
                    }
                    Toast.makeText(requireContext(), "Bet Check remaining", Toast.LENGTH_LONG).show()
                    store.collection("desc").document(uid).get().addOnSuccessListener {
                        val m: HashMap<String, Any> = hashMapOf()
                        m["name"] = it["displayName"].toString()
                        m["uid"] = uid
                        val ma: HashMap<String, Any> = gameData["otherPlayers"] as HashMap<String, Any>
                        ma[uid] = m
                        ma["no"] = ma["no"].toString().toInt() + 1
                        gameData["otherPlayers"] = ma
                        total[roomCode.text.toString()] = gameData
                        store.collection("games").document(hostUid).update(total).addOnSuccessListener {
                            Toast.makeText(requireContext(), "Room Joined", Toast.LENGTH_LONG).show()
                            val cons: ConstraintLayout = view.findViewById(R.id.spareJoin)
                            cons.visibility = View.GONE
                            val bundle = Bundle()
                            bundle.putString("roomcode", roomCode.text.toString())
                            bundle.putString("hostuid", hostUid)
                            val fragment: Fragment = PlayersFragment()
                            fragment.arguments = bundle
                            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                            transaction.replace(R.id.join, fragment)
                            transaction.addToBackStack("joinl")
                            transaction.commit()
                        }
                    }

                }
            }
        }
    }


    fun help(it: QuerySnapshot, roomCode: TextView):String{
        for(user in it)
        {
            val userData: HashMap<String, Any> = user.data as HashMap<String, Any>
            for(q in userData)
            {
                if(q.key == roomCode.text.toString())
                {
                    Toast.makeText(requireContext(), ((q.value as HashMap<String, Any>)["host"] as HashMap<String, Any>)["uid"].toString() , Toast.LENGTH_SHORT).show()
                    return ((q.value as HashMap<String, Any>)["host"] as HashMap<String, Any>)["uid"].toString()
                }
            }
        }
        return ""
    }
}