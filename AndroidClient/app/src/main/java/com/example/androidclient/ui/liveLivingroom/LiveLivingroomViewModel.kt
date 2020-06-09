package com.example.androidclient.ui.liveLivingroom

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidclient.ui.liveGarage.LiveGarageViewModel
import com.example.androidclient.ui.service.MQTTConnectionParams
import com.example.androidclient.ui.service.MQTTmanager
import com.example.androidclient.ui.service.Repo
import com.example.androidclient.ui.service.UIUpdaterInterface

class LiveLivingroomViewModel(application: Application) : AndroidViewModel(application) {
    private var mqttManager: MQTTmanager? = null

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text
    private val _temperatureText = MutableLiveData<String>().apply {
        value = "0"
    }
    val temperatureText: LiveData<String> = _temperatureText

    private val _gasText = MutableLiveData<String>().apply {
        value = "0"
    }
    val gasText: LiveData<String> = _gasText
    private val _humidityText = MutableLiveData<String>().apply {
        value = "0"
    }
    val humidityText: LiveData<String> = _humidityText
    private val _airPressureText = MutableLiveData<String>().apply {
        value = "0"
    }
    val airPressureText: LiveData<String> = _airPressureText
    val _lightState = MutableLiveData<Boolean>().apply {
        value = false
    }
    val lightState: LiveData<Boolean> = _lightState

    init {
        Log.d(LiveGarageViewModel.LOG_TAG, "Test")
        Repo.getDataByTopic(
            "Htl-Leonding2020NVS/SmartHome/Livingroom/Temperatur",
            "1",
            _temperatureText
        )
        Repo.getDataByTopic("Htl-Leonding2020NVS/SmartHome/Livingroom/Humidity", "1", _humidityText)
        Repo.getDataByTopic(
            "Htl-Leonding2020NVS/SmartHome/Livingroom/AirPressure",
            "1",
            _airPressureText
        )
        Repo.getDataByTopic(
            "Htl-Leonding2020NVS/SmartHome/Livingroom/Gas",
            "1",
            _gasText
        )
        Repo.getDataByTopic(
            "Htl-Leonding2020NVS/SmartHome/Livingroom/Light",
            "1",
            light=_lightState
        )
        connect()
    }
    fun connect(){

        var host = "tcp://" + "broker.mqttdashboard.com:1883"
        var topic = "Htl-Leonding2020NVS/SmartHome/Livingroom/Light"
        var connectionParams = MQTTConnectionParams("MQTTSample",host,topic,"","")
        mqttManager = MQTTmanager(connectionParams,getApplication())
        Log.i("x","Connected to Broker")
        // sendMessage()
    }

    fun disconnect(){
        mqttManager?.disconnect()
    }

    fun sendMessage(msg: String){
        mqttManager?.connect(msg)
    }

}