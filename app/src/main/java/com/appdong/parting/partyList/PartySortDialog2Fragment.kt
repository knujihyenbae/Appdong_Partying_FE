package com.appdong.parting.partyList

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.appdong.parting.R
import com.appdong.parting.databinding.FragmentPartySortDialog1Binding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PartySortDialog2Fragment() : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_party_sort_dialog2, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.findViewById<TextView>(R.id.dialog2_close_btn)?.setOnClickListener {
            dismiss()
        }
    }
}

//class ProfileDialog(
//    context: Context,
//    private val okCallback: (String) -> Unit,
//) : Dialog(context) { // 뷰를 띄워야하므로 Dialog 클래스는 context를 인자로 받는다.
//
//    private lateinit var binding: FragmentPartySortDialog1Binding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // 만들어놓은 dialog_profile.xml 뷰를 띄운다.
//        binding = FragmentPartySortDialog1Binding.inflate(layoutInflater)
//        setContentView(binding.root)
//        initViews()
//    }
//
//    private fun initViews() = with(binding) {
//        // 뒤로가기 버튼, 빈 화면 터치를 통해 dialog가 사라지지 않도록
//        setCancelable(false)
//
//        // OK Button 클릭에 대한 Callback 처리. 이 부분은 상황에 따라 자유롭게!
//        buttonBottomSheet.setOnClickListener {
//            if (!radioGroup.isActivated()) {
//                Toast.makeText(context, "정렬을 선택하세요", Toast.LENGTH_SHORT).show()
//            } else {
//                okCallback(radioGroup.toString())
//                dismiss()
//            }
//        }
//    }
//
//    private fun showProfileDialog() {
//        ProfileDialog(requireContext()) {
//            viewModel.setName(it)
//        }.show()
//    }
//}