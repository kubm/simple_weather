package com.example.kubacm.weatherapp.main;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kubacm.weatherapp.CityList;
import com.example.kubacm.weatherapp.R;
import com.example.kubacm.weatherapp.WeatherFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, AdapterView.OnItemClickListener {

    private ListView listView;
    private ProgressBar progressBar;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        presenter = new MainPresenterImpl(this, new FindItemsInteractorImpl());
        List<String>Cities = createItemList();
        setItems(Cities);
        };
    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override protected void onDestroy(){
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override public void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
    }

    @Override public void hideProgress(){
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override public void setItems(List<String>items){
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items));
    }

    @Override public void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        presenter.onItemClicked(position);
    }

    public List<String> createItemList(){
        return Arrays.asList(
                "Lodz",
                "Warsaw",
                "Poznan"
        );
    }


    }

