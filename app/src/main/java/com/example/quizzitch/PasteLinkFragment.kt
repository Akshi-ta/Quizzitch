package com.example.quizzitch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_paste_link.view.*
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
        generatebutton.setOnClickListener {
            store.collection("quiz").document(uid).get().addOnSuccessListener {
                val map: HashMap<String,Any> = hashMapOf()
                if (it["time"]==null){
                    map["time"] = 0
                }
                val time : EditText = view.findViewById(R.id.editTextTime)
                map["time"] = time.text.toString()
                store.collection("quiz").document(uid).update(map).addOnSuccessListener {
                }
            }

            store.collection("quizCount").document("a").get().addOnSuccessListener {}
//            val roomCode : String = (it.data as HashMap<String,Any>)["a"].toString()
        }



    }
}

