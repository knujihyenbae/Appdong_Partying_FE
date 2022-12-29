package com.appdong.parting

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import com.appdong.parting.databinding.FragmentPartyDetailBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailInformationFragment : Fragment(), PopupMenu.OnMenuItemClickListener {
    private var _binding: FragmentPartyDetailBinding? = null
    private val binding get() = _binding!!
    lateinit var detailInformationAdapter: DetailInformationAdapter
    private val partyData = mutableListOf<PartyProfileData>()
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPartyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.menuButton.setOnClickListener { showPopup(binding.menuButton) }
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    //    팝업 메뉴 보여주는 커스텀 메소드
    private fun showPopup(v: View) {
        val popup = PopupMenu(requireContext(), v) // PopupMenu 객체 선언
        popup.menuInflater.inflate(R.menu.menu_option, popup.menu) // 메뉴 레이아웃 inflate
        popup.setOnMenuItemClickListener(this) // 메뉴 아이템 클릭 리스너 달아주기
        popup.show() // 팝업 보여주기
    }
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) { // 메뉴 아이템에 따라 동작 다르게 하기
            R.id.menu_option_edit -> Toast.makeText(activity, "수정 클릭!", Toast.LENGTH_SHORT).show()
            R.id.menu_option_delete -> Toast.makeText(activity, "삭제 클릭!", Toast.LENGTH_SHORT).show()
            R.id.menu_option_share -> Toast.makeText(activity, "공유 클릭!", Toast.LENGTH_SHORT).show()
        }

        return item != null // 아이템이 null이 아닌 경우 true, null인 경우 false 리턴
    }

    private fun initRecycler() {
        detailInformationAdapter = DetailInformationAdapter(requireContext())


        partyData.apply {
            add(PartyProfileData(img = R.drawable.book_icon, name = "내이름0"))
            add(PartyProfileData(img = R.drawable.book_icon, name = "내이름1"))
            add(PartyProfileData(img = R.drawable.book_icon, name = "내이름2"))
            add(PartyProfileData(img = R.drawable.book_icon, name = "내이름3"))

            detailInformationAdapter.datas = partyData
            detailInformationAdapter.notifyDataSetChanged()

        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_option_edit -> {
                return super.onOptionsItemSelected(item)
            }
            R.id.menu_option_delete -> {
                return super.onOptionsItemSelected(item)
            }
            R.id.menu_option_share -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}