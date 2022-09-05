package com.example.util

import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment

object Utils {
    fun Int.toMinus() = this ?: -1
    fun Int.toZero() = this ?: 0

    fun EditText.onTextChange(action: (String)-> Unit) {
        this.addTextChangedListener(
            beforeTextChanged = {_,_,_,_-> },
            onTextChanged = {text,_,_,_->
                Log.i("text-change:", text.toString())
                action(text.toString())
            },
            afterTextChanged = {
                action(it.toString())
            }
        )
    }

    fun AppCompatActivity.replaceFragment(
        containerViewId: Int,
        fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(containerViewId, it)
                .commit()
        }
    }
}