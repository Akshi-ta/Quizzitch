package com.example.quizzitch.ui.customQuiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentTransaction
import com.example.quizzitch.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class CreateCustomFragment : Fragment() {
    val mauth: FirebaseAuth = FirebaseAuth.getInstance()
    val uid: String = mauth.currentUser!!.uid
    val store: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_custom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val map: HashMap<String, Any> = hashMapOf()
        val total: TextView = view.findViewById(R.id.textView64)
        val generate: Button = view.findViewById(R.id.button11)
        val another: Button = view.findViewById(R.id.button12)
        val question: EditText = view.findViewById(R.id.editTextTextPersonName)
        val options1: EditText = view.findViewById(R.id.cusOption1)
        val options2: EditText = view.findViewById(R.id.cusOption2)
        val options3: EditText = view.findViewById(R.id.cusOption3)
        val options4: EditText = view.findViewById(R.id.cusOption4)
        val op1: RadioButton = view.findViewById(R.id.op1)
        val op2: RadioButton = view.findViewById(R.id.op2)
        val op3: RadioButton = view.findViewById(R.id.op3)
        val op4: RadioButton = view.findViewById(R.id.op4)
        val radioGroup: RadioGroup = view.findViewById(R.id.radioGroup)
        var i = 1

        another.setOnClickListener {
            if(question.text.toString().isEmpty())
            {
                Toast.makeText(requireContext(), "Write a questions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(options1.text.toString().isEmpty()||options2.text.toString().isEmpty()||options3.text.toString().isEmpty()||options4.text.toString().isEmpty())
            {
                Toast.makeText(requireContext(), "Enter all options", Toast.LENGTH_SHORT).show()
            }else if(op1.isChecked==false&&op2.isChecked==false&&op3.isChecked==false&&op4.isChecked==false)
            {
                Toast.makeText(requireContext(), "Tell the correct option first", Toast.LENGTH_SHORT).show()
            }else
            {
                val myQues: HashMap<String, Any> = hashMapOf()
                myQues["ques"] = question.text.toString()
                myQues["op1"] = options1.text.toString()
                myQues["op2"] = options2.text.toString()
                myQues["op3"] = options3.text.toString()
                myQues["op4"] = options4.text.toString()
                if(op1.isChecked)
                {
                    myQues["correct"] = options1.text.toString()
                }else if(op2.isChecked)
                {
                    myQues["correct"] = options2.text.toString()
                }else if(op3.isChecked)
                {
                    myQues["correct"] = options3.text.toString()
                }else if(op4.isChecked)
                {
                    myQues["correct"] = options4.text.toString()
                }
                map["$i"] = myQues

                question.text.clear()
                options1.text.clear()
                options2.text.clear()
                options3.text.clear()
                options4.text.clear()
                radioGroup.clearCheck()
                total.text = i.toString()
                i++
            }
        }

        generate.setOnClickListener {
            if(map.isEmpty())
            {
                Toast.makeText(requireContext(), "No questions Added", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else {
                store.collection("quiz").document("code").get().addOnSuccessListener {
                    val roomCode: String = it["i"].toString()
                    store.collection("quiz").document(uid).collection(roomCode).document("gen").set(map).addOnSuccessListener {
                        Toast.makeText(requireContext(), "Quiz formed",Toast.LENGTH_LONG).show()
                        val temp: HashMap<String,Any> = hashMapOf()
                        temp["i"] = roomCode.toInt()+1
                        store.collection("quiz").document("code").set(temp).addOnSuccessListener {
                            //Toast.makeText(requireContext(), "quiz updated", Toast.LENGTH_SHORT).show()
                        }
                        val card:CardView = view.findViewById(R.id.cardView10)
                        val roomCard: CardView = view.findViewById(R.id.roomcodeCard)
                        val roomTV: TextView = view.findViewById(R.id.textView61)
                        card.visibility = View.GONE
                        another.visibility = View.GONE
                        generate.visibility = View.GONE
                        roomCard.visibility = View.VISIBLE
                        roomTV.text = roomCode
                    }
                }

            }
        }
    }

}