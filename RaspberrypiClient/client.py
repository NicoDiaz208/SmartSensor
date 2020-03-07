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
	if msg.payload == "on":
		print("Light is ON")
	print(msg.topic+" "+str(msg.qos)+" "+str(msg.payload))

client = paho.Client(client_id="RaspberryPi_Nico")
client.on_connect = on_connect
client.on_publish = on_publish
client.on_subscribe = on_subscribe
client.on_message = on_message
client.connect("169.254.219.0",1883)
client.subscribe("nico/raspberry",qos=1)
client.loop_start()

while True:
	msg  = client.publish("SmartHome/Light","HelloWorld",qos=1)
	time.sleep(10)
	if msg.is_published() == False:
		print("Message is not yet pushed")
	else:
		print("Message pushed")
