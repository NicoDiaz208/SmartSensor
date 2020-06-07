package com.example.androidclient.ui.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import at.htl.countries.model.Data
import at.htl.countries.service.SmartHomeApi
import com.example.androidclient.ui.liveGarage.LiveGarageViewModel
import kotlinx.coroutines.*
import java.lang.Exception

object Repo {
    var kitchenTemperature :List<Data> = listOf(Data("1",1.0,"",""));
    lateinit var kitchenHumidity :List<Data>;
    lateinit var kitchenAirpressure :List<Data>;

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

    fun getDataByTopic(topic: String, amount: String = "100", data: MutableLiveData<String>) {
        GlobalScope.launch {
            Log.d(LiveGarageViewModel.LOG_TAG, "Load...")
            val deferredObject =
                SmartHomeApi.retrofitService.getByTopic(topic, amount)
            try {
                val listResult = deferredObject.await()
                kitchenTemperature = listResult
                for (x in listResult) {
                    Log.d(LiveGarageViewModel.LOG_TAG, x.message)
                }
                data.postValue(kitchenTemperature[0].message)
            } catch (ex: Exception) {
                Log.e(LiveGarageViewModel.LOG_TAG, "load from api exception ${ex.message}")
            }
        }
    }

}