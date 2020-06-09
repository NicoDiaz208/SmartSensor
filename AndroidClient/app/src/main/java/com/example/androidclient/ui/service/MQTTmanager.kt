package com.example.androidclient.ui.service

import android.content.Context
import android.util.Log
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import java.io.UnsupportedEncodingException
import java.util.*


class MQTTmanager(
    private val connectionParams: MQTTConnectionParams,
    private val context: Context
) {

    private var client =
        MqttAndroidClient(context, connectionParams.host, connectionParams.clientId + id(context))
    private var uniqueID: String? = null
    private val PREF_UNIQUE_ID = "PREF_UNIQUE_ID"

    init {
        client.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(reconnect: Boolean, serverURI: String?) {
                Log.d("Mqtt", "Verbunden! $serverURI")
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                Log.d("Mqtt", message.toString())
            }

            override fun connectionLost(cause: Throwable?) {
                Log.d("Mqtt", "Verbindung getrennt!")
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {

            }
        })
    }

    fun connect(msg: String = "") {
        client.connect(MqttConnectOptions(), null, object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken) {
                // subscribe("Htl-Leonding2020NVS/SmartHome/Livingroom/Light")
                val message =
                    MqttMessage(msg.toByteArray())
                message.qos = 2
                message.isRetained = false

                try {
                    if (msg != "") {
                        Log.i("Message: ", msg)
                        client.publish("Htl-Leonding2020NVS/SmartHome/Livingroom/Light", message)
                        Log.i("x", "Message published")
                    }
                    client.disconnect()
                    Log.i("x", "client disconnected")
                } catch (e: MqttPersistenceException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    Log.i("x", "Error")
                } catch (e: MqttException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                    Log.i("x", "Error")

                }
                Log.d("Mqtt", "Mqtt funktioniert")
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d("Mqtt", "es ist etwas Schiefgelaufen")
            }
        })
    }

    fun disconnect() {
        client.disconnect(MqttConnectOptions(), object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d("Mqtt: ", "Disconnected")
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.d("Mqtt: ", "Fehler beim Disconnecten")
            }

        })
    }

    // Subscribe to topic
    fun subscribe(topic: String) {
        client.subscribe(
            "Htl-Leonding2020NVS/SmartHome/Livingroom/Light",
            0,
            null,
            object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken?) {
                    Log.d("Mqtt: ", "Subscription!")
                }

                override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                    Log.d("Mqtt: ", "Subscription fail!")
                }
            })
    }

    fun publish(message: String) {
        val message =
            MqttMessage("1".toByteArray())
        message.qos = 2
        message.isRetained = false
        try {
            client.publish("Htl-Leonding2020NVS/SmartHome/Livingroom/Light", message)
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    @Synchronized
    fun id(context: Context): String {
        if (uniqueID == null) {
            val sharedPrefs = context.getSharedPreferences(
                PREF_UNIQUE_ID, Context.MODE_PRIVATE
            )
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null)
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString()
                val editor = sharedPrefs.edit()
                editor.putString(PREF_UNIQUE_ID, uniqueID)
                editor.commit()
            }
        }
        return uniqueID!!
    }

}

data class MQTTConnectionParams(
    val clientId: String,
    val host: String,
    val topic: String,
    val username: String,
    val password: String
) {

}