/**
  @file    Test_ESP8266_STA.ino
  @brief   Test STA (Station) mode using WebServer with ESP8266 module.
  @author  LACOUR Vincent
  @date    2018-01
*/

#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>

// SSID parameters
const char* ssid = "YOUR_SSID";
const char* password = "YOUR_PASSWORD";

const int port = 80; // Port
ESP8266WebServer server(port);

void setup() {
  Serial.begin(115200);
  pinMode(LED_BUILTIN, OUTPUT);
  // Setup STA
  Serial.print("Connecting to : ");
  Serial.println(ssid);

  WiFi.persistent(false);
  WiFi.mode(WIFI_OFF);
  WiFi.mode(WIFI_STA);
  WiFi.disconnect();
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    pinMode(LED_BUILTIN, LOW);
    delay(250);
    pinMode(LED_BUILTIN, HIGH);
    delay(250);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("Connection established!");
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());

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


