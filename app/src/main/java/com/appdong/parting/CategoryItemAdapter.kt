package com.appdong.parting

import android.app.LauncherActivity.ListItem
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CategoryItemAdapter(private val categoryItemList: ArrayList<CategoryItem>) :
        RecyclerView.Adapter<CategoryItemAdapter.CategoryItemViewHolder>(){

    // (1) 아이템 레이아웃과 결합
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryItemViewHolder(view)
    }

    // (2) 리스트내 아이템 개수
    override fun getItemCount(): Int {
        return categoryItemList.size
    }

    // (3) view에 내용 입력
    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val categoryItem = categoryItemList[position]
        holder.textView.text = categoryItem.name


        val context = holder.itemCategoryConstraintLayout.context

        // 3-(1) 리스트 내 항목 클릭 시 onClick() 호출
        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it, position)

            holder.itemCategoryConstraintLayout.backgroundTintList = ContextCompat.getColorStateList(context, R.color.partyingColor)

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
    class CategoryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView : TextView = itemView.findViewById(R.id.textView_category)
        val itemCategoryConstraintLayout : ConstraintLayout = itemView.findViewById(R.id.item_category_constraintlayout)
    }



}