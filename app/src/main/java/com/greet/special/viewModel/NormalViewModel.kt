package com.greet.special.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


class NormalViewModel : ViewModel() {

    var menuNameSelected = MutableLiveData<String>()
        get() = field
        set(value) {
            field = value
        }


}