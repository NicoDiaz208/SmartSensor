package com.example.androidclient.ui.liveCellar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidclient.ui.liveGarage.LiveGarageViewModel
import com.example.androidclient.ui.service.Repo

class LiveCellarViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is cellar Fragment"
    }
    val text: LiveData<String> = _text
    private val _temperatureText = MutableLiveData<String>().apply {
        value = "43"
    }
    val temperatureText: LiveData<String> = _temperatureText

    private val _gasText = MutableLiveData<String>().apply {
        value = "Gas"
    }
    val gasText: LiveData<String> = _gasText
    private val _humidityText = MutableLiveData<String>().apply {
        value = "33"
    }
    val humidityText: LiveData<String> = _humidityText
    private val _airPressureText = MutableLiveData<String>().apply {
        value = "33"
    }
    val airPressureText: LiveData<String> = _airPressureText
    init {
        Log.d(LiveGarageViewModel.LOG_TAG, "Test")
        Repo.getDataByTopic(
            "Htl-Leonding2020NVS/SmartHome/Garage/Temperatur",
            "1",
            _temperatureText
        )
        Repo.getDataByTopic("Htl-Leonding2020NVS/SmartHome/Garage/Humidity", "1", _humidityText)
        Repo.getDataByTopic(
            "Htl-Leonding2020NVS/SmartHome/Garage/AirPressure",
            "1",
            _airPressureText
        )
        Repo.getDataByTopic(
            "Htl-Leonding2020NVS/SmartHome/Garage/Gas",
            "1",
            _gasText
        )
    }
}