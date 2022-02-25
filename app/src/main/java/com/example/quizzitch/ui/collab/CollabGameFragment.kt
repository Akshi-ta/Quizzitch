package com.example.quizzitch.ui.collab

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.quizzitch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CollabGameFragment : Fragment() {

    val mauth: FirebaseAuth = FirebaseAuth.getInstance()
    val uid: String = mauth.currentUser!!.uid
    val store: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collab_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val roomcode = requireArguments().getString("roomcode")
        val previousBt: Button = view.findViewById(R.id.previousButton)
        val nextBt: Button = view.findViewById(R.id.nextButton)

        var iterator = 0
        prot(view, iterator)
        nextBt.setOnClickListener{
            iterator++
            prot(view, iterator)
        }
        previousBt.setOnClickListener{
            iterator--
            prot(view, iterator)
        }


    }

    fun prot(view:View, iterator: Int) {
        val roomcode = requireArguments().getString("roomcode")
        val totalQ: Int = requireArguments().getString("ques")!!.toInt()
        if(iterator<0)
        {
            Toast.makeText(requireContext(), "Start reached", Toast.LENGTH_SHORT).show()
            return
        }
        if(totalQ<=iterator)
        {
            Toast.makeText(requireContext(), "all done", Toast.LENGTH_SHORT).show()
            return
        }
        val questionTV: TextView = view.findViewById(R.id.textView58)
        val options1: TextView = view.findViewById(R.id.options1)
        val options2: TextView = view.findViewById(R.id.options2)
        val options3: TextView = view.findViewById(R.id.options3)
        val options4: TextView = view.findViewById(R.id.options4)
        store.collection("games").document(uid).get()
            .addOnCompleteListener{
                val map: HashMap<String, Any> = it.result.data as HashMap<String, Any>
                //Toast.makeText(requireContext(), iterator.toString(), Toast.LENGTH_LONG).show()
                val questionsData: HashMap<String, Any> = (map[roomcode]as HashMap<String, Any>)["questions"] as HashMap<String, Any>
                questionTV.text = (iterator+1).toString() + ". " + (questionsData[iterator.toString()] as HashMap<String, Any>)["ques"].toString()
                val incorrect: ArrayList<String> = (questionsData[iterator.toString()] as HashMap<String, Any>)["incorrect_answers"]as ArrayList<String>
                options1.text =  incorrect[0]
                options2.text = incorrect[1]
                options3.text = incorrect[2]
                options4.text = (questionsData[iterator.toString()] as HashMap<String, Any>)["correct_answer"].toString()
            }
    }


}