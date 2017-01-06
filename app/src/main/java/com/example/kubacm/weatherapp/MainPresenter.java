package com.example.kubacm.weatherapp;

/**
 * Created by kubacm on 2017-01-05.
 */

public interface MainPresenter {

    void onResume();

    void onItemClicked(int position, String name);

    void onDestroy();

}
