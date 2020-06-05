package com.example.androidclient.ui.liveLivingroom

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.androidclient.R
import com.example.androidclient.ui.service.AxisValueFormatter
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class ChartsLivingroomFragment : Fragment() {

    private lateinit var chartsLivingroomViewModel: ChartsLivingroomViewModel

    private lateinit var lineChart: LineChart


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        chartsLivingroomViewModel =
                ViewModelProviders.of(this).get(ChartsLivingroomViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_charts_livingroom, container, false)
        /* val textView: TextView = root.findViewById(R.id.text_slideshow)
        chartsLivingroomViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        lineChart = root.findViewById(R.id.linechart)

        val upperLimitLine = LimitLine(0f)
        upperLimitLine.lineWidth = 1f
        upperLimitLine.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
        upperLimitLine.textSize = 10f
        upperLimitLine.textColor = Color.BLACK
        upperLimitLine.lineColor = Color.BLACK

        val leftAxis = lineChart.axisLeft
        leftAxis.addLimitLine(upperLimitLine)

        leftAxis.axisMaximum = 200f
        leftAxis.axisMinimum = -10f

        lineChart.axisRight.isEnabled = false


        val yValueTemperature = ArrayList<Entry>();

        yValueTemperature.add(Entry(0f,10f))
        yValueTemperature.add(Entry(1f,45f))
        yValueTemperature.add(Entry(2f,20f))
        yValueTemperature.add(Entry(3f,101f))
        yValueTemperature.add(Entry(4f,121f))
        yValueTemperature.add(Entry(5f,87f))
        yValueTemperature.add(Entry(6f,56f))
        yValueTemperature.add(Entry(7f,77f))
        yValueTemperature.add(Entry(8f,80f))
        yValueTemperature.add(Entry(9f,40f))

        val set1 = LineDataSet(yValueTemperature,"Temperature")

        set1.color = Color.RED
        set1.lineWidth = 2f

        set1.valueTextColor = Color.BLACK
        set1.valueTextSize = 12f

        val y1ValueHumidity = ArrayList<Entry>();

        y1ValueHumidity.add(Entry(0f,20f))
        y1ValueHumidity.add(Entry(1f,90f))
        y1ValueHumidity.add(Entry(2f,30f))
        y1ValueHumidity.add(Entry(3f,101f))
        y1ValueHumidity.add(Entry(4f,121f))
        y1ValueHumidity.add(Entry(5f,87f))
        y1ValueHumidity.add(Entry(6f,56f))
        y1ValueHumidity.add(Entry(7f,77f))
        y1ValueHumidity.add(Entry(8f,80f))
        y1ValueHumidity.add(Entry(9f,40f))

        val set2 = LineDataSet(y1ValueHumidity,"Humidity")

        set2.color = Color.GRAY
        set2.lineWidth = 2f

        set2.valueTextColor = Color.BLACK
        set2.valueTextSize = 12f


        val yValueGas = ArrayList<Entry>();

        yValueGas.add(Entry(0f,120f))
        yValueGas.add(Entry(1f,190f))
        yValueGas.add(Entry(2f,130f))
        yValueGas.add(Entry(3f,01f))
        yValueGas.add(Entry(4f,21f))
        yValueGas.add(Entry(5f,187f))
        yValueGas.add(Entry(6f,156f))
        yValueGas.add(Entry(7f,177f))
        yValueGas.add(Entry(8f,180f))
        yValueGas.add(Entry(9f,140f))

        val set3 = LineDataSet(yValueGas,"Gas")

        set3.color = Color.YELLOW
        set3.lineWidth = 2f

        set3.valueTextColor = Color.BLACK
        set3.valueTextSize = 12f


        val yValueAirpressure = ArrayList<Entry>();

        yValueAirpressure.add(Entry(0f,9f))
        yValueAirpressure.add(Entry(1f,8f))
        yValueAirpressure.add(Entry(2f,7f))
        yValueAirpressure.add(Entry(3f,6f))
        yValueAirpressure.add(Entry(4f,5f))
        yValueAirpressure.add(Entry(5f,4f))
        yValueAirpressure.add(Entry(6f,3f))
        yValueAirpressure.add(Entry(7f,2f))
        yValueAirpressure.add(Entry(8f,1f))
        yValueAirpressure.add(Entry(9f,0f))

        val set4 = LineDataSet(yValueAirpressure,"Airpressure")

        set4.color = Color.BLUE
        set4.lineWidth = 2f

        set4.valueTextColor = Color.BLACK
        set4.valueTextSize = 12f

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set1)
        dataSets.add(set2)
        dataSets.add(set3)
        dataSets.add(set4)
        val data = LineData(dataSets)

        lineChart.data = data;

        val values = arrayOf<String>("Jan","Feb","Mar","Apr","Mai","Jun","Jul","Aug","Sep","Okt")

        val xAxis = lineChart.xAxis
        val formatter = AxisValueFormatter()
        formatter.values = values
        xAxis.valueFormatter = formatter

        return root
    }
}
