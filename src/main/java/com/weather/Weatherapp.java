package com.weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Weatherapp {
    public static void main(String[] args) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=12.97&longitude=77.59&current_weather=true";

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();

            String json = response.body().string();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);
            JsonNode currentWeather = node.get("current_weather");

            double temperature = currentWeather.get("temperature").asDouble();
            double windspeed = currentWeather.get("windspeed").asDouble();
            String time = currentWeather.get("time").asText();

            System.out.println(" Current Weather (Bangalore):");
            System.out.println(" Time: " + time);
            System.out.println("️ Temperature: " + temperature + "°C");
            System.out.println(" Windspeed: " + windspeed + " km/h");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

