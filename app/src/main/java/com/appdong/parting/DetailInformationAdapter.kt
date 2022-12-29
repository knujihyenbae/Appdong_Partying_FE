package com.appdong.parting

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appdong.parting.chat.ProfileData
import com.bumptech.glide.Glide

class DetailInformationAdapter(private val context: Context) : RecyclerView.Adapter<DetailInformationAdapter.ViewHolder>(){

    var datas = mutableListOf<PartyProfileData>()
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
        private val partyProfileImg: ImageView = itemView.findViewById(R.id.item_party_people_imageview_profile)
        private val partyProfileName: TextView = itemView.findViewById(R.id.itemPartyPeople_textview_name)
        fun bind(item: PartyProfileData) {
            partyProfileName.text = item.name
            Glide.with(itemView).load(item.img).into(partyProfileImg)
            itemView.setOnClickListener {
                Intent(context, ChatActivity::class.java).apply {
                    putExtra("data", item)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }

        }
    }
}