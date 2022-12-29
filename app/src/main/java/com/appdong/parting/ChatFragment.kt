package com.appdong.parting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appdong.parting.chat.ProfileAdapter
import com.appdong.parting.chat.ProfileData
import com.appdong.parting.databinding.FragmentChatListBinding
import com.appdong.parting.itemDecorator.HorizontalItemDecorator
import com.appdong.parting.itemDecorator.VerticalItemDecorator


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentChatListBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null
    lateinit var profileAdapter: ProfileAdapter
    val datas = mutableListOf<ProfileData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun initRecycler() {
        profileAdapter = ProfileAdapter(requireContext())
        binding.chatFragmentRecyclerview.adapter = profileAdapter
        binding.chatFragmentRecyclerview.addItemDecoration(VerticalItemDecorator(10))
        binding.chatFragmentRecyclerview.addItemDecoration(HorizontalItemDecorator(10))


        datas.apply {
            add(ProfileData(img = R.drawable.book_icon, name = "OO하는 모임", age = "어디"))
            add(ProfileData(img = R.drawable.book_icon, name = "OO하는 모임", age = "누구"))
            add(ProfileData(img = R.drawable.book_icon, name = "OO하는 모임", age = "어떻게"))
            add(ProfileData(img = R.drawable.book_icon, name = "OO하는 모임", age = "왜"))
            add(ProfileData(img = R.drawable.book_icon, name = "OO하는 모임", age = "무엇"))

            profileAdapter.datas = datas
            profileAdapter.notifyDataSetChanged()

        }
    }

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
        _binding = FragmentChatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
}