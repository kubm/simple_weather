package com.example.kubacm.weatherapp;

import android.content.Context;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by kubacm on 2017-01-05.
 */

public interface MainView {
    void showProgress();
    void hideProgress();
    void setItems(List<String> items);
    void showMessage(String message);
    Context getContext();
    void showWeather(JSONObject data);

}
