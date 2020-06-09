package com.example.androidclient.ui.liveCellar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidclient.R

class LiveCellarFragment : Fragment() {

    private lateinit var liveCellarViewModel: LiveCellarViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        liveCellarViewModel =
                ViewModelProviders.of(this).get(LiveCellarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_live_cellar, container, false)
        val temperatureText: TextView = root.findViewById(R.id.text_temperature)
        liveCellarViewModel.temperatureText.observe(viewLifecycleOwner, Observer {
            temperatureText.text = it
        })
        val humidityText: TextView = root.findViewById(R.id.text_humidity)
        liveCellarViewModel.humidityText.observe(viewLifecycleOwner, Observer {
            humidityText.text = it
        })
        val airPressureText: TextView = root.findViewById(R.id.text_airpressure)
        liveCellarViewModel.airPressureText.observe(viewLifecycleOwner, Observer {
            airPressureText.text = it
        })
        val gasText: TextView = root.findViewById(R.id.text_gas)
        liveCellarViewModel.gasText.observe(viewLifecycleOwner, Observer {
            gasText.text = it
        })
        return root
    }
}
