package com.example.feature_auth.ui.login

import android.content.Intent
import com.example.common_base.BaseActivity
import com.example.feature_auth.R
import com.example.feature_auth.ui.register.ActivityRegister
import kotlinx.android.synthetic.main.activity_login.btnRegister
import kotlinx.android.synthetic.main.activity_login.btnLogin

class ActivityLogin : BaseActivity() {

    override fun setLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        setEventView()
    }

    private fun setEventView() {
        btnLogin.setOnClickListener {
        }
        btnRegister.setOnClickListener {
            gotoRegister()
        }
    }

    private fun gotoRegister() {
        val i = Intent(this@ActivityLogin, ActivityRegister::class.java)
        startActivity(i)
    }
}