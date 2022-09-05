package com.example.common_base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    var route = MutableLiveData<String>()
    var action = MutableLiveData<String>()
}