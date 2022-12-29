package com.appdong.parting.partyList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdong.parting.PartyOpenActivity
import com.appdong.parting.R
import com.appdong.parting.databinding.FragmentPartyListBinding

class PartyListFragment : Fragment() {
    private var _binding: FragmentPartyListBinding? = null
    private val binding get() = _binding!!
    val partyList= mutableListOf<PartyList>()
    val potListAdapter=PartyListAdapter(partyList)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPartyListBinding.inflate(inflater,container,false)
//        binding.petListRecyclerView.layoutManager=LinearLayoutManager(requireContext())
//        binding.petListRecyclerView.adapter= PetListAdapter(pets)

        partyList.add(PartyList("대구 중구 1km","2 / 5","R.drawable.cafe1","00하는 모임",
            "2022.12.26 PM 1시 ~ 3시","#카공","#공무원","#국가고시"))
        partyList.add(PartyList("대구 산격동 300m","1 / 5","R.drawable.study","00하는 모임",
            "2022.12.26 AM 10시 ~ 자유","#영어","#토익","#아이엘츠"))
        partyList.add(PartyList("대구 대현동 500m","2 / 5","R.drawable.cafe2","00하는 모임",
            "2022.12.26 PM 4시 ~ 10시","#카공","#자유","#스터디메이트"))


//        binding.petsitterImage.setImageResource(R.drawable.dog_service)
//        binding.myName.text=userProfile[position].userName
//        binding.myPhoneNum.editableText=userProfile[position].userPhoneNum
//        binding.myLocation.editableText=userProfile[position].userLoaction
//        binding.myGender.text=userProfile[position].userGender
//        binding.myAge.editableText=userProfile[position].userAge

        binding.partyRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.partyRecyclerView.adapter=potListAdapter
        potListAdapter.setItemClickListener(object : PartyListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
//                loadFragment()
            }

        })

        binding.fab.setOnClickListener {
            val intent = Intent(getActivity(), PartyOpenActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
    private fun loadFragment(fragment: Fragment){
        Log.d("clickTest","click!->"+fragment.tag)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}