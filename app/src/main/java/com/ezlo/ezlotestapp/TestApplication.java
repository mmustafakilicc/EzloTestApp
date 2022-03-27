package com.ezlo.ezlotestapp;

import android.app.Application;

import com.ezlo.ezlotestapp.di.component.AppComponent;
import com.ezlo.ezlotestapp.di.component.DaggerAppComponent;

public class TestApplication extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()//initialize dagger component
                .build();
    }
}
