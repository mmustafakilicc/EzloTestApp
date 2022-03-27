package com.ezlo.ezlotestapp.di.module.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ezlo.ezlotestapp.ui.device.DeviceViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(DeviceViewModel.class)
    protected abstract ViewModel deviceViewModel(DeviceViewModel deviceViewModel);
}
