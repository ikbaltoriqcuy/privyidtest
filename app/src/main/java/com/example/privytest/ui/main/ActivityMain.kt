package com.example.privytest.ui.main

import android.content.Intent
import android.view.MenuItem
import com.example.common_base.BaseActivity
import com.example.feature_auth.ui.login.ActivityLogin
import com.example.feature_auth.ui.otp.ActivityOTP
import com.example.privytest.R
import com.example.privytest.ui.fragment.FragmentMessage
import com.example.privytest.ui.fragment.FragmentProfile
import com.example.util.Utils.replaceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActivityMain : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    val viewModel: MainViewModel by viewModel()
    override fun setLayoutId(): Int = R.layout.activity_main
    override fun initView() {
        setupObserver()
        setupBottomNavigation()
    }

    fun setupObserver() {
        with(viewModel) {
           route.observe(this@ActivityMain) {
               when(it) {
                   MainViewModel.ROUTE_LOGIN -> gotoLogin()
                   MainViewModel.ROUTE_OTP -> gotoOTP()
               }
           }
        }
    }

    fun setupBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        bottomNavigation.selectedItemId = R.id.menuHome
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuHome -> {
                replaceFragment(R.id.flContent, FragmentHome())
                true
            }
            R.id.menuMessage -> {
                replaceFragment(R.id.flContent, FragmentMessage())
                true
            }
            R.id.menuProfile -> {
                replaceFragment(R.id.flContent, FragmentProfile())
                true
            }
            else -> false
        }
    }

    fun gotoLogin() {
        val i = Intent(this@ActivityMain, ActivityLogin::class.java)
        startActivity(i)
    }

    fun gotoOTP() {
        val i = Intent(this@ActivityMain, ActivityOTP::class.java)
        startActivity(i)
    }
}