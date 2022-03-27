package com.ezlo.ezlotestapp.data.remote;


import com.ezlo.ezlotestapp.data.model.api.DeviceListResponse;
import com.ezlo.ezlotestapp.data.model.api.DeviceNetworkEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET(ApiEndPoint.ENDPOINT_DEVICE_LIST)
    Observable<DeviceListResponse> getDeviceList();
}
