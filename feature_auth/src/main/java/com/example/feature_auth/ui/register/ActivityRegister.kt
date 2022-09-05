package com.example.feature_auth.ui.register

import android.content.Intent
import android.view.View
import com.example.common_base.BaseActivity
import com.example.feature_auth.R
import com.example.feature_auth.ui.otp.ActivityOTP
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivityRegister : BaseActivity() {
    val viewModel: RegisterViewModel by viewModel()
    override fun setLayoutId(): Int = R.layout.activity_register

    override fun initView() {
        setupObserver()
        setupEventView()
    }

    private fun setupObserver() {
        with(viewModel) {
            isLoading.observe(this@ActivityRegister) {
                btnRegister.isEnabled = !it
                pbLoading.visibility = if (it) View.VISIBLE
                                       else View.GONE
            }
            route.observe(this@ActivityRegister) {
                when (it) {
                    RegisterViewModel.ROUTE_OTP -> gotoOTP()
                }
            }
        }
    }

    private fun setupEventView() {
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        btnRegister.setOnClickListener {
            viewModel.register(
                edUsername.text.toString(),
                edPassword.text.toString(),
                edCountry.text.toString()
            )
        }
    }

    private fun gotoOTP() {
        val i = Intent(this@ActivityRegister, ActivityOTP::class.java)
        startActivity(i)
    }

}