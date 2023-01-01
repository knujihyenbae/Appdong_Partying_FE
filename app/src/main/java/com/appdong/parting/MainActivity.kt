package com.appdong.parting

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.appdong.parting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)//타이틀 변경
        toolbar.title = "팟팅"
        //알림 클릭 리스너
//        binding.toolbarAlarm.setOnClickListener(){
//            Log.d("jhb", "알림")
//        }
    
        //젤 첫화면 홈화면
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, HomeFragment()).commit()

        //바텀바 프래그먼트 전환
        binding.bottomBtn1.setOnClickListener() {
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, HomeFragment()).addToBackStack(null).commit()
            toolbar.title = "팟팅"
        }
        binding.bottomBtn2.setOnClickListener() {
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, MapFragment()).addToBackStack(null).commit()
            toolbar.title = "지도로 보기"
        }
        binding.bottomBtn3.setOnClickListener() {
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, ChatFragment()).addToBackStack(null).commit()
            toolbar.title = "내가 참여한 파티챗"
        }
        binding.bottomBtn4.setOnClickListener() {
            //supportFragmentManager.beginTransaction().replace(R.id.frame_layout, MypageFragment()).addToBackStack(null).commit()
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, DetailInformationFragment()).addToBackStack(null).commit()
            toolbar.title = "마이페이지"
        }
    }
    //액션버튼 메뉴 액션바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }
    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.menu_toolbar_alarm -> {
                //알람 버튼 눌렀을 때
                Toast.makeText(applicationContext, "알람 이벤트 실행", Toast.LENGTH_LONG).show()
                return super.onOptionsItemSelected(item)
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}