package com.example.feature_auth.ui.login

import android.content.Intent
import android.view.View
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
            isLoading.observe(this@ActivityLogin) {
                btnLogin.isEnabled = !it
                pbLoading.visibility = if (it) View.VISIBLE
                                        else View.GONE
            }
            route.observe(this@ActivityLogin) {
                when (it) {
                    LoginViewModel.ROUTE_REGISTER -> {
                        gotoRegister()
                    }
                    LoginViewModel.ROUTE_MAIN -> gotoMain()
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

    private fun gotoMain() {
        val i = Intent()
        i.setClassName("com.example.privytest","com.example.privytest.ui.main.ActivityMain")
        startActivity(i)
    }

    private fun gotoRegister() {
        val i = Intent(this@ActivityLogin, ActivityRegister::class.java)
        startActivity(i)
    }
}