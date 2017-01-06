package com.example.kubacm.weatherapp;

import android.content.Context;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by kubacm on 2017-01-05.
 */

public interface FindItemsInteractor {

    interface OnFinishedListener {
        void onFinished(JSONObject weather);
    }

    void findItems(OnFinishedListener listener, String city, Context context);
}
