package com.example.androidclient.ui.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import at.htl.countries.model.Data
import at.htl.countries.service.SmartHomeApi
import com.example.androidclient.ui.liveGarage.LiveGarageViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import com.github.mikephil.charting.data.Entry


object Repo {
    var kitchenTemperature :List<Data> = listOf(Data("1",1.0,"",""));
    lateinit var kitchenHumidity :List<Data>;
    lateinit var kitchenAirpressure :List<Data>;
    public var amount = "100000"
    suspend fun getAllData(): List<Data> = coroutineScope {
        val data = GlobalScope.async {
            Log.d(LiveGarageViewModel.LOG_TAG, "Load...")
            val deferredObject =
                SmartHomeApi.retrofitService.getAllData()
            try {
                val listResult = deferredObject.await()
                for (x in listResult) {
                    Log.d(LiveGarageViewModel.LOG_TAG, x.message)
                }
                kitchenTemperature = listResult;
                // Log.d(LOG_TAG,listResult)
            } catch (ex: Exception) {
                Log.e(LiveGarageViewModel.LOG_TAG, "load from api exception ${ex.message}")
            }
        }
        data.await() as List<Data>
    }

    fun getDataByTopic(topic: String, amount: String = "100", data: MutableLiveData<String> = MutableLiveData<String>(), y: ArrayList<Entry> = arrayListOf<Entry>(),light: MutableLiveData<Boolean> = MutableLiveData<Boolean>()) {
        GlobalScope.launch {
            Log.d(LiveGarageViewModel.LOG_TAG, "Load...")
            val deferredObject =
                SmartHomeApi.retrofitService.getByTopic(topic, amount)
            try {
                val listResult = deferredObject.await()
                kitchenTemperature = listResult
                var count = 0;
                for (x in listResult) {
                    Log.d(LiveGarageViewModel.LOG_TAG, x.message)
                    data.postValue(x.message)
                    if(x.message == "0"){
                        light.postValue(false)
                    }
                    else if(x.message == "1"){
                        light.postValue(true)
                    }
                    // y.add(Entry(kitchenAirpressure[count].message.toFloat(),0F))
                    count++;
                }
            } catch (ex: Exception) {
                Log.e(LiveGarageViewModel.LOG_TAG, "load from api exception ${ex.message}")
            }
        }
    }

}