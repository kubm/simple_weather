package com.example.kubacm.weatherapp;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by kubacm on 2016-11-21.
 */

public class CityPreference {

    SharedPreferences prefs;

    public CityPreference(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    //If the user has not chosen a city yet, return Sydney as the default city
    String getCity(){
        return prefs.getString("city","Lodz");
    }

    void setCity(String city){
        prefs.edit().putString("city", city).commit();
    }
}
