package com.example.privytest.ui.fragment

import com.example.common_base.BaseFragment
import com.example.privytest.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentMessage : BaseFragment() {
    val viewModel: MessageViewModel by viewModel()

    override fun setLayoutId(): Int = R.layout.fragment_message

    override fun initView() {
        setupObserver()
    }

    fun setupObserver() {
        with(viewModel) {

        }
    }


}