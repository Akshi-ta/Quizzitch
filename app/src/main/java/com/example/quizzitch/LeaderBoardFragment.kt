package com.example.quizzitch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LeaderBoardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_leader_board, container, false)
    }

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var adapter: CustomAdaptor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRecyclerView = view.findViewById(R.id.recycler_view)
        userRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        userRecyclerView.setHasFixedSize(true)
        userArrayList = arrayListOf()
        adapter = CustomAdaptor(userArrayList)
        userRecyclerView.adapter = adapter
        getuserdata()
    }


    private fun getuserdata() {
        Firebase.firestore.collection("desc")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val data = document.data
                    try {
                        val user = User(
                            data["avatar"].toString(),
                            data["displayName"].toString(),
                            data["score"].toString().toInt()
                        )
                        adapter.addUser(user)
                    } catch (nfe: NumberFormatException) {
                        Log.e("NFE Error", "Wrong format")
                    }
                    Log.d("leaderboard", "${document.id} => ${document.data}")
                }
            }


    }
}