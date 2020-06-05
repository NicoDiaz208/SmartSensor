package com.example.androidclient.ui.liveCellar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveCellarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is cellar Fragment"
    }
    val text: LiveData<String> = _text
}