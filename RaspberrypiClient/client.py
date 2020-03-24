#!/usr/bin/env python
import paho.mqtt.client as paho
import time
import os

def on_connect(client, userdata, flags, rc):
	os.system("clear")
	print("CONNACK received with code %d." % (rc))

def on_publish(client, userdata, mid):
	print("mid: "+str(mid))

def on_subscribe(client, userdata, mid, granted_qos):
    print("Subscribed: "+str(mid)+" "+str(granted_qos))

def on_message(client, userdata, msg):
	print(msg.topic+": "+str(msg.payload))
	if msg.payload == "Turn light on":
		print("RaspberryPi: Turned light on!")
	elif msg.payload == "Turn light off":
		print("RaspberryPi: Turned light off!")

client = paho.Client(client_id="RaspberryPi")
client.on_connect = on_connect
client.on_publish = on_publish
client.on_subscribe = on_subscribe
client.on_message = on_message
client.connect("169.254.219.0",1883)
client.subscribe("#",qos=1)
client.loop_start()

while True:
#	msg  = client.publish("SmartHome/Light","HelloWorld",qos=1)
	time.sleep(10)
#	if msg.is_published() == False:
#		print("Message is not yet pushed")
#	else:
#		print("Message pushed")

