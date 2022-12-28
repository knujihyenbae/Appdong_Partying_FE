package com.appdong.parting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PartyItemAdapter(private val partyItemList:ArrayList<PartyItem>) :
    RecyclerView.Adapter<PartyItemAdapter.PartyItemViewHolder>() {


    // (1) 아이템 레이아웃과 결합
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartyItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_party, parent, false)
        return PartyItemViewHolder(view)
    }

    // (2) 리스트내 아이템 개수
    override fun getItemCount(): Int {
        return partyItemList.size
    }

    // (3) view에 내용 입력
    override fun onBindViewHolder(holder: PartyItemViewHolder, position: Int) {
        val partyItem = partyItemList[position]
        holder.imageView.setImageResource(partyItem.image)
        holder.textView.text = partyItem.name

        // 3-(1) 리스트 내 항목 클릭 시 onClick() 호출
        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)
        }
    }

    // 3-(2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // 3-(3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // 3-(4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener: OnItemClickListener
    //작동방식 itemClickListener(어뎁터) <-> OnItemClickListener <-> setItemClickListener(액티비티)



    // (4) 레이아웃 내 View 연결
    class PartyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
        val textView : TextView = itemView.findViewById(R.id.textView)
    }

}