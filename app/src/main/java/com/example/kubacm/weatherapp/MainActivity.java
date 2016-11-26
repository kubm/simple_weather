package com.example.kubacm.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CityList.OnClickedItemListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v = this.getWindow().getDecorView();
        v.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.white,null));

        CityList city_list = new CityList();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_main,city_list).addToBackStack(null).commit();


        };
    public void Clicked(String search_name){
        Toast.makeText(this,search_name+" selected",Toast.LENGTH_LONG).show();
        Bundle bundle = new Bundle();
        bundle.putString("search_name",search_name);
        WeatherFragment wf = new WeatherFragment();
        wf.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,wf).addToBackStack(null).commit();
        //WeatherFragment wf = (WeatherFragment)getSupportFragmentManager().findFragmentById(R.id.activity_main);
        //wf.changeCity(search_name);
        //getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, new WeatherFragment()).addToBackStack(null).commit();

    }


    }

