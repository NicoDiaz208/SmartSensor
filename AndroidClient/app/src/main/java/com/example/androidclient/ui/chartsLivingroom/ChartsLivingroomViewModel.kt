package com.example.androidclient.ui.liveLivingroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChartsLivingroomViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is chartsLivingroom Fragment"
    }
    val text: LiveData<String> = _text
}