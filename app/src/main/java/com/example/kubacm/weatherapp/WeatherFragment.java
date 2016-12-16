package com.example.kubacm.weatherapp;


import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {
    Typeface weatherFont;

    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;
    ProgressDialog prg;

    Handler handler;

    public WeatherFragment() {
        // Required empty public constructor
        handler = new Handler();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        cityField = (TextView)rootView.findViewById(R.id.city_field);
        updatedField = (TextView) rootView.findViewById(R.id.updated_field);
        detailsField = (TextView) rootView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView) rootView.findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView) rootView.findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);
        prg = ProgressDialog.show(getActivity(), "Connecting to database", "Please wait a little bit", true);
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        String strtext = getArguments().getString("search_name");
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(),"fonts/weather.ttf");
        updateWeatherData(strtext);
    }

    private void updateWeatherData(final String city){
        new Thread(){

            public void run(){
                final JSONObject json = RemoteFetch.getJSON(getActivity(),city);
                if (json==null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            prg.dismiss();
                            Toast.makeText(getActivity(),getActivity().getString(R.string.place_not_found),Toast.LENGTH_LONG).show();
                        }
                    });
                } else{
                  handler.post(new Runnable() {
                      @Override
                      public void run() {
                          prg.dismiss();
                          renderWeather(json);
                      }
                  })  ;
                }
            }
        }.start();
    }

    private void renderWeather(JSONObject json){
        try{
            cityField.setText(json.getString("name").toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country"));

            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            detailsField.setText(details.getString("description").toUpperCase(Locale.US) + "\n" + "Humidity: "+ main.getString("humidity") + "%" + "\n" + "Pressure: " + main.getString("pressure") + "hPa");

            currentTemperatureField.setText(String.format(Locale.US,"%.2f", main.getDouble("temp"))+ " °C");

            DateFormat df = DateFormat.getDateInstance();
            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
            updatedField.setText("Last update: "+updatedOn);

            setWeatherIcon(details.getInt("id"),json.getJSONObject("sys").getLong("sunrise")*1000,json.getJSONObject("sys").getLong("sunset") * 1000);
        }catch(Exception e){
            Log.e("SimpleWeather","One or more fields not found in the JSON data");
        }
    }

    private void setWeatherIcon(int actualID, long sunrise, long sunset){
        int id = actualID / 100;
        String icon = "";
        int color= ResourcesCompat.getColor(getResources(),R.color.clear,null);
        int t_color= ResourcesCompat.getColor(getResources(),R.color.white,null);

        if (actualID == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset){
                icon = getActivity().getString(R.string.weather_sunny);
                color= ResourcesCompat.getColor(getResources(),R.color.clear,null);
                t_color = ResourcesCompat.getColor(getResources(),R.color.white,null);
            }else{
                icon = getActivity().getString(R.string.weather_clear_night);
                color= ResourcesCompat.getColor(getResources(),R.color.night,null);
                t_color = ResourcesCompat.getColor(getResources(),R.color.white,null);
            }
        } else{
            switch(id){
                case 2: {icon = getActivity().getString(R.string.weather_thunder); color= ResourcesCompat.getColor(getResources(),R.color.thunder,null); t_color = ResourcesCompat.getColor(getResources(),R.color.white,null);}
                    break;
                case 3: {icon = getActivity().getString(R.string.weather_drizzle); color= ResourcesCompat.getColor(getResources(),R.color.rain,null); t_color = ResourcesCompat.getColor(getResources(),R.color.black,null);}
                    break;
                case 7: {icon = getActivity().getString(R.string.weather_foggy); color= ResourcesCompat.getColor(getResources(),R.color.foggy,null); t_color = ResourcesCompat.getColor(getResources(),R.color.black,null);}
                    break;
                case 8: {icon = getActivity().getString(R.string.weather_cloudy);color= ResourcesCompat.getColor(getResources(),R.color.rain,null); t_color = ResourcesCompat.getColor(getResources(),R.color.black,null);}
                    break;
                case 6: {icon = getActivity().getString(R.string.weather_snowy);color= ResourcesCompat.getColor(getResources(),R.color.clear,null); t_color = ResourcesCompat.getColor(getResources(),R.color.white,null);}
                    break;
                case 5: {icon = getActivity().getString(R.string.weather_rainy);color= ResourcesCompat.getColor(getResources(),R.color.rain,null); t_color = ResourcesCompat.getColor(getResources(),R.color.black,null);}
                    break;
            }
        }
        weatherIcon.setText(icon);

        cityField.setTextColor(t_color);
        updatedField.setTextColor(t_color);
        detailsField.setTextColor(t_color);
        currentTemperatureField.setTextColor(t_color);
        weatherIcon.setTextColor(t_color);
        View v = getActivity().getWindow().getDecorView();
        v.setBackgroundColor(color);
    }

    public void changeCity(String city){
        updateWeatherData(city);
    }
}
