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
        holder.txtView1.text = currentItem.displayName
        holder.txtView2.text = currentItem.score.toString()
        if (currentItem.avatar == "hawkeye"){
            holder.imgViewName.setImageResource(R.drawable.hawkeye)
        }
        else if (currentItem.avatar == "blackpanther"){
            holder.imgViewName.setImageResource(R.drawable.blackpanther)
        }
        else if (currentItem.avatar == "hulk"){
            holder.imgViewName.setImageResource(R.drawable.hulk)
        }
        else if (currentItem.avatar == "blackwidow"){
            holder.imgViewName.setImageResource(R.drawable.blackwidow)
        }
        else if (currentItem.avatar == "thanos"){
            holder.imgViewName.setImageResource(R.drawable.thanos)
        }
        else if (currentItem.avatar == "thor"){
            holder.imgViewName.setImageResource(R.drawable.thor1)
        }
        else if (currentItem.avatar == "starlord"){
            holder.imgViewName.setImageResource(R.drawable.starlord)
        }
        else if (currentItem.avatar == "captainamerica"){
            holder.imgViewName.setImageResource(R.drawable.captainamerica)
        }
        else if (currentItem.avatar == "captainmarvel"){
            holder.imgViewName.setImageResource(R.drawable.marvel)
        }
        else if (currentItem.avatar == "spiderman"){
            holder.imgViewName.setImageResource(R.drawable.spiderman)
        }
        else if (currentItem.avatar == "scarletwitch"){
            holder.imgViewName.setImageResource(R.drawable.scarletwitch)
        }
        else if (currentItem.avatar == "drstrange"){
            holder.imgViewName.setImageResource(R.drawable.drstrange)
        }
        else if (currentItem.avatar == "bucky"){
            holder.imgViewName.setImageResource(R.drawable.bucky)
        }
        else if (currentItem.avatar == "deadpool"){
            holder.imgViewName.setImageResource(R.drawable.deadpool)
        }
        else if (currentItem.avatar == "falcon"){
            holder.imgViewName.setImageResource(R.drawable.falcon)
        }
        else{
            holder.imgViewName.setImageResource(R.drawable.profilenew)
        }

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
        val txtView3: TextView = itemView.findViewById((R.id.user_rank))
    }
}
