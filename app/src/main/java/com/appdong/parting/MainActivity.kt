package com.appdong.parting

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.appdong.parting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //알림 클릭 리스너
        binding.toolbarAlarm.setOnClickListener(){
            Log.d("jhb", "알림")
        }
    
        //젤 첫화면 홈화면
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, HomeFragment()).commit()

        //바텀바 프래그먼트 전환
        binding.bottomBtn1.setOnClickListener() {
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, HomeFragment()).addToBackStack(null).commit()
        }
        binding.bottomBtn2.setOnClickListener() {
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, MapFragment()).addToBackStack(null).commit()
        }
        binding.bottomBtn3.setOnClickListener() {
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, ChatFragment()).addToBackStack(null).commit()
        }
        binding.bottomBtn4.setOnClickListener() {
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, MypageFragment()).addToBackStack(null).commit()
        }

    }
}