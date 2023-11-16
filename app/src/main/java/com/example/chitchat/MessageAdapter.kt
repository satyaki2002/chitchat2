package com.example.chitchat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MessageAdapter(val context: Context,val messageList:ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ItemSend=2
    val ItemRecieve=1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == 1)
        {
            val view : View= LayoutInflater.from(context).inflate(R.layout.recieve,parent,false)
            return RecieveViewHolder(view)
        }
        else
        {
            val view : View= LayoutInflater.from(context).inflate(R.layout.sent,parent,false)
            return SendViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage=messageList[position]

        if(holder.javaClass==SendViewHolder::class.java)
        {
            val ViewHolder=holder as SendViewHolder
            holder.sendMessage.text=currentMessage.message
        }
        else
        {
            val ViewHolder=holder as RecieveViewHolder
            holder.recieveMessage.text=currentMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage=messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderid))
            return ItemSend
        else
            return ItemRecieve
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    class SendViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)
    {
        val sendMessage=itemView.findViewById<TextView>(R.id.txt_send_message)
    }

    class RecieveViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)
    {
        val recieveMessage=itemView.findViewById<TextView>(R.id.txt_recieve_message)
    }
}