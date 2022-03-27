package com.ezlo.ezlotestapp.di.component;

import com.ezlo.ezlotestapp.data.remote.ApiClient;
import com.ezlo.ezlotestapp.di.module.viewmodel.ViewModelModule;
import com.ezlo.ezlotestapp.ui.device.DeviceDetailFragment;
import com.ezlo.ezlotestapp.ui.device.DeviceListFragment;
import com.ezlo.ezlotestapp.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewModelModule.class, ApiClient.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(DeviceListFragment deviceListFragment);

    void inject(DeviceDetailFragment deviceDetailFragment);
}
