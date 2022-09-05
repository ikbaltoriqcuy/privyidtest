package com.example.privytest.ui.fragment

import android.content.Intent
import android.widget.Toast
import com.example.common_base.BaseFragment
import com.example.feature_auth.ui.login.ActivityLogin
import com.example.privytest.R
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentProfile : BaseFragment() {
    val viewModel: ProfileViewModel by viewModel()

    override fun setLayoutId(): Int = R.layout.fragment_profile

    override fun initView() {
        setEventView()
        setupObserver()
    }

    fun setupObserver() {
        with(viewModel) {
            route.observe(viewLifecycleOwner) {
                when(it) {
                    ProfileViewModel.ROUTE_LOGIN -> gotoLogin()
                }
            }
            message.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setEventView() {
        btnSave.setOnClickListener {
            saveAll()
        }
        btnLogout.setOnClickListener {
            viewModel.logOut()
        }
    }

    fun saveAll() {
        with(viewModel) {
            saveProfile(
                lblNameValue.text.toString(),
                lblGenderValue.text.toString(),
                lblBirthDateValue.text.toString(),
                lblPlaceValue.text.toString(),
                lblBioValue.text.toString()
            )

            saveEducation(
                lblSchoolNameValue.text.toString(),
                lblGraduationTimeValue.text.toString()
            )

            saveCareer(
                lblPositionCareerValue.text.toString(),
                lblCompanyCareerValue.text.toString(),
                lblStartCareerValue.text.toString(),
                lblEndCareerValue.text.toString()
            )
        }
    }

    fun gotoLogin() {
        val i = Intent(context, ActivityLogin::class.java)
        startActivity(i)
    }

}