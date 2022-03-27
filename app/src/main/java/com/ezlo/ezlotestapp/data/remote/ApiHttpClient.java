package com.ezlo.ezlotestapp.data.remote;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

public class ApiHttpClient {

    @Inject
    public ApiHttpClient(){}

    public OkHttpClient getClient(ApiInterceptor myInterceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(myInterceptor);
        return builder.build();
    }
}
