#!/usr/bin/env python
import paho.mqtt.client as paho
import time

def on_connect(client, userdata, flags, rc):
	print("CONNACK received with code %d." % (rc))

def on_publish(client, userdata, mid):
	print("mid: "+str(mid))

def on_subscribe(client, userdata, mid, granted_qos):
    print("Subscribed: "+str(mid)+" "+str(granted_qos))

def on_message(client, userdata, msg):
    print(msg.topic+" "+str(msg.qos)+" "+str(msg.payload))  

client = paho.Client()
client.on_subscribe = on_subscribe
client.on_message = on_message
client.connect("169.254.219.0",8080)
client.subscribe("encyclopedia/#",qos=1)
client.loop_start()

while True:
	temperature = read_from_imaginary_thermometer()
	(rc,mid) = client.publish("encyclopedia/temperature",str(temperature), qos=1)
	time.sleep(30)

