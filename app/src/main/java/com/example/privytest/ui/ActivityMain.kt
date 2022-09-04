package com.example.privytest.ui

import android.content.Intent
import com.example.common_base.BaseActivity
import com.example.feature_auth.ui.login.ActivityLogin
import com.example.privytest.R

class ActivityMain : BaseActivity() {
    override fun setLayoutId(): Int = R.layout.activity_main
    override fun initView() {
        gotoLogin()
    }

    fun gotoLogin() {
        val i = Intent(this@ActivityMain, ActivityLogin::class.java)
        startActivity(i)
    }
}