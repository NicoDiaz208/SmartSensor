package com.example.androidclient.ui.service

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class AxisValueFormatter : ValueFormatter() {

    lateinit var values : Array<String>;

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return values.getOrNull(value.toInt()) ?: value.toString()
    }

}