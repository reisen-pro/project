package com.project.demo.webservice;

import com.project.demo.webservice.impl.WeatherInterfaceImpl;

import javax.xml.ws.Endpoint;

public class WeatherServer {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:12345/weather", new WeatherInterfaceImpl());
    }
}
