package com.example.androidclient.ui.chartsKitchen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChartsKitchenViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is chartsLivingroom Fragment"
    }
    val text: LiveData<String> = _text
}