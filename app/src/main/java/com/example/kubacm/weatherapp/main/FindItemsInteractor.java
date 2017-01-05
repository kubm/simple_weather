package com.example.kubacm.weatherapp.main;

import java.util.List;

/**
 * Created by kubacm on 2017-01-05.
 */

public interface FindItemsInteractor {

    interface OnFinishedListener {
        void onFinished(List<String> items);
    }

    void findItems(OnFinishedListener listener);
}
