/***************************************************************************
  This is a library for the BME680 gas, humidity, temperature & pressure sensor

  Designed specifically to work with the Adafruit BME680 Breakout
  ----> http://www.adafruit.com/products/3660

  These sensors use I2C or SPI to communicate, 2 or 4 pins are required
  to interface.

  Adafruit invests time and resources providing this open source code,
  please support Adafruit and open-source hardware by purchasing products
  from Adafruit!

  Written by Limor Fried & Kevin Townsend for Adafruit Industries.
  BSD license, all text above must be included in any redistribution
 ***************************************************************************/

#include <Wire.h>
#include <SPI.h>
#include <Adafruit_Sensor.h>
#include "Adafruit_BME680.h"
#include <stdio.h>
#define BME_SCK 13
#define BME_MISO 12
#define BME_MOSI 11
#define BME_CS 10

#define SEALEVELPRESSURE_HPA (1013.25)

Adafruit_BME680 bme; // I2C
//Adafruit_BME680 bme(BME_CS); // hardware SPI
//Adafruit_BME680 bme(BME_CS, BME_MOSI, BME_MISO,  BME_SCK);


// PubNub MQTT example using ESP32.
#include <WiFi.h>
#include <PubSubClient.h>
// Connection info.
const char* ssid = "Spackos Forever";
const char* password =  "EntenHabenRechte123";
const char* mqttServer = "broker.hivemq.com";
const int mqttPort = 1883;
const char* clientID = "Patrick's Client";
const char* topicTemp = "Htl-Leonding2020NVS/SmartHome/Livingroom/Temperatur";
const char* topicHum = "Htl-Leonding2020NVS/SmartHome/Livingroom/Humidity";
const char* topicPress = "Htl-Leonding2020NVS/SmartHome/Livingroom/AirPressure";
const char* topicGas = "Htl-Leonding2020NVS/SmartHome/Livingroom/Gas";
WiFiClient MQTTclient;
PubSubClient client(MQTTclient);
void callback(char* topic, byte* payload, unsigned int length) {
  String payload_buff;
  for (int i = 0; i < length; i++) {
    payload_buff = payload_buff + String((char)payload[i]);
  }
  Serial.println(payload_buff); // Print out messages.
}
long lastReconnectAttempt = 0;
boolean reconnect() {
  if (client.connect(clientID)) {
    client.subscribe(topicTemp); // Subscribe to channel.
  }
  return client.connected();
}





void setup() {
  Serial.begin(9600);
  while (!Serial);
  Serial.println(F("BME680 test"));

  if (!bme.begin()) {
    Serial.println("Could not find a valid BME680 sensor, check wiring!");
    while (1);
  }

  // Set up oversampling and filter initialization
  bme.setTemperatureOversampling(BME680_OS_8X);
  bme.setHumidityOversampling(BME680_OS_2X);
  bme.setPressureOversampling(BME680_OS_4X);
  bme.setIIRFilterSize(BME680_FILTER_SIZE_3);
  bme.setGasHeater(320, 150); // 320*C for 150 ms


  //client

  Serial.begin(9600);
  Serial.println("Attempting to connect...");
  WiFi.begin(ssid, password); // Connect to WiFi.
  if (WiFi.waitForConnectResult() != WL_CONNECTED) {
    Serial.println("Couldn't connect to WiFi.");
    while (1) delay(100);
  }
  client.setServer(mqttServer, mqttPort); // Connect to PubNub.
  client.setCallback(callback);
  lastReconnectAttempt = 0;
}

void loop() {
  if (! bme.performReading()) {
    Serial.println("Failed to perform reading :(");
    return;
  }

  //bme680 is connected


  if (!client.connected()) {
    long now = millis();
    if (now - lastReconnectAttempt > 5000) { // Try to reconnect.
      lastReconnectAttempt = now;
      if (reconnect()) { // Attempt to reconnect.
        lastReconnectAttempt = 0;
      }
    }
  } else { // Connected.
    client.loop();
    
    char str[12];
    //Convert Float to string
    gcvt(bme.temperature, 6, str);
   // Serial.print("Temperature = %s *C",str);
    client.publish(topicTemp, str); // Publish message.
 
     //Convert Float to string
 
    gcvt((bme.pressure / 100.0), 6, str);
  //  Serial.print("Pressure = %s hPa",str);
    client.publish(topicPress, str); // Publish message.

 

       //Convert Float to string
    gcvt(bme.humidity, 6, str);
 //   Serial.print("Humidity = %s %",str);
    client.publish(topicHum, str); // Publish message.

       //Convert Float to string
    gcvt((bme.gas_resistance / 1000.0), 6, str);
 //   Serial.print("Gas: = %s KOhms",str);
    client.publish(topicGas, str); // Publish message.
    delay(1000);

  Serial.print("Approx. Altitude = ");
  Serial.print(bme.readAltitude(SEALEVELPRESSURE_HPA));
  Serial.println(" m");
  }
}
