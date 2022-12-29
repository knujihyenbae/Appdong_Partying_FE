package com.appdong.parting.partyList

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appdong.parting.R
import com.appdong.parting.databinding.PartyListRecyclerviewBinding


class PartyListAdapter(val partyList:MutableList<PartyList>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class MyViewHolder(val binding: PartyListRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PartyListAdapter.MyViewHolder(
            PartyListRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("LOGTest",""+itemCount+"---"+position)
        val binding = (holder as MyViewHolder).binding

        binding.loaction.text=partyList[position].loaction
        binding.participatePeople.text=partyList[position].participateNum
//        binding.potLocationImg.setImageResource(R.drawable.cafe1)
        binding.partyLocationImg.setImageResource(R.drawable.study)
//        binding.potLocationImg.setImageResource(R.drawable.study)
//        binding.petsitterImg.setImageResource(R.drawable.example1)
        binding.partyName.text=partyList[position].partyName
        binding.partyTime.text=partyList[position].partyTime
        binding.partyTag1.text=partyList[position].partyTag1
        binding.partyTag2.text=partyList[position].partyTag2
        binding.partyTag3.text=partyList[position].partyTag3

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    private lateinit var itemClickListener : OnItemClickListener

    override fun getItemCount(): Int {
        return partyList.size
    }
}