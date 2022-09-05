package com.example.feature_auth.ui.otp

import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.Toast
import com.example.common_base.BaseActivity
import com.example.feature_auth.R
import com.example.util.Utils.onTextChange
import kotlinx.android.synthetic.main.activity_otp.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivityOTP : BaseActivity() {

    val viewModel: OTPViewModel by viewModel()
    var currentPosOTP = 0
    val listOTP by lazy {
        listOf(edOTPOne, edOTPTwo, edOTPThree, edOTPFour)
    }

    override fun setLayoutId(): Int = R.layout.activity_otp

    override fun initView() {
        setupObserver()
        setEventView()
    }

    private fun setupObserver() {
        with(viewModel) {
            route.observe(this@ActivityOTP) {
                when (it) {
                    OTPViewModel.ROUTE_MAIN -> gotoMain()
                    OTPViewModel.ROUTE_FAILED -> Toast.makeText(this@ActivityOTP, "Oops, ada yang salah", Toast.LENGTH_SHORT).show()
                }
            }
            isLoading.observe(this@ActivityOTP) {
                btnVerification.isEnabled = !it
                btnReplay.isEnabled = !it
                pbLoading.visibility = if (it) View.VISIBLE
                                       else View.GONE
            }
            message.observe(this@ActivityOTP) {
                Toast.makeText(this@ActivityOTP, it, Toast.LENGTH_SHORT).show()
            }
        }
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
            viewModel.matchOtp()
        }
        btnReplay.setOnClickListener {
            viewModel.resendOtp()
        }
    }

    private fun otpHandle(length: Int, pos: Int) {
        viewModel.otpValue = ""
        currentPosOTP = pos
        if (length == 1 && pos < listOTP.size - 1) {
            currentPosOTP++
        }

        if (length == 0 && pos > 0) {
            currentPosOTP--
        }

        listOTP.forEach {
            viewModel.otpValue += it.text.toString()
            it.isEnabled = false
            it.setTextColor(Color.BLACK)
        }
        listOTP[currentPosOTP].isEnabled = true
        listOTP[currentPosOTP].requestFocus()
    }

    fun gotoMain() {
        val i = Intent()
        i.setClassName("com.example.privytest","com.example.privytest.ui.main.ActivityMain")
        startActivity(i)
    }

    //disable back
    override fun finish() {}

}