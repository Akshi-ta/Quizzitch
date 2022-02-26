package com.example.quizzitch

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdaptor(userInp: ArrayList<User>) :
    RecyclerView.Adapter<CustomAdaptor.ViewHolder>() {
    private val TAG = "CustomAdaptor"
    private val userArr: ArrayList<User> = userInp

    override fun getItemCount(): Int {
        return userArr.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userArr[position]
        holder.imgViewName.setImageResource(R.drawable.blackpanther)
        holder.txtView1.text = currentItem.displayName
        holder.txtView2.text = currentItem.score.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imgView =
            LayoutInflater.from(parent.context).inflate(R.layout.board_layout, parent, false)
        return ViewHolder(imgView)
    }

    fun addUser(user: User) {
        for (i in 0 until userArr.size) {
            val v = userArr[i]
            if (v.score < user.score) {
                userArr.add(i, user)
                notifyItemInserted(i)
                return
            }
        }
        userArr.add(user)
        notifyItemInserted(userArr.size - 1)
        Log.d(TAG, "Adding user " + user.displayName)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgViewName: ImageView = itemView.findViewById(R.id.user_avatar)
        val txtView1: TextView = itemView.findViewById(R.id.username)
        val txtView2: TextView = itemView.findViewById(R.id.user_score)
    }
}
