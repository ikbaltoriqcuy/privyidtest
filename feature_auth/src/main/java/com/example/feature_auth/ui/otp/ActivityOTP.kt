package com.example.feature_auth.ui.otp

import android.content.Intent
import android.graphics.Color
import com.example.common_base.BaseActivity
import com.example.feature_auth.R
import com.example.util.Utils.onTextChange
import kotlinx.android.synthetic.main.activity_otp.*

class ActivityOTP : BaseActivity() {

    var currentPosOTP = 0
    val listOTP by lazy {
        listOf(edOTPOne, edOTPTwo, edOTPThree, edOTPFour)
    }

    override fun setLayoutId(): Int = R.layout.activity_otp

    override fun initView() {
        setEventView()
    }

    private fun setEventView() {
        listOTP[currentPosOTP].requestFocus()
        for ((index,item) in listOTP.withIndex()) {
            if (index != currentPosOTP) item.isEnabled = false
            item.onTextChange {
                otpHandle(it.length, index)
            }
        }
        btnVerification.setOnClickListener {
            gotoMain()
        }
        btnReplay.setOnClickListener {
        }
    }

    private fun otpHandle(length: Int, pos: Int) {
        currentPosOTP = pos
        if (length == 1 && pos < listOTP.size - 1) {
            currentPosOTP++
        }

        if (length == 0 && pos > 0) {
            currentPosOTP--
        }

        listOTP.forEach {
            it.isEnabled = false
            it.setTextColor(Color.BLACK)
        }
        listOTP[currentPosOTP].isEnabled = true
        listOTP[currentPosOTP].requestFocus()
    }

    fun gotoMain() {
        val i = Intent()
        i.setClassName("com.example.privytest","com.example.privytest.ui.ActivityMain")
        startActivity(i)
    }

    //disable back
    override fun finish() {}

}