package com.appdong.parting

import android.app.Activity
import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.appdong.parting.databinding.ActivityPartyOpenBinding
import com.bumptech.glide.Glide.init
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import com.google.android.material.internal.ViewUtils.hideKeyboard
import java.time.Clock
import java.util.*
import kotlin.collections.ArrayList


var category = 0

class PartyOpenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPartyOpenBinding
    private lateinit var recyclerView: RecyclerView

    private lateinit var partyItemList: ArrayList<PartyItem>
    private lateinit var partyItemAdapter: PartyItemAdapter

    private lateinit var categoryItemList: ArrayList<CategoryItem>
    private lateinit var categoryItemAdapter: CategoryItemAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPartyOpenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 좌측 최상단 백버튼
        binding.activityPartyBackBtn.setOnClickListener(){
            onBackPressed()
            Log.d("jhb","파티 개설 뒤로가기")
        }

        // 모임 테마 설정 리사이클러뷰 item 선택 init1으로 가세요~
        // 세부 카테고리 리사이클러뷰 item 선택 init2로 가세요!


        // partyTitleEditText 글자 컨트롤러
        PartyTitleEditTextController()
        // partyHashtagEditText 글자 컨트롤러
        PartyHashtagEditTextController()
        // partyIntroduceEditText 글자 컨트롤러
        PartyIntroduceEditTextController()

        // 파티일시 년,월,일
        binding.partyYearMonthDay.setOnClickListener(){
            Log.d("jhb", "년월일")
            
            val cal = Calendar.getInstance()
            // 사용자가 OK버튼을 누르면 바인딩된 text가 바뀜
            val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                binding.partyYearMonthDay.text = "${year}년. ${month+1}월. ${day}일"
            }
            // 다이얼로그 생성, 다이얼로그를 띄울때 오늘 날짜를 기본 데이터로 잡아준다.
            //DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
            //스피너 모드로 바꿔주기 deprecated 됐기 때문에 themes 에서 추가해줘야함
            val dateDialog = DatePickerDialog(this, dateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
            dateDialog.show()
            dateDialog.datePicker.spinnersShown = true

        }
        
        // 파티일시 시간
        binding.partyTime.setOnClickListener(){
            Log.d("jhb", "시간")

            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                binding.partyTime.text = "${hourOfDay}시 ${minute}분"
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

        }
        
        // 지도에서 위치 설정 클릭 리스너
        binding.goToMap.setOnClickListener(){
            Log.d("jhb", "지도에서 위치 설정")
        }

        // 모임 테마 설정 리싸이클러
        init1()

        // 세부 카테고리 리싸이클러
        init2()

        // 파티 등록 완료
        binding.activityPartyOpenRegistrationComplete.setOnClickListener(){
            Log.d("jhb", "파티 등록 완료")
        }


    }




    private fun init1() {
        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(this, 4)

        partyItemList = ArrayList()
        addDataToList()

        partyItemAdapter = PartyItemAdapter(partyItemList)
        recyclerView.adapter = partyItemAdapter


        // 카테고리 아이템 어뎁터 / setItemClickListner 호출 하고 / OnItemClickListener를 익명 개체로 넘겨준다.
        partyItemAdapter.setItemClickListener(object: PartyItemAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {

                //클릭 시 이벤트 작성
                Log.d("jhb", "${partyItemList[position].name}")

                when(partyItemList[position]){
                    partyItemList[0] -> {
                        Log.d("jhb", "${partyItemList[0].name}")
                        //1번 모임 테마 눌렀을 때 1번에 해당하는 세부 카테고리가 가 나와야한다.
                        category = 0
                    }
                    partyItemList[1] -> {
                        Log.d("jhb", "${partyItemList[1].name}")
                        category = 1
                    }
                    partyItemList[2] -> {
                        Log.d("jhb", "${partyItemList[2].name}")
                        category = 2
                    }
                    partyItemList[3] -> {
                        Log.d("jhb", "${partyItemList[3].name}")
                        category = 3
                    }
                    partyItemList[4] -> {
                        Log.d("jhb", "${partyItemList[4].name}")
                        category = 4
                    }
                    partyItemList[5] -> {
                        Log.d("jhb", "${partyItemList[5].name}")
                        category = 5
                    }
                    partyItemList[6] -> {
                        Log.d("jhb", "${partyItemList[6].name}")
                        category = 6
                    }
                    partyItemList[7] -> {
                        Log.d("jhb", "${partyItemList[7].name}")
                        category = 7
                    }

                    else -> {}
                }
            }

        })

    }


    private fun addDataToList() {
        partyItemList.add(PartyItem(R.drawable.party_culture, "문화생활팟", R.color.party_1))
        partyItemList.add(PartyItem(R.drawable.party_watching, "관람팟", R.color.party_2))
        partyItemList.add(PartyItem(R.drawable.party_develop, "자기개발팟", R.color.party_3))
        partyItemList.add(PartyItem(R.drawable.party_eat, "한입팟", R.color.party_4))
        partyItemList.add(PartyItem(R.drawable.party_health, "운동팟", R.color.party_5))
        partyItemList.add(PartyItem(R.drawable.party_game, "오락팟", R.color.party_6))
        partyItemList.add(PartyItem(R.drawable.party_cafe, "카페팟", R.color.party_7))
        partyItemList.add(PartyItem(R.drawable.party_drunk, "한잔팟", R.color.party_8))
    }


    private fun init2() {
        recyclerView = binding.recyclerViewCategory
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        categoryItemList = ArrayList()
        addDataToCategoryList()

        categoryItemAdapter = CategoryItemAdapter(categoryItemList)
        recyclerView.adapter = categoryItemAdapter

        // 카테고리 아이템 어뎁터 / setItemClickListner 호출 하고 / OnItemClickListener를 익명 개체로 넘겨준다.
        categoryItemAdapter.setItemClickListener(object: CategoryItemAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {


                //클릭 시 이벤트 작성
                Log.d("jhb", "${categoryItemList[position].name}")

                when(categoryItemList[position]){
                    categoryItemList[0] -> {
                        Log.d("jhb", "${categoryItemList[0].name}")
                        
                    }
                    categoryItemList[1] -> {Log.d("jhb", "${categoryItemList[1].name}")}
                    categoryItemList[2] -> {Log.d("jhb", "${categoryItemList[2].name}")}
                    categoryItemList[3] -> {Log.d("jhb", "${categoryItemList[3].name}")}
                    categoryItemList[4] -> {Log.d("jhb", "${categoryItemList[4].name}")}
                    categoryItemList[5] -> {Log.d("jhb", "${categoryItemList[5].name}")}
                    categoryItemList[6] -> {Log.d("jhb", "${categoryItemList[6].name}")}
                    categoryItemList[7] -> {Log.d("jhb", "${categoryItemList[7].name}")}


                    else -> {}
                }
            }

        })
    }

    private fun addDataToCategoryList() {
        //액티비티를 종료했다가 다시들어가면 바뀜
        when(category) {
            0 -> {
                categoryItemList.add(CategoryItem("문화생활1"))
                categoryItemList.add(CategoryItem("문화생활2"))
                categoryItemList.add(CategoryItem("문화생활3"))
                categoryItemList.add(CategoryItem("문화생활4"))
                categoryItemList.add(CategoryItem("문화생활5"))
                categoryItemList.add(CategoryItem("문화생활6"))
            }
            1 -> {
                categoryItemList.add(CategoryItem("영화"))
                categoryItemList.add(CategoryItem("뮤지컬"))
                categoryItemList.add(CategoryItem("콘서트"))
                categoryItemList.add(CategoryItem("박람회"))
                categoryItemList.add(CategoryItem("연극"))
                categoryItemList.add(CategoryItem("클래식/무용"))
                categoryItemList.add(CategoryItem("전시/행사"))
            }
            2 -> {
                categoryItemList.add(CategoryItem("코딩"))
                categoryItemList.add(CategoryItem("프로그래밍"))
                categoryItemList.add(CategoryItem("과제"))
                categoryItemList.add(CategoryItem("모각코"))
                categoryItemList.add(CategoryItem("전공공부"))
                categoryItemList.add(CategoryItem("교양공부"))
            }
            3 -> {
                categoryItemList.add(CategoryItem("한식"))
                categoryItemList.add(CategoryItem("중식"))
                categoryItemList.add(CategoryItem("양식"))
                categoryItemList.add(CategoryItem("일식"))
                categoryItemList.add(CategoryItem("패스트푸드"))
                categoryItemList.add(CategoryItem("치킨/피자"))
            }
            4 -> {
                categoryItemList.add(CategoryItem("헬스장"))
                categoryItemList.add(CategoryItem("등산"))
                categoryItemList.add(CategoryItem("요가"))
                categoryItemList.add(CategoryItem("스트레칭"))
                categoryItemList.add(CategoryItem("달리기"))
                categoryItemList.add(CategoryItem("수영"))
            }
            5 -> {
                categoryItemList.add(CategoryItem("PC방"))
                categoryItemList.add(CategoryItem("보드게임"))
                categoryItemList.add(CategoryItem("콘솔게임"))
                categoryItemList.add(CategoryItem("오락실"))
                categoryItemList.add(CategoryItem("노래방"))
                categoryItemList.add(CategoryItem("볼링장"))
            }
            6 -> {
                categoryItemList.add(CategoryItem("룸카페"))
                categoryItemList.add(CategoryItem("드로잉 카페"))
                categoryItemList.add(CategoryItem("동물 카페"))
                categoryItemList.add(CategoryItem("스터디 카페"))
                categoryItemList.add(CategoryItem("만화 카페"))
                categoryItemList.add(CategoryItem("디저트 카페"))
            }
            7 -> {
                categoryItemList.add(CategoryItem("소주"))
                categoryItemList.add(CategoryItem("맥주"))
                categoryItemList.add(CategoryItem("양주"))
                categoryItemList.add(CategoryItem("헌팅포차"))
                categoryItemList.add(CategoryItem("감성주점"))
                categoryItemList.add(CategoryItem("편맥"))
            }
            else -> {}
        }


    }

    private fun PartyTitleEditTextController(){
        binding.activityPartyOpenPartyTitleEditText.addTextChangedListener(object : TextWatcher{
            var maxText = ""
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                maxText = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val partyTitleEditText = binding.activityPartyOpenPartyTitleEditText
                val partyTitleEditTextCounter = binding.partyTitleEditTextCounter

                if(partyTitleEditText.lineCount > 1){
                    Toast.makeText(this@PartyOpenActivity, "한줄만 입력해", Toast.LENGTH_SHORT).show()

                    partyTitleEditText.setText(maxText)
                    partyTitleEditText.setSelection(partyTitleEditText.length())
                    partyTitleEditTextCounter.setText("${partyTitleEditText.length()}/20")
                }else if(partyTitleEditText.length() > 20){
                    Toast.makeText(this@PartyOpenActivity, "파티제목 20자 최대!", Toast.LENGTH_SHORT).show()
                    partyTitleEditText.setText(maxText)
                    partyTitleEditText.setSelection(partyTitleEditText.length())
                    partyTitleEditTextCounter.setText("${partyTitleEditText.length()}/20")
                } else{
                    partyTitleEditTextCounter.setText("${partyTitleEditText.length()}/20")
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun PartyHashtagEditTextController(){
        binding.partyHashtagEditText.addTextChangedListener(object : TextWatcher{
            var maxText = ""
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                maxText = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val partyHashtagEditText = binding.partyHashtagEditText
                val partyHashtagEditTextCounter = binding.partyHashtagEditTextCounter

                if(partyHashtagEditText.lineCount > 1){
                    Toast.makeText(this@PartyOpenActivity, "한줄만 입력해", Toast.LENGTH_SHORT).show()

                    partyHashtagEditText.setText(maxText)
                    partyHashtagEditText.setSelection(partyHashtagEditText.length())
                    partyHashtagEditTextCounter.setText("${partyHashtagEditText.length()}/20")
                }else if(partyHashtagEditText.length() > 20){
                    Toast.makeText(this@PartyOpenActivity, "해시태그 20자 최대!", Toast.LENGTH_SHORT).show()
                    partyHashtagEditText.setText(maxText)
                    partyHashtagEditText.setSelection(partyHashtagEditText.length())
                    partyHashtagEditTextCounter.setText("${partyHashtagEditText.length()}/20")
                } else{
                    partyHashtagEditTextCounter.setText("${partyHashtagEditText.length()}/20")
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    private fun PartyIntroduceEditTextController(){
        with(binding) {
            partyIntroduceEditText.addTextChangedListener(object : TextWatcher {
                //글자수, 문장수 제한을 넘지 않으면 입력 받은 문자열을 그대로 출력하기 위해
                //입력받은 문자열이 담길 maxText 변수를 만들어준다.
                var maxText = ""
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    //텍스트가 대체되기 전에 대체됨을 알리기 위해 호출되는 함수
                    maxText = s.toString()
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(partyIntroduceEditText.length() > 200){
                        Toast.makeText(this@PartyOpenActivity, "파티소개 200자 최대!", Toast.LENGTH_SHORT).show()
                        partyIntroduceEditText.setText(maxText)
                        partyIntroduceEditText.setSelection(partyIntroduceEditText.length())
                        partyIntroduceEditTextCounter.setText("${partyIntroduceEditText.length()}/200")
                    } else{
                        partyIntroduceEditTextCounter.setText("${partyIntroduceEditText.length()}/200")
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }

            })
        }
    }

}
