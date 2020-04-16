#!/usr/bin/env python
from gpiozero import LED
import time
import RPi.GPIO as GPIO

#1 | 17 = livingroom
#2 | 27 = garage
#3 | 22 = cellar
#4 | 26 = kitchen

is_on = [False, False, False, False]
rooms = ['Livingroom','Garage','Cellar','Kitchen']

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(17,GPIO.OUT)
GPIO.setup(27,GPIO.OUT)
GPIO.setup(22,GPIO.OUT)
GPIO.setup(26,GPIO.OUT)


def translator(arg):
    if arg == "False":
        return False
    elif arg == "True":
        return True

def switch(room, action):
    index = rooms.index(room)
    is_on[index] = translator(action) #From String to Boolean
    print is_on
    print rooms





#GPIO.setup(17,GPIO.OUT)
#    
#    print "Garage light ON"
#    GPIO.output(17,GPIO.HIGH)
    
#    time.sleep(1)
    
#    print "Garage light OFF"
#    GPIO.output(17,GPIO.LOW)
