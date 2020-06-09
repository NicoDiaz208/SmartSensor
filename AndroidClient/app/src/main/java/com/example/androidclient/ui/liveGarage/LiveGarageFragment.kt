package com.example.androidclient.ui.liveGarage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidclient.R

class LiveGarageFragment : Fragment() {

    private lateinit var liveGarageViewModel: LiveGarageViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        liveGarageViewModel =
                ViewModelProviders.of(this).get(LiveGarageViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_live_garage, container, false)
        val temperatureText: TextView = root.findViewById(R.id.text_temperature)
        liveGarageViewModel.temperatureText.observe(viewLifecycleOwner, Observer {
            temperatureText.text = it
        })
        val humidityText: TextView = root.findViewById(R.id.text_humidity)
        liveGarageViewModel.humidityText.observe(viewLifecycleOwner, Observer {
            humidityText.text = it
        })
        val airPressureText: TextView = root.findViewById(R.id.text_airpressure)
        liveGarageViewModel.airPressureText.observe(viewLifecycleOwner, Observer {
            airPressureText.text = it
        })
        val gasText: TextView = root.findViewById(R.id.text_gas)
        liveGarageViewModel.gasText.observe(viewLifecycleOwner, Observer {
            gasText.text = it
        })
        return root
    }
}
