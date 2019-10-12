# Wifi car with ESP8266

<p align="center">
<img src="img/wifi_car.png" width="300" >
</p>


## Synopsis

There are a lot of RC car projects using a smartphone to control the car. Most use the bluetooth protocol. Thanks to the ESP8266 chip, reliable and cheap, it is now possible to build a RC car for about $30.
The steps are:
* build the wifi car
* develop the HTTP server with motor control
* develop the mobile application

## How it works

We are using HTTP protocol.

The server is the ESP8266 and the client is the smartphone. The client will send HTTP requests to the server in order to control the car.

ESP8266 chip is able to create its own WIFI network (Access Point mode = AP) or to connect to an existing wifi network (Station Mode = STA). It is also possible to deploy a HTTP server.

To make it simple, the smartphone must be connected to the same wifi network as the chip and know its ip address and the port of its server.

The user will interact with the buttons of the mobile application to control the car.


## Build your own wifi car

The first step is to build your wifi car. Basically, you have to assemble the chassis with 2 motors, connect the controller and the motor controller, add battery.

This is the most interesting part because you can customize your car by adding new features. In my case, I have replaced the chassis by a tank chassis.

<p align="center">
<img src="img/car_kit.png" width="200"  >
</p>

You can find a complete kit the build a wifi car at this [link](https://www.banggood.com/Geekcrei-2WD-L293D-WIFI-Smart-Robot-Car-With-NodeMCU-Shield-Kit-For-ESP-12E-Based-On-ESP8266-p-995166.html).

## Upload the code into your controller

To complete ... 

## Configure your mobile application

To complete ... 

## Preview

You can find the result my project at this [link](https://www.youtube.com/watch?v=E-RyAsFMnTI)




