package com.example.kubacm.weatherapp;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kubacm on 2017-01-05.
 */

public class FindItemsInteractorImpl implements FindItemsInteractor {
    JSONObject data = new JSONObject();
    @Override
    public void findItems(final OnFinishedListener listener, final String city, final Context context){
        try {
            listener.onFinished(getJSON(context, city));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static final String OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";

    public JSONObject getJSON(final Context context, final String city) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL(String.format(OPEN_WEATHER_MAP_API,city));
                    //establishing connection
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    //adding openWeather api key to request
                    connection.addRequestProperty("x-api-key",context.getString(R.string.open_weather_maps_app_id));

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer json = new StringBuffer(1024);
                    String tmp = "";
                    while((tmp=reader.readLine())!=null)
                        json.append(tmp).append("\n");
                    reader.close();

                    JSONObject data1 = new JSONObject(json.toString());
                    data = data1;
                    //This data will be 404 if the request was not successful
                    if (data1.getInt("cod")!=200){
                        data1 = null;
                        data = data1;
                    }
                    //return data;
                } catch (Exception e){
                    data = null;
                }
            }
        });
        t.start();
        t.join();
        return data;
    }
}
