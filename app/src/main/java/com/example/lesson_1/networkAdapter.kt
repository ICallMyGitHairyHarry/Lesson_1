package com.example.lesson_1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_1.model.Album
import com.example.lesson_1.model.User

class networkAdapter: RecyclerView.Adapter<networkAdapter.ViewHolder>() {

    var data_list = listOf<Album>()
    var new_data_list = listOf<User>()
    var switch = 0


    fun setAlbumsData(data: List<Album>) {
        data_list = data
        notifyDataSetChanged()
        switch = 1
    }

    fun setUsersData(data: List<User>) {
        new_data_list = data
        notifyDataSetChanged()
        switch = 2
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.album, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount() = if (switch == 1) {data_list.size} else {new_data_list.size}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (switch == 1) {
            holder.bind(data_list[position])
        } else {
            holder.bind(new_data_list[position])
        }

    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val us_id = itemView.findViewById<TextView>(R.id.user_id)
        val jst_id = itemView.findViewById<TextView>(R.id.just_id)
        val tit = itemView.findViewById<TextView>(R.id.title)


        fun bind(alb : Album) {
            us_id.text = alb.userId.toString()
            jst_id.text = alb.id.toString()
            tit.text = alb.title
        }

        @SuppressLint("SetTextI18n")
        fun bind(user : User) {
            us_id.text = user.name + ", " + user.username
            jst_id.text = user.email
            tit.text = user.address.street + ", " + user.address.suite + ", " + user.address.city
        }
    }

}