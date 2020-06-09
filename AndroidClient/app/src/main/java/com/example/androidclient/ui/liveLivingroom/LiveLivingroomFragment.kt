package com.example.androidclient.ui.liveLivingroom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidclient.R
import com.example.androidclient.ui.service.MQTTmanager


class LiveLivingroomFragment : Fragment() {

    private lateinit var liveLivingroomViewModel: LiveLivingroomViewModel
    private var mqttManager: MQTTmanager? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        liveLivingroomViewModel =
            ViewModelProviders.of(this).get(LiveLivingroomViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_live_livingroom, container, false)
        val temperatureText: TextView = root.findViewById(R.id.text_temperature)
        liveLivingroomViewModel.temperatureText.observe(viewLifecycleOwner, Observer {
            temperatureText.text = it
        })
        val humidityText: TextView = root.findViewById(R.id.text_humidity)
        liveLivingroomViewModel.humidityText.observe(viewLifecycleOwner, Observer {
            humidityText.text = it
        })
        val airPressureText: TextView = root.findViewById(R.id.text_airpressure)
        liveLivingroomViewModel.airPressureText.observe(viewLifecycleOwner, Observer {
            airPressureText.text = it
        })
        val gasText: TextView = root.findViewById(R.id.text_gas)
        liveLivingroomViewModel.gasText.observe(viewLifecycleOwner, Observer {
            gasText.text = it
        })
        val lightSwitch = root.findViewById(R.id.lightSwitch) as Switch
        liveLivingroomViewModel.lightState.observe(viewLifecycleOwner, Observer {
            lightSwitch.isChecked = it
        })

        lightSwitch.setOnCheckedChangeListener { _, isChecked ->
            liveLivingroomViewModel._lightState.postValue(isChecked)
            liveLivingroomViewModel.sendMessage(if(isChecked) "1" else "0")
        }
        return root
    }
}
