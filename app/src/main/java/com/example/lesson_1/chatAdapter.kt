package com.example.lesson_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class chatAdapter: RecyclerView.Adapter<chatAdapter.ViewHolder>() {

    var data_list = listOf<Message>()

    fun setData(data: List<Message>) {
        data_list = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = if (viewType == 1) {
            LayoutInflater.from(parent.context).inflate(R.layout.user_message, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.user_message2, parent, false)
        }
        return  ViewHolder(view)
    }

    override fun getItemCount() = data_list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data_list[position].text)
    }

    override fun getItemViewType(position: Int): Int = data_list[position].person


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mess = itemView.findViewById<TextView>(R.id.mess)
        fun bind(chat_mess: String) {
            mess.text = chat_mess
        }
    }
}