package com.example.androidclient.ui.chartsGarage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChartsGarageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is chartsLivingroom Fragment"
    }
    val text: LiveData<String> = _text
}