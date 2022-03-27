package com.ezlo.ezlotestapp.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    Application application;

    public ApplicationModule(Application application){
        this.application = application;
    }

    @Singleton
    @Provides
    Application provide(){
        return application;
    }
}
