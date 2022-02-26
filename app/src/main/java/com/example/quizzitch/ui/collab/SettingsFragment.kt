package com.example.quizzitch.ui.collab

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentTransaction
import com.example.quizzitch.R
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.io.Console
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class SettingsFragment : Fragment() {
    val mauth: FirebaseAuth = FirebaseAuth.getInstance()
    val uid: String = mauth.currentUser!!.uid
    val store: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val level: RadioGroup = view.findViewById(R.id.level)
        val questionNo: RadioGroup = view.findViewById(R.id.questionRadioGroup)
        //val levelSelected: RadioButton = view.findViewById(level.checkedRadioButtonId)
        val startGameSettings: TextView = view.findViewById(R.id.startGameSettings)
        startGameSettings.setOnClickListener{
            //Toast.makeText(requireContext(), level.checkedRadioButtonId.toString(), Toast.LENGTH_LONG).show()
            if(level.checkedRadioButtonId==-1)
            {
                Toast.makeText(requireContext(), "Select difficulty level first", Toast.LENGTH_LONG).show()
            }else if(questionNo.checkedRadioButtonId==-1)
            {
                Toast.makeText(requireContext(), "Select no of questions first", Toast.LENGTH_LONG).show()
            }else {
                var diff: String = "";var ques = ""
                when(level.checkedRadioButtonId) {
                    1->{diff = "easy"}
                    2->{diff =  "medium"}
                    3->{diff =  "hard"}
                    else->{}
                }
                when(level.checkedRadioButtonId) {
                    1->{ques = "10"}
                    2->{ques =  "15"}
                    3->{ques =  "20"}
                    else->{}
                }

                //Toast.makeText(requireContext(), ques, Toast.LENGTH_LONG).show()
                store.collection("games").document(uid).get().addOnSuccessListener {
                    val link: String = "https://opentdb.com/api.php?amount=" + ques + "&category=" + requireArguments().getString("category") + "&difficulty=" + diff + "&type=multiple"
                    val url = URL(link)
                    val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                    connection.setRequestProperty("accept", "application/json");
                    val int = Build.VERSION.SDK_INT
                    if (int > 8) {
                        val policy = StrictMode.ThreadPolicy.Builder()
                            .permitAll().build()
                        StrictMode.setThreadPolicy(policy)
                    }
                    val stream: InputStream = connection.inputStream
                    val mapper = ObjectMapper()
                    val node: JsonNode = mapper.readTree(stream)
                    val result: JsonNode = node.path("results")
                    val questions: HashMap<String, Any> = hashMapOf()
                    for(i in 0 until ques.toInt())
                    {
                        val temp: JsonNode = result[i]
                        val oneQues: HashMap<String, Any> = hashMapOf()
                        oneQues["ques"] = temp["question"].toString()
                        oneQues["correct_answer"] = temp["correct_answer"].toString()
                        val arr: ArrayList<String> = arrayListOf()
                        for(j in 0..2)
                        {
                            Log.e(TAG, j.toString())
                            arr.add(temp["incorrect_answers"][j].toString())
                        }
                        oneQues["incorrect_answers"] = arr
                        questions[i.toString()] = oneQues
                    }
                    questions["totalQ"] = ques
                    questions["diff"] = diff
                    questions["category"] = requireArguments().getString("category")!!
                    val map: HashMap<String, Any> = it.data as HashMap<String, Any>
                    val t: HashMap<String, Any> = map[requireArguments().getString("roomcode")] as HashMap<String, Any>
                    t["questions"] = questions
                    t["started"] = "1"
                    map[requireArguments().getString("roomcode")!!] = t
                    store.collection("games").document(uid).update(map)

                    val cons: ConstraintLayout = view.findViewById(R.id.spareSetting)
                    cons.visibility = View.GONE
                    val fragment: Fragment = CollabGameFragment()
                    val bundle = Bundle()
                    bundle.putString("diff", diff)
                    bundle.putString("ques", ques)
                    bundle.putString("category", requireArguments().getString("category"))
                    bundle.putString("roomcode", requireArguments().getString("roomcode"))
                    bundle.putString("hostuid", requireArguments().getString("hostuid"))
                    fragment.arguments = bundle
                    val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.settings, fragment)
                    transaction.addToBackStack("game starts now")
                    transaction.commit()
                }

            }


        }
    }
}