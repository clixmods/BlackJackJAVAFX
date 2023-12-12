package com.example.blackjackjavafx.Application.helper;

import com.example.blackjackjavafx.Application.lib.request.Request;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

//example https://www.weather-forecast.com/locations/ac_location_name?query=jacou
public class CityChecker extends Request {
    @Override
    protected URL url() throws MalformedURLException {

        return new URL("https://www.weather-forecast.com/locations/ac_location_name");
    }

    public boolean IsValidCity(String city)
    {
        Map<String, String> cityData = Map.of("query", city);
        String response = Request("GET",cityData);

        return response.toLowerCase().contains(city.toLowerCase());
    }
}
