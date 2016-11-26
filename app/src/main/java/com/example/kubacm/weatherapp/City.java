package com.example.kubacm.weatherapp;

/**
 * Created by KUBACM on 2016-11-19.
 */

public class City {
    private String name;
    private String search_name;
    private String picture;

    public City(){
        super();
    }

    public City(String name, String search_name, String picture){
        super();
        this.name=name;
        this.search_name = search_name;
        this.picture=picture;
    }

    public String getName(){return name;}
    public String getSearch_name(){return search_name;}
    public String getPicture(){return picture;};
}
