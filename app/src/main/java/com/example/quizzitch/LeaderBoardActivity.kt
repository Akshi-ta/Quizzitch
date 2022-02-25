package com.example.quizzitch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LeaderBoardActivity : AppCompatActivity() {
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var adapter: CustomAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_board)

        userRecyclerView = findViewById(R.id.recycler_view)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
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
                            1,
                            data["displayName"].toString(),
                            data["score"].toString().toInt()
                        )
                        adapter.addUser(user)
                    } catch (nfe: NumberFormatException) {
                        Log.e("NFE Error", "Wrong format")
                    }
                    Log.d("avychanna", "${document.id} => ${document.data}")
                }
            }


    }
}



