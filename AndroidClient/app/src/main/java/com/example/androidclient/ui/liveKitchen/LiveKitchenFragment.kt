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
        val textView: TextView = root.findViewById(R.id.text_home)
        liveKitchenViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        val temperatureText: TextView = root.findViewById(R.id.text_temperature)
        liveKitchenViewModel.temperatureText.observe(viewLifecycleOwner, Observer {
            temperatureText.text = it
        })
        return root
    }
}
