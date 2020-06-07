package com.example.androidclient.ui.liveKitchen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidclient.R

class LiveKitchenFragment : Fragment() {

    private lateinit var liveKitchenViewModel: LiveKitchenViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        liveKitchenViewModel =
                ViewModelProviders.of(this).get(LiveKitchenViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_live_kitchen, container, false)
        val temperatureText: TextView = root.findViewById(R.id.text_temperature)
        liveKitchenViewModel.temperatureText.observe(viewLifecycleOwner, Observer {
            temperatureText.text = it
        })
        val humidityText: TextView = root.findViewById(R.id.text_humidity)
        liveKitchenViewModel.humidityText.observe(viewLifecycleOwner, Observer {
            humidityText.text = it
        })
        val airPressureText: TextView = root.findViewById(R.id.text_airpressure)
        liveKitchenViewModel.airPressureText.observe(viewLifecycleOwner, Observer {
            airPressureText.text = it
        })
        val gasText: TextView = root.findViewById(R.id.text_gas)
        liveKitchenViewModel.gasText.observe(viewLifecycleOwner, Observer {
            gasText.text = it
        })
        return root
    }
}
