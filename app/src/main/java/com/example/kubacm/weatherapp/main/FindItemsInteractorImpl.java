package com.example.kubacm.weatherapp.main;

import android.os.Handler;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kubacm on 2017-01-05.
 */

public class FindItemsInteractorImpl implements FindItemsInteractor {
    @Override
    public void findItems(final OnFinishedListener listener){
        listener.onFinished(createArrayList());
    }

    private List<String> createArrayList(){
        return Arrays.asList(
                "1",
                "2",
                "3"
        );
    }
}
