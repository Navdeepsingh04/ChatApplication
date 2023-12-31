package com.example.chatapplication

import android.app.Notification
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import java.security.MessageDigest

class messageAdapter(val context: Context,val messageList:ArrayList<message>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_SENT = 2
    val  ITEM_RECIEVE = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): RecyclerView.ViewHolder{


        if(viewType == 1){
            //inflate Recieve
            val view:View = LayoutInflater.from(context).inflate(R.layout.recieve,parent,false)
            return ReceiveViewHolder(view)
        }
        else{
            //inflate Sent
            val view:View = LayoutInflater.from(context).inflate(R.layout.send,parent,false)
            return SentViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        if(holder.javaClass == SentViewHolder::class.java){
            //Do stuff for SentViewHolder.

          val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message
        }
        else{
            //Do stuff for recieve view Holder.
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text = currentMessage.message
        }



    }

    override fun getItemViewType(position: Int): Int {

        val currentMessage = messageList[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId))
        {
            return ITEM_SENT
        }
        else{
            return ITEM_RECIEVE
        }
    }

    class SentViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val sentMessage = itemView.findViewById<TextView>(R.id.txt_message_send)

    }

    class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val receiveMessage = itemView.findViewById<TextView>(R.id.txt_message_receive)

    }

}