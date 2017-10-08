package com.rangon.en_mmgeologydictionary.data.repository.datasource;

import android.util.Log;

import com.rangon.en_mmgeologydictionary.model.configrestmodels.ApiConfig;
import com.rangon.en_mmgeologydictionary.services.ApiConfigService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.rangon.en_mmgeologydictionary.network.RestClient.getRetrofit;

/**
 * Created by win on 9/9/17.
 */

public class ApiConfigCloudDataStore implements ApiConfigDataStore {

    @Override
    public Observable<List<ApiConfig>> getApiConfigList(int page, int limit) {
        return getRetrofit().create(ApiConfigService.class).getApiConfig(page, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("ERROR", throwable.getMessage());
                    }
                })
                .map(configResponse -> {
                    List<ApiConfig> configList = new ArrayList<ApiConfig>();
                    if (configResponse != null) {
                        configList = configResponse.getData().getConfig();
                    }
                    return configList;
                }).doOnNext(new Consumer<List<ApiConfig>>() {
                    @Override
                    public void accept(List<ApiConfig> apiConfigs) throws Exception {
                        Log.e("ON Next", String.valueOf(apiConfigs.size()));
                    }
                });
    }
}
