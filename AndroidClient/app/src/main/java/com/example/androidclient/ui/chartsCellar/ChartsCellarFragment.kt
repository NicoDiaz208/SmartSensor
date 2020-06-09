package com.example.androidclient.ui.chartsCellar

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import at.htl.countries.service.SmartHomeApi
import com.example.androidclient.R
import com.example.androidclient.ui.liveGarage.LiveGarageViewModel
import com.example.androidclient.ui.service.AxisValueFormatter
import com.example.androidclient.ui.service.Repo
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChartsCellarFragment : Fragment() {

    private lateinit var chartsCellarViewModel: ChartsCellarViewModel

    private lateinit var lineChart: LineChart

    private val sdf = SimpleDateFormat("dd/MM")
    private var maxY = 0f
    private var minY = 100f
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chartsCellarViewModel =
            ViewModelProviders.of(this).get(ChartsCellarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_charts_cellar, container, false)
        /* val textView: TextView = root.findViewById(R.id.text_slideshow)
        chartsCellarViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        lineChart = root.findViewById(R.id.linechart)
        lineChart.setNoDataText("Loading Data...")
        GlobalScope.launch {

            val upperLimitLine = LimitLine(0f)
            upperLimitLine.lineWidth = 1f
            upperLimitLine.labelPosition = LimitLine.LimitLabelPosition.RIGHT_TOP
            upperLimitLine.textSize = 10f
            upperLimitLine.textColor = Color.BLACK
            upperLimitLine.lineColor = Color.BLACK

            val leftAxis = lineChart.axisLeft
            leftAxis.addLimitLine(upperLimitLine)

            lineChart.axisRight.isEnabled = false


            val yValueTemperature = ArrayList<Entry>();

            /*yValueTemperature.add(Entry(0f, 10f))
            yValueTemperature.add(Entry(1f, 45f))
            yValueTemperature.add(Entry(2f, 20f))
            yValueTemperature.add(Entry(3f, 101f))
            yValueTemperature.add(Entry(4f, 121f))
            yValueTemperature.add(Entry(5f, 87f))
            yValueTemperature.add(Entry(6f, 56f))
            yValueTemperature.add(Entry(7f, 77f))
            yValueTemperature.add(Entry(8f, 80f))
            yValueTemperature.add(Entry(9f, 40f))*/
            var deferredObject =
                SmartHomeApi.retrofitService.getByTopic(
                    "Htl-Leonding2020NVS/SmartHome/Cellar/Temperatur",
                    Repo.amount
                )
            try {
                val listResult = deferredObject.await()
                Repo.kitchenTemperature = listResult
                var count = 0;
                for (x in listResult) {
                    Log.d(LiveGarageViewModel.LOG_TAG, x.message)
                    val date = Date(x.timestamp.toLong())
                    Log.i("date:", "" + date.day)
                    Log.i("date:", "" + x.timestamp)
                    Log.i("format", sdf.format(date))
                    Log.i("Seconds", "" + date.seconds)
                    yValueTemperature.add(Entry(count.toFloat(), x.message.toFloat()))
                    count++;
                    if(x.message.toFloat() > maxY){
                        maxY = x.message.toFloat() + 40
                    }
                    if(x.message.toFloat() < minY){
                        minY = x.message.toFloat()
                    }
                }
                Log.i("x", "l")
            } catch (ex: Exception) {
                Log.e(LiveGarageViewModel.LOG_TAG, "load from api exception ${ex.message}")
            }
            val set1 = LineDataSet(yValueTemperature, "Temperature")

            set1.color = Color.RED
            set1.lineWidth = 2f
            set1.valueTextColor = Color.BLACK
            set1.valueTextSize = 10f
            val y1ValueHumidity = ArrayList<Entry>();

            /*y1ValueHumidity.add(Entry(0f, 20f))
            y1ValueHumidity.add(Entry(1f, 90f))
            y1ValueHumidity.add(Entry(2f, 30f))
            y1ValueHumidity.add(Entry(3f, 101f))
            y1ValueHumidity.add(Entry(4f, 121f))
            y1ValueHumidity.add(Entry(5f, 87f))
            y1ValueHumidity.add(Entry(6f, 56f))
            y1ValueHumidity.add(Entry(7f, 77f))
            y1ValueHumidity.add(Entry(8f, 80f))
            y1ValueHumidity.add(Entry(9f, 40f))*/
            deferredObject =
                SmartHomeApi.retrofitService.getByTopic(
                    "Htl-Leonding2020NVS/SmartHome/Cellar/Humidity",
                    Repo.amount
                )
            try {
                val listResult = deferredObject.await()
                Repo.kitchenTemperature = listResult
                var count = 0;
                for (x in listResult) {
                    Log.d(LiveGarageViewModel.LOG_TAG, x.message)
                    val date = Date(x.timestamp.toLong())
                    Log.i("date:", "" + date.day)
                    Log.i("date:", "" + x.timestamp)
                    Log.i("format", sdf.format(date))
                    Log.i("Seconds", "" + date.seconds)
                    y1ValueHumidity.add(Entry(count.toFloat(), x.message.toFloat()))
                    count++;
                    if(x.message.toFloat() > maxY){
                        maxY = x.message.toFloat() + 40
                    }
                    if(x.message.toFloat() < minY){
                        minY = x.message.toFloat()
                    }
                }
                Log.i("x", "l")
            } catch (ex: Exception) {
                Log.e(LiveGarageViewModel.LOG_TAG, "load from api exception ${ex.message}")
            }
            val set2 = LineDataSet(y1ValueHumidity, "Humidity")

            set2.color = Color.GRAY
            set2.lineWidth = 2f
            set2.valueTextColor = Color.BLACK
            set2.valueTextSize = 10f

            val yValueGas = ArrayList<Entry>();

            /*yValueGas.add(Entry(0f, 120f))
            yValueGas.add(Entry(1f, 190f))
            yValueGas.add(Entry(2f, 130f))
            yValueGas.add(Entry(3f, 01f))
            yValueGas.add(Entry(4f, 21f))
            yValueGas.add(Entry(5f, 187f))
            yValueGas.add(Entry(6f, 156f))
            yValueGas.add(Entry(7f, 177f))
            yValueGas.add(Entry(8f, 180f))
            yValueGas.add(Entry(9f, 140f))*/
            deferredObject =
                SmartHomeApi.retrofitService.getByTopic(
                    "Htl-Leonding2020NVS/SmartHome/Cellar/Gas",
                    Repo.amount
                )
            try {
                val listResult = deferredObject.await()
                Repo.kitchenTemperature = listResult
                var count = 0;
                for (x in listResult) {
                    Log.d(LiveGarageViewModel.LOG_TAG, x.message)
                    val date = Date(x.timestamp.toLong())
                    Log.i("date:", "" + date.day)
                    Log.i("date:", "" + x.timestamp)
                    Log.i("format", sdf.format(date))
                    Log.i("Seconds", "" + date.seconds)
                    yValueGas.add(Entry(count.toFloat(), x.message.toFloat()))
                    count++;
                    if(x.message.toFloat() > maxY){
                        maxY = x.message.toFloat() + 40
                    }
                    if(x.message.toFloat() < minY){
                        minY = x.message.toFloat()
                    }
                }
                Log.i("x", "l")
            } catch (ex: Exception) {
                Log.e(LiveGarageViewModel.LOG_TAG, "load from api exception ${ex.message}")
            }
            val set3 = LineDataSet(yValueGas, "Gas")

            set3.color = Color.YELLOW
            set3.lineWidth = 2f
            set3.valueTextColor = Color.BLACK
            set3.valueTextSize = 10f

            val yValueAirpressure = ArrayList<Entry>();
            val dataSets = ArrayList<ILineDataSet>()

            /*yValueAirpressure.add(Entry(0f,9f))
        yValueAirpressure.add(Entry(1f,8f))
        yValueAirpressure.add(Entry(2f,7f))
        yValueAirpressure.add(Entry(3f,6f))
        yValueAirpressure.add(Entry(4f,5f))
        yValueAirpressure.add(Entry(5f,4f))
        yValueAirpressure.add(Entry(6f,3f))
        yValueAirpressure.add(Entry(7f,2f))
        yValueAirpressure.add(Entry(8f,1f))
        yValueAirpressure.add(Entry(9f,0f))*/
            //val values = arrayListOf<String>()
            val values = arrayListOf<String>()
            var lastValueY = ""
            deferredObject =
                SmartHomeApi.retrofitService.getByTopic(
                    "Htl-Leonding2020NVS/SmartHome/Cellar/AirPressure",
                    Repo.amount
                )
            try {
                val listResult = deferredObject.await()
                Repo.kitchenTemperature = listResult
                var count = 0;
                for (x in listResult) {
                    Log.d(LiveGarageViewModel.LOG_TAG, x.message)
                    val date = Date(x.timestamp.toLong())
                    Log.i("date:", "" + date.day)
                    Log.i("date:", "" + x.timestamp)
                    Log.i("format", sdf.format(date))
                    Log.i("Seconds", "" + date.seconds)
                    yValueAirpressure.add(Entry(count.toFloat(), x.message.toFloat()))
                    // values.add(""+count)
                    if(lastValueY == sdf.format(date)){
                        values.add("${date.hours}:${date.minutes}:${date.seconds}")
                    }
                    else{
                        lastValueY = sdf.format(date)
                        values.add(""+sdf.format(date))
                    }
                    count++;
                    if(x.message.toFloat() > maxY){
                        maxY = x.message.toFloat() + 40
                    }
                    if(x.message.toFloat() < minY){
                        minY = x.message.toFloat()
                    }
                }
            } catch (ex: Exception) {
                Log.e(LiveGarageViewModel.LOG_TAG, "load from api exception ${ex.message}")
            }

            val set4 = LineDataSet(yValueAirpressure, "Airpressure")
            set4.color = Color.BLUE
            set4.lineWidth = 2f
            set4.valueTextColor = Color.BLACK
            set4.valueTextSize = 10f
            dataSets.add(set4)


            dataSets.add(set1)
            dataSets.add(set2)
            dataSets.add(set3)
            val data = LineData(dataSets)

            lineChart.data = data;


            val xAxis = lineChart.xAxis
            val formatter = AxisValueFormatter()
            formatter.values = values.toTypedArray()
            xAxis.valueFormatter = formatter
            leftAxis.axisMaximum = maxY
            leftAxis.axisMinimum = minY
            lineChart.invalidate();
        }
        return root
    }
}
