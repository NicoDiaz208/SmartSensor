package com.example.androidclient.ui.liveKitchen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.htl.countries.model.Data
import at.htl.countries.service.SmartHomeApi
import com.example.androidclient.ui.liveGarage.LiveGarageViewModel
import com.example.androidclient.ui.liveGarage.LiveGarageViewModel.Companion.LOG_TAG
import com.example.androidclient.ui.service.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class LiveKitchenViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is kitchen Fragment"
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
        Log.d(LOG_TAG, "Test")
        Repo.getDataByTopic(
            "Htl-Leonding2020NVS/SmartHome/Kitchen/Temperatur",
            "1",
            _temperatureText
        )
        Repo.getDataByTopic("Htl-Leonding2020NVS/SmartHome/Kitchen/Humidity", "1", _humidityText)
        Repo.getDataByTopic(
            "Htl-Leonding2020NVS/SmartHome/Kitchen/AirPressure",
            "1",
            _airPressureText
        )

        /*GlobalScope.launch {
            Log.d(LiveGarageViewModel.LOG_TAG, "Load...")
            val deferredObjectTemperature =
                SmartHomeApi.retrofitService.getByTopic(
                    "Htl-Leonding2020NVS/SmartHome/Kitchen/Temperatur",
                    "1"
                )
            try {
                val listResult = deferredObjectTemperature.await()
                Log.d(LOG_TAG, "ListResult:")
                for (x in listResult) {
                    Log.d(LOG_TAG, x.message)
                }
                Log.i("Test", listResult[0].message)
                _temperatureText.postValue(listResult[0].message)
            } catch (ex: Exception) {
                Log.e(LiveGarageViewModel.LOG_TAG, "load from api exception ${ex.message}")
            }
        }*/
    }

}