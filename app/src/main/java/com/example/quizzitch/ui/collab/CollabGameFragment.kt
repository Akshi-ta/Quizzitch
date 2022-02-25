package com.example.quizzitch.ui.collab

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizzitch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.collection.LLRBNode
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
        //val previousBt: Button = view.findViewById(R.id.previousButton)
        val totalQ: Int = requireArguments().getString("ques")!!.toInt()
        val nextBt: Button = view.findViewById(R.id.nextButton)
        val options1: TextView = view.findViewById(R.id.options1)
        val options2: TextView = view.findViewById(R.id.options2)
        val options3: TextView = view.findViewById(R.id.options3)
        val options4: TextView = view.findViewById(R.id.options4)

        var iterator = 0
        val answers: HashMap<String, Any> = hashMapOf()
        prot(view, iterator)
        nextBt.setOnClickListener{
            //answers[iterator.toString()] =  options.checkedRadioButtonId.toString()
            options1.background = null
            options2.background = null
            options3.background = null
            options4.background = null
            iterator++
            prot(view, iterator)
            if(prot(view, iterator)==1)
                iterator= totalQ
        }
//        previousBt.setOnClickListener{
//            options1.background = null
//            options2.background = null
//            options3.background = null
//            options4.background = null
//            iterator--
//            if(prot(view, iterator)==2)
//                iterator=0
//        }

        val submit: Button = view.findViewById(R.id.button10)
        submit.setOnClickListener {
            Toast.makeText(requireContext(), answers.toString(), Toast.LENGTH_LONG).show()
        }

        options1.setOnClickListener {
            options1.background = ContextCompat.getDrawable(requireContext(), R.drawable.bluegradient)
            options1.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            options2.background = null
            options3.background = null
            options4.background = null
            answers[iterator.toString()] = 0
        }
        options2.setOnClickListener{
            options1.background = null
            options2.background = ContextCompat.getDrawable(requireActivity(), R.drawable.bluegradient)
            options2.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            options3.background = null
            options4.background = null
            answers[iterator.toString()] = 1
        }
        options3.setOnClickListener{
            options1.background = null
            options2.background = null
            options3.background = ContextCompat.getDrawable(requireActivity(), R.drawable.bluegradient)
            options3.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            options4.background = null
            answers[iterator.toString()] = 2
        }
        options4.setOnClickListener{
            options1.background = null
            options2.background = null
            options3.background = null
            options4.background = ContextCompat.getDrawable(requireActivity(), R.drawable.bluegradient)
            options4.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            answers[iterator.toString()] = 3
        }

    }

    fun prot(view:View, iterator: Int): Int {
        val roomcode = requireArguments().getString("roomcode")
        val totalQ: Int = requireArguments().getString("ques")!!.toInt()
        if(iterator<0)
        {
            Toast.makeText(requireContext(), "Start reached", Toast.LENGTH_SHORT).show()
            return 2
        }
        if(totalQ<=iterator)
        {
            Toast.makeText(requireContext(), "all done", Toast.LENGTH_SHORT).show()
            val submit: Button = view.findViewById(R.id.button10)
            submit.visibility = View.VISIBLE
            return 1
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
        return 0
    }


}