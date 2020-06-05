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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class LiveKitchenViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is kitchen Fragment"
    }
    val text: LiveData<String> = _text
    private val _temperatureText = MutableLiveData<String>().apply {
        value = x
    }
    val temperatureText: LiveData<String> =  _temperatureText
    var x: String? = "2"
    init {
        Log.d(LOG_TAG,"Test")
        //Repo.getDataByTopic("Htl-Leonding2020NVS/SmartHome/Kitchen/Temperature");
        val data = GlobalScope.launch {
            Log.d(LiveGarageViewModel.LOG_TAG, "Load...")
            val deferredObject =
                SmartHomeApi.retrofitService.getByTopic("Htl-Leonding2020NVS/SmartHome/Kitchen/Temperatur","1")
            try {
                val listResult = deferredObject.await()
                Log.d(LOG_TAG, "ListResult:")
                for (x in listResult) {
                    Log.d(LOG_TAG, x.message)
                }
                x = listResult[0].message

            } catch (ex: Exception) {
                Log.e(LiveGarageViewModel.LOG_TAG, "load from api exception ${ex.message}")
            }
        }
        // _temperatureText.value = x
    }
}