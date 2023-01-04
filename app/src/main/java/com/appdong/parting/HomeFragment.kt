package com.appdong.parting

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appdong.parting.databinding.FragmentHomeBinding
import com.appdong.parting.partyList.PartyListFragment


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var partyItemList: ArrayList<PartyItem>
    private lateinit var partyItemAdapter: PartyItemAdapter





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 검색바 관련
        search()

        // 모임 테마 리싸이클러
        init()

        // 홈캘린터 클릭
        binding.homeCalender.setOnClickListener() {
            var intent = Intent(context, PartyOpenActivity::class.java)
            startActivity(intent)
        }
        
        //디데이 클릭
        binding.homeDdayConstraint.setOnClickListener(){
            Log.d("jhb", "홈프래그먼트 디데이 모임")
        }

        // 배너 클릭
        homeBanner()

    }





    private fun init() {
        recyclerView = binding.fragmentHomeRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity, 4)

        partyItemList = ArrayList()
        addDataToList()

        partyItemAdapter = PartyItemAdapter(partyItemList)
        recyclerView.adapter = partyItemAdapter

        // 카테고리 아이템 어뎁터 / setItemClickListner 호출 하고 / OnItemClickListener를 익명 개체로 넘겨준다.
        partyItemAdapter.setItemClickListener(object: PartyItemAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {


                //전체클릭 시 이벤트 작성
                Log.d("jhb", "${partyItemList[position].name}")

                //각각 클릭 시 이벤트 작성
                when(partyItemList[position]){
                    partyItemList[0] -> {
                        Log.d("jhb", "${partyItemList[0].name}")
                        loadFragment(PartyListFragment())}
                    partyItemList[1] -> {
                        Log.d("jhb", "${partyItemList[1].name}")}
                    partyItemList[2] -> {
                        Log.d("jhb", "${partyItemList[2].name}")}
                    partyItemList[3] -> {
                        Log.d("jhb", "${partyItemList[3].name}")}
                    partyItemList[4] -> {
                        Log.d("jhb", "${partyItemList[4].name}")}
                    partyItemList[5] -> {
                        Log.d("jhb", "${partyItemList[5].name}")}
                    partyItemList[6] -> {
                        Log.d("jhb", "${partyItemList[6].name}")}
                    partyItemList[7] -> {
                        Log.d("jhb", "${partyItemList[7].name}")}

                    else -> {}
                }
            }

        })
    }

    private fun addDataToList() {
        partyItemList.add(PartyItem(R.drawable.party_culture, "문화생활팟"))
        partyItemList.add(PartyItem(R.drawable.party_watching, "관람팟"))
        partyItemList.add(PartyItem(R.drawable.party_develop, "자기개발팟"))
        partyItemList.add(PartyItem(R.drawable.party_eat, "한입팟"))
        partyItemList.add(PartyItem(R.drawable.party_health, "운동팟"))
        partyItemList.add(PartyItem(R.drawable.party_game, "오락팟"))
        partyItemList.add(PartyItem(R.drawable.party_cafe, "카페팟"))
        partyItemList.add(PartyItem(R.drawable.party_drunk, "한잔팟"))

    }


    private fun homeBanner() {
        binding.fragmentHomeBanner1.setOnClickListener(){
            Log.d("jhb", "홈배너1")
        }
        binding.fragmentHomeBanner2.setOnClickListener(){
            Log.d("jhb", "홈배너2")
        }
        binding.fragmentHomeBanner3.setOnClickListener(){
            Log.d("jhb", "홈배너3")
        }
        binding.fragmentHomeBanner4.setOnClickListener(){
            Log.d("jhb", "홈배너4")
        }
    }

    private fun loadFragment(fragment: Fragment){
        Log.d("clickTest","click!->"+fragment.tag)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun search(){
        var inputSearch: String? = null

        binding.fragmentHomeSearchEditText.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                // 다운 이나 엔터 눌렀을 때 실행
                //fragment 키보드 내리기
                val mInputMethodManager =
                    requireContext().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                mInputMethodManager.hideSoftInputFromWindow(
                    binding.fragmentHomeSearchEditText.windowToken,
                    0
                )

                //edittext에서 입력값 받아오기
                inputSearch = binding.fragmentHomeSearchEditText.text.toString()
                //입력값 저장된거 토스트로 띄워서 확인
                Toast.makeText(context, inputSearch, Toast.LENGTH_SHORT).show()

                //edittext 초기화 시켜주기
                binding.fragmentHomeSearchEditText.text = null


                true
            }else{
                false
            }
        }
    }
}