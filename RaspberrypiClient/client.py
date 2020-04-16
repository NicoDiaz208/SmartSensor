#!/usr/bin/env python
import paho.mqtt.client as paho
import time
import os
import light as _light

def on_connect(client, userdata, flags, rc):
    os.system("clear")
    print("CONNACK received with code %d." % (rc))

def on_publish(client, userdata, mid):
    print("mid: "+str(mid))

def on_subscribe(client, userdata, mid, granted_qos):
    print("Subscribed: "+str(mid)+" "+str(granted_qos))

#-------------------------------------------------------------------------------------
def on_message(client, userdata, msg):
    room = msg.topic.split("/")[2]
    action = msg.payload
    _light.switch(room, action)
    

client = paho.Client(client_id="RaspberryPi")
client.on_connect = on_connect
client.on_publish = on_publish
client.on_subscribe = on_subscribe
client.on_message = on_message
client.connect("broker.mqttdashboard.com",1883)
client.subscribe("Htl-Leonding2020NVS/SmartHome/Livingroom/Light",qos=1)
client.loop_start()

while True:
#   msg  = client.publish("SmartHome/Light","HelloWorld",qos=1)
    time.sleep(10)
#   if msg.is_published() == False:
#       print("Message is not yet pushed")
#   else:
#       print("Message pushed")

