//Arduino
#include <Wire.h>
#include <SPI.h>
#include "Adafruit_BME680.h"
#include <Adafruit_Sensor.h>
//HiveMQ
#include <Ethernet.h>
#include <PubSubClient.h>
 
//Steckplaetze
#define BME_SCK 13
#define BME_MISO 12
#define BME_MOSI 11
#define BME_CS 10
//convert (c++)
#include <string>

//bme680
Adafruit_BME680 bme; // I2C Schnittstelle

//HiveMq

byte mac[]    = {  0xDE, 0xED, 0xBA, 0xFE, 0xFE, 0xED };
IPAddress ip(172, 16, 0, 100);
const char* server = "broker.example.com";

EthernetClient ethClient;
PubSubClient mqttClient(ethClient);

void setup() {
  //bme680
  Serial.begin(9600);
  while (!Serial);
  Serial.println("BME680 Sensor");
  if (!bme.begin()) {
    Serial.println("Der Sensor ist nicht richtig angeschlossen oder wurde nicht gefunden!");
    while (1);
  }
  // Einrichten Filterinitialisierung laut Dokumentation
  bme.setTemperatureOversampling(BME680_OS_8X);
  bme.setHumidityOversampling(BME680_OS_2X);
  bme.setPressureOversampling(BME680_OS_4X);
  bme.setIIRFilterSize(BME680_FILTER_SIZE_3);
  bme.setGasHeater(320, 150);
  
  //HiveMq
   Ethernet.begin(mac, ip);
  // Allow the hardware to sort itself out
  delay(1500);
  mqttClient.setServer(server, 1883);

    if (mqttClient.connect("bme680")) {
    // connection succeeded
  } else {
    // connection failed
    // mqttClient.state() will provide more information
    // on why it failed.
    mqttClient.state()
  }
}

void loop() {
 // mqttClient.loop();
  if (! bme.performReading()) {
    Serial.println("Fehler beim Lesen überprüfe den Sensor!!!");
    return;
  }
  Serial.print("Temperatur = ");
  Serial.print(bme.temperature);
  Serial.println(" *C");
  boolean rc = mqttClient.publish("temperatur", to_string(bme.temperature) );
  if(rc == false)
  {
    error();
  }
  Serial.print("Luftdruck = ");
  Serial.print(bme.pressure / 100.0);
  Serial.println(" hPa");
  rc = mqttClient.publish("pressure", to_string((bme.pressure/100)));
  if(rc == false)
  {
    error();
  }
  Serial.print("Luftfeuchtigkeit = ");
  Serial.print(bme.humidity);
  Serial.println(" %");
  rc = mqttClient.publish("humidity", to_string(bme.humidity) );
  if(rc == false)
  {
    error();
  }
  Serial.print("Gas = ");
  Serial.print(bme.gas_resistance / 1000.0);
  Serial.println(" KOhms");
  rc = mqttClient.publish("gasatur", to_string((bme.gas_resistance/1000.0)));
  if(rc == false)
  {
    error();
  }
  Serial.println();
  delay(10000);//10sec
}
void error()
{
  Serial.print("Es gab einen Uebertragungsfehler!");
}
