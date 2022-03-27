package com.ezlo.ezlotestapp.data.remote;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {

    @Inject
    public ApiInterceptor() {
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Content-type", "application/json")
                .build();
        return chain.proceed(request);
    }
}
