package com.example.quizzitch.ui.collab

import android.content.ContentValues.TAG
import android.graphics.Color
import android.graphics.Color.blue
import android.os.Bundle
import android.util.Log
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
import androidx.fragment.app.FragmentTransaction
import com.example.quizzitch.R
import com.example.quizzitch.Result1Fragment
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
        val hostUid: String = requireArguments().getString("hostuid")!!
        //val previousBt: Button = view.findViewById(R.id.previousButton)
        val totalQ: Int = requireArguments().getString("ques")!!.toInt()
        val nextBt: Button = view.findViewById(R.id.nextButton)
        val options1: TextView = view.findViewById(R.id.options1)
        val options2: TextView = view.findViewById(R.id.options2)
        val options3: TextView = view.findViewById(R.id.options3)
        val options4: TextView = view.findViewById(R.id.options4)

        store.collection("games").document(hostUid).get().addOnSuccessListener {
            Log.e(TAG, roomcode.toString())
            if((it[roomcode!!]as HashMap<String, Any>)["responses"]!=null)
            {
                val res: HashMap<String, Any> = (it[roomcode!!]as HashMap<String, Any>)["responses"] as HashMap<String, Any>
                if(res[uid]!=null)
                {
//                    val fragment: Fragment = ResultFragment()
//                    val bundle = Bundle()
//                    bundle.putString("diff", requireArguments().getString("diff"))
//                    bundle.putString("ques", totalQ.toString())
//                    bundle.putString("category", requireArguments().getString("category"))
//                    bundle.putString("roomcode", requireArguments().getString("roomcode"))
//                    bundle.putString("hostuid", requireArguments().getString("hostuid"))
//                    fragment.arguments = bundle
//                    val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//                    transaction.replace(R.id.game, fragment)
//                    transaction.addToBackStack("result")
//                    transaction.commit()
                }
            }
        }

        var iterator = 0
        val answers: HashMap<String, Any> = hashMapOf()
        prot(view, iterator)
        nextBt.setOnClickListener{
            //answers[iterator.toString()] =  options.checkedRadioButtonId.toString()
//            options1.background = null
//            options2.background = null
//            options3.background = null
//            options4.background = null
            options1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            options2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            options3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            options4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
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
            store.collection("games").document(requireArguments().getString("hostuid")!!).get().addOnSuccessListener {
                val map: HashMap<String, Any> = it.data as HashMap<String, Any>
                val gameData: HashMap<String, Any> = map[roomcode] as HashMap<String, Any>
                var responses: HashMap<String, Any> = hashMapOf()
                if(gameData["responses"]!=null)
                {
                    responses = gameData["responses"] as HashMap<String, Any>
                }
                responses[uid] = answers
                gameData["responses"] = responses
                map[roomcode!!] = gameData
                store.collection("games").document(requireArguments().getString("hostuid")!!).update(map).addOnSuccessListener {
                    Toast.makeText(requireContext(), "response recorded", Toast.LENGTH_LONG).show()
//                    val fragment: Fragment = ResultFragment()
//                    val bundle = Bundle()
//                    bundle.putString("diff", requireArguments().getString("diff"))
//                    bundle.putString("ques", totalQ.toString())
//                    bundle.putString("category", requireArguments().getString("category"))
//                    bundle.putString("roomcode", requireArguments().getString("roomcode"))
//                    bundle.putString("hostuid", requireArguments().getString("hostuid"))
//                    fragment.arguments = bundle
//                    val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
//                    transaction.replace(R.id.game, fragment)
//                    transaction.addToBackStack("result")
//                    transaction.commit()
                }
            }
        }

        options1.setOnClickListener {
            //options1.background = ContextCompat.getDrawable(requireContext(), R.drawable.bluegradient)
            options1.setTextColor(Color.BLUE)
            options2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            options3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            options4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//            options2.background = null
//            options3.background = null
//            options4.background = null
            answers[iterator.toString()] = options1.text
        }
        options2.setOnClickListener{
//            options1.background = null
//            options2.background = ContextCompat.getDrawable(requireActivity(), R.drawable.bluegradient)
            options1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            options2.setTextColor(Color.BLUE)
            options3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            options4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
//            options3.background = null
//            options4.background = null
            answers[iterator.toString()] =  options2.text
        }
        options3.setOnClickListener{
//            options1.background = null
//            options2.background = null
//            options3.background = ContextCompat.getDrawable(requireActivity(), R.drawable.bluegradient)
            options1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            options2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            options3.setTextColor(Color.BLUE)
            options4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            //options4.background = null
            answers[iterator.toString()] =  options3.text
        }
        options4.setOnClickListener{
//            options1.background = null
//            options2.background = null
//            options3.background = null
//            options4.background = ContextCompat.getDrawable(requireActivity(), R.drawable.bluegradient)
            options1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            options2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            options3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            options4.setTextColor(Color.BLUE)
            answers[iterator.toString()] =  options4.text
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
        store.collection("games").document(requireArguments().getString("hostuid")!!).get()
            .addOnCompleteListener{
                val map: HashMap<String, Any> = it.result.data as HashMap<String, Any>

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