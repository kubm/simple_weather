package com.example.kubacm.weatherapp;

import org.json.JSONObject;

/**
 * Created by kubacm on 2017-01-05.
 */

public class MainPresenterImpl implements MainPresenter, FindItemsInteractor.OnFinishedListener {
    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;

    public MainPresenterImpl (MainView mainView, FindItemsInteractor findItemsInteractor){
        this.mainView = mainView;
        this.findItemsInteractor = findItemsInteractor;

    }

    @Override
    public void onResume(){
//        if (mainView !=null){
//            mainView.showProgress();
//        }
//
//        findItemsInteractor.findItems(this);
    }

    @Override
    public void onItemClicked(int position, String name) {
        if (mainView !=null){
            mainView.showProgress();
            mainView.showMessage(String.format("Position %d clicked, name: %s",position +1, name));
        }

            findItemsInteractor.findItems(this, name, mainView.getContext());


    }

    @Override
    public void onDestroy() {mainView = null;}

    @Override
    public void onFinished(JSONObject data){
        if (data==null){
            mainView.showMessage("Dzejson not found");
        }else{
            try{
                String name = data.getString("name");
                mainView.showMessage("Dzejson found: "+name);

                mainView.hideProgress();
                mainView.showWeather(data);
            }catch (Exception e){

            }
        }
//        if (mainView !=null){
//            mainView.setItems(items);
//            mainView.hideProgress();
//        }
    }
    public MainView getMainView(){return mainView; }
}
