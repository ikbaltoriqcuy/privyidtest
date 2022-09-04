package com.example.feature_auth.ui.register

import android.content.Intent
import com.example.common_base.BaseActivity
import com.example.feature_auth.R
import com.example.feature_auth.ui.otp.ActivityOTP
import kotlinx.android.synthetic.main.activity_register.*

class ActivityRegister : BaseActivity() {
    override fun setLayoutId(): Int = R.layout.activity_register

    override fun initView() {
        setupEventView()
    }

    private fun setupEventView() {
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        btnRegister.setOnClickListener {
            gotoOTP()
        }
    }

    private fun gotoOTP() {
        val i = Intent(this@ActivityRegister, ActivityOTP::class.java)
        startActivity(i)
    }

}