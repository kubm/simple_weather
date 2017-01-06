package com.example.kubacm.weatherapp;


import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kubacm.weatherapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowMeWeather extends Fragment {
    Typeface weatherFont;

    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;

    Handler handler;

    public ShowMeWeather() {
        // Required empty public constructor
        handler = new Handler();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_show_me_weather, container, false);
        //cityField = (TextView)rootView.findViewById(R.id.city_field);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/weather.ttf");

        renderWeather();
    }

    public void renderWeather(){
        try{
            cityField.setText("DUPA!@!!!");
        }catch (Exception e){
            Log.e("SimplExampl","asedhasdhsh");
        }
    }
}
