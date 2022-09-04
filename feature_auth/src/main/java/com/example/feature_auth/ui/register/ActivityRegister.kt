package com.example.feature_auth.ui.register

import com.example.common_base.BaseActivity
import com.example.feature_auth.R
import kotlinx.android.synthetic.main.activity_register.toolbar

class ActivityRegister : BaseActivity() {
    override fun setLayoutId(): Int = R.layout.activity_register

    override fun initView() {
        setupEventView()
    }

    private fun setupEventView() {
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }

}