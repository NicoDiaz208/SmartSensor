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
        val textView: TextView = root.findViewById(R.id.text_live_cellar)
        liveCellarViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
