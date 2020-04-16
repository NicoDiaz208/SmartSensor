from gpiozero import LED
import time
import threading

class LEDplus():
    def __init__(self,pinnumber):
        self.led = LED(pinnumber)
        self.__loop = True
        self.__threading = threading.Thread(target=self.__blink)


    def on(self,):
        self.__loop = False
        self.maybejoin()
        self.led.on()

    def off(self, ):
        self.__loop = False
        self.maybejoin()
        self.led.off()

    def maybejoin(self,):
        if self.__threading.isAlive():
            self.__threading.join()

    def blink(self, pitch):
        self.__threading = threading.Thread(target=self.__blink, args=(pitch, ))
        self.__threading.start()

    def __blink(self, pitch=.25):
        self.__loop = True
        while self.__loop:
            self.led.toggle()
            time.sleep(pitch/2)
        self.led.off()

green = LEDplus(22)
green.on()
