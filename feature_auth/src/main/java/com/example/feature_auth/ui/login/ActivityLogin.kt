package com.example.feature_auth.ui.login

import android.os.Bundle
import com.example.common_base.BaseActivity
import com.example.feature_auth.R

class ActivityLogin : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun setLayoutId(): Int = R.layout.activity_login

    override fun initView() {

    }
}