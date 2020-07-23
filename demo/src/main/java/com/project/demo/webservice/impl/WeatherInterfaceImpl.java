package com.project.demo.webservice.impl;

import com.project.demo.webservice.WeatherInterface;

import javax.jws.WebService;

@WebService
public class WeatherInterfaceImpl implements WeatherInterface {
    @Override
    public String queryWeather(String cityName) {
        System.out.println("获取城市名" + cityName);
        String weather = "暴雨";
        return weather;
    }
}
