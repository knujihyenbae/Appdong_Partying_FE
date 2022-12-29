package com.appdong.parting.chat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appdong.parting.ChatActivity
import com.appdong.parting.R
import com.bumptech.glide.Glide

class ProfileAdapter(private val context: Context) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    var datas = mutableListOf<ProfileData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_chat,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }
    interface OnItemClickListener{
        fun onItemClick(v:View, data: ProfileData, pos : Int)
    }
    private var listener : OnItemClickListener? = null
    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtTitle: TextView = itemView.findViewById(R.id.chat_item_textview_title)
        private val txtMessage: TextView = itemView.findViewById(R.id.chat_item_textview_last_message)
        private val imgProfile: ImageView = itemView.findViewById(R.id.chat_item_imageview_profile)

        fun bind(item: ProfileData) {
            txtTitle.text = item.name
            txtMessage.text = item.age.toString()
            Glide.with(itemView).load(item.img).into(imgProfile)
            itemView.setOnClickListener {
                Intent(context, ChatActivity::class.java).apply {
                    putExtra("data", item)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }

        }
    }


}