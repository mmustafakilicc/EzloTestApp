package com.ezlo.ezlotestapp.data.remote;

import com.ezlo.ezlotestapp.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiClient {

    @Singleton
    @Provides
    public ApiService getApiService(ApiHttpClient apiHttpClient, ApiInterceptor apiInterceptor) {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(apiHttpClient.getClient(apiInterceptor))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().create(ApiService.class);
    }
}
