package com.example.androidclient.ui.liveGarage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.htl.countries.service.SmartHomeApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class LiveGarageViewModel : ViewModel() {

    companion object {
        val LOG_TAG = LiveGarageViewModel::class.java.simpleName
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}