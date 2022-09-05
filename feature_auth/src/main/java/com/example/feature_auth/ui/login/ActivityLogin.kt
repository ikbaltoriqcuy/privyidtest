package com.example.feature_auth.ui.login

import android.content.Intent
import android.widget.Toast
import com.example.common_base.BaseActivity
import com.example.feature_auth.R
import com.example.feature_auth.ui.register.ActivityRegister
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivityLogin : BaseActivity() {

    private val viewModel: LoginViewModel by viewModel()
    override fun setLayoutId(): Int = R.layout.activity_login

    override fun initView() {
        setupObserver()
        setEventView()
    }

    private fun setupObserver() {
        with(viewModel) {
            route.observe(this@ActivityLogin) {
                when (it) {
                    LoginViewModel.ROUTE_REGISTER -> {
                        gotoRegister()
                    }
                    LoginViewModel.ROUTE_MAIN -> {}
                    LoginViewModel.ROUTE_FAILED -> {
                        Toast.makeText(this@ActivityLogin, "Gagal Load Data, Coba lagi", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setEventView() {
        btnLogin.setOnClickListener {
            viewModel.login(
                edUsername.text.toString(),
                edPassword.text.toString()
            )
        }
        btnRegister.setOnClickListener {
            viewModel.route.value = LoginViewModel.ROUTE_REGISTER
        }
    }

    private fun gotoRegister() {
        val i = Intent(this@ActivityLogin, ActivityRegister::class.java)
        startActivity(i)
    }
}