package com.appdong.parting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appdong.parting.chat.ProfileData

class ChatActivity: AppCompatActivity() {
    lateinit var datas : ProfileData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_chat)

//        datas = intent.getSerializableExtra("data") as ProfileData
//
//        Glide.with(this).load(datas.img).into(img_profile)
//        tv_name.text = datas.name

    }
}