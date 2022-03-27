package com.ezlo.ezlotestapp.data.remote.repository;

import com.ezlo.ezlotestapp.data.model.api.DeviceListResponse;
import com.ezlo.ezlotestapp.data.model.api.DeviceNetworkEntity;
import com.ezlo.ezlotestapp.data.remote.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class DeviceRepository {

    private final ApiService apiService;

    @Inject
    public DeviceRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<DeviceListResponse> getDeviceList() {
        return apiService.getDeviceList();
    }
}
