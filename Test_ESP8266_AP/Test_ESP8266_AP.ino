/**
  @file    Test_ESP8266_AP.ino
  @brief   Test AP (Access Point) mode using WebServer with ESP8266 module.
  @author  LACOUR Vincent
  @date    2018-01
*/

#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>

// SSID parameters
const char *ssid = "Wifi_Car_ESP8266";
const char *password = "wificarpassword";

IPAddress ip(192, 168, 4, 1); // IP Address
IPAddress netmask(255, 255, 255, 0); // Netmask
const int port = 80; // Port
ESP8266WebServer server = ESP8266WebServer(port);

void setup() {
  Serial.begin(115200);
  // Setup AP
  WiFi.mode(WIFI_AP); //Only Access point
  WiFi.softAPConfig(ip, ip, netmask);
  WiFi.softAP(ssid, password);

  // Get and print IP Address
  IPAddress myIP = WiFi.softAPIP();
  Serial.print("AP IP address: ");
  Serial.println(myIP);

  server.on("/", HTTP_GET, handleRoot);
  server.begin();
}

void loop() {
  server.handleClient();
}

void handleRoot() {
  if (server.hasArg("State")) {
    String value = server.arg("State");
    Serial.println("Value = " + value);
    server.send(200, "text / plain", "Request received");
  }
}


