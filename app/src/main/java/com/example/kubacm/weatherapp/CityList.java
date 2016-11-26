package com.example.kubacm.weatherapp;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CityList extends ListFragment {
    private ArrayList<City> citiesArrayList = new ArrayList<>();
    OnClickedItemListener mCallback;


    public interface OnClickedItemListener{
        public void Clicked(String search_name);
    }
    public CityList() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        citiesArrayList.add(new City("Łódź","Lodz","city_image_1"));
        citiesArrayList.add(new City("Warszawa","Warsaw","city_image_2"));
        citiesArrayList.add(new City("Kraków","Krakow","city_image_3"));
        citiesArrayList.add(new City("Wrocław","Wroclaw","city_image_4"));
        try{
            mCallback=(OnClickedItemListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+ "must implement OnClickedItemListener");
        }

    }
    @Override
    public void onResume(){
        super.onResume();
        View v = getActivity().getWindow().getDecorView();
        v.setBackgroundColor(ResourcesCompat.getColor(getResources(),R.color.white,null));
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);



        ArrayAdapter<City> adapter = new cityArrayAdapter(getActivity(),android.R.layout.list_content,citiesArrayList);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        City item = (City) getListAdapter().getItem(position);
        String name_local = item.getSearch_name();
        mCallback.Clicked(name_local);
//        Toast.makeText(getActivity(),name_local+" selected",Toast.LENGTH_LONG).show();
    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_city_list, container, false);
//
//        citiesArrayList.add(new City("Łódź","Lodz","city_image_1"));
//        citiesArrayList.add(new City("Warszawa","Warsaw","city_image_2"));
//        citiesArrayList.add(new City("Kraków","Cracow","city_image_3"));
//        //create new array adapter
//        ArrayAdapter<City> adapter = new cityArrayAdapter(getActivity(),0,citiesArrayList);
//
//        ListView listView = (ListView) rootView.findViewById(R.id.customListView);
//        listView.setAdapter(adapter);
//
//        // Inflate the layout for this fragment
//        return rootView;
//    }

}
//custom ArrayAdapter
class cityArrayAdapter extends ArrayAdapter<City>{


    private Context context;
    private java.util.List<City> citiesList;

    //constructor
    public cityArrayAdapter(Context context, int resource, ArrayList<City> objects){
        super(context,resource,objects);

        this.context=context;
        this.citiesList=objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        City city = citiesList.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.city_layout, null);

        TextView city_name = (TextView) view.findViewById(R.id.city_name);
        TextView search_name = (TextView) view.findViewById(R.id.search_name);
        ImageView image = (ImageView) view.findViewById(R.id.image);

        //Set name and search name
        String cityName = city.getName();
        city_name.setText(cityName);

        String citySearchName = city.getSearch_name();
        search_name.setText(citySearchName);

        //get image associated with this city
        int imageID = context.getResources().getIdentifier(city.getPicture(),"drawable",context.getPackageName());
        image.setImageResource(imageID);

        return view;
    }

}