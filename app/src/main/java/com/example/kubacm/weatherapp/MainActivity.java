package com.example.kubacm.weatherapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView, AdapterView.OnItemClickListener {

    private ListView listView;
    private ProgressBar progressBar;
    private MainPresenter presenter;
    @Override
    public Context getContext(){return this;}

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
        //listView.setVisibility(View.VISIBLE);
    }

    @Override public void setItems(List<String>items){
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items));
    }

    @Override public void showMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Object obj  = listView.getAdapter().getItem(position);
        String name = obj.toString();
        presenter.onItemClicked(position, name);
    }
    @Override public void showWeather(JSONObject data){
        Bundle bundle = new Bundle();
        bundle.putString("data",data.toString());
        ShowMeWeather wf = new ShowMeWeather();
        wf.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,wf).addToBackStack(null).commit();
    }

    public List<String> createItemList(){
        return Arrays.asList(
                "Lodz",
                "Warsaw",
                "Poznan"
        );
    }


    }

