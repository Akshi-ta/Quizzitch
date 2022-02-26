package com.example.quizzitch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Time

class PasteLinkFragment : Fragment() {
    val mauth: FirebaseAuth = FirebaseAuth.getInstance()
    val store: FirebaseFirestore = FirebaseFirestore.getInstance()
    val uid: String = mauth.currentUser!!.uid

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paste_link , container , false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        val generatebutton: Button = view.findViewById(R.id.generatebutton)
        val pastelink : EditText = view.findViewById(R.id.pastelink)
        val time: EditText = view.findViewById(R.id.editTextTime)



        generatebutton.setOnClickListener {
            if(pastelink.text.toString().isEmpty())
            {
                Toast.makeText(requireContext(), "Please enter link first", Toast.LENGTH_SHORT).show()
            }else if(time.text.toString().isEmpty())
            {
                Toast.makeText(requireContext(), "Please enter timer first", Toast.LENGTH_SHORT).show()
            }else
            {
                store.collection("quiz").document("code").get().addOnSuccessListener {
                    val roomCode:String = it["i"].toString()
                    val map: HashMap<String, Any> = hashMapOf()
                    map["link"] = pastelink.text.toString()
                    map["time"] = time.text.toString()
                    store.collection("quiz").document(uid).collection(roomCode).document("gen").set(map).addOnSuccessListener {
                        Toast.makeText(requireContext(), "Room Generated", Toast.LENGTH_SHORT).show()
                        val m: HashMap<String, Any> = hashMapOf()
                        m["i"] = roomCode.toInt()+1
                        store.collection("quiz").document("code").set(m)
                    }
                }
            }
        }



    }
}

