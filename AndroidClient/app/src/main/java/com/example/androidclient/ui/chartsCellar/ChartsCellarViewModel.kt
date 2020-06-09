package com.example.androidclient.ui.chartsCellar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChartsCellarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is chartsLivingroom Fragment"
    }
    val text: LiveData<String> = _text
}