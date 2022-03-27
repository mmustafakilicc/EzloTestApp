package com.ezlo.ezlotestapp.di.module.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@SuppressWarnings("unchecked")
@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelCreator;

    @Inject
    public ViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> viewModelCreator) {
        this.viewModelCreator = viewModelCreator;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<? extends ViewModel> provider = viewModelCreator.get(modelClass);
        if (provider == null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : viewModelCreator.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    provider = entry.getValue();
                    break;
                }
            }
        }

        if (provider == null) {
            throw new IllegalArgumentException("What is this model " + modelClass);
        }

        try {
            return (T) provider.get();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
