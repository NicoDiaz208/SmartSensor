package com.example.androidclient.ui.liveLivingroom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidclient.R

class LiveLivingroomFragment : Fragment() {

    private lateinit var liveLivingroomViewModel: ChartsLivingroomViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        liveLivingroomViewModel =
                ViewModelProviders.of(this).get(ChartsLivingroomViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_live_livingroom, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        liveLivingroomViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
