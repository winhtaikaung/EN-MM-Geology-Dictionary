package com.rangon.en_mmgeologydictionary.data.repository.datasource;

import com.rangon.en_mmgeologydictionary.model.configrestmodels.ApiConfig;
import com.rangon.en_mmgeologydictionary.services.ApiConfigService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
                .map(configResponse -> {
                    List<ApiConfig> configList = new ArrayList<ApiConfig>();
                    if (configResponse != null) {
                        configList = configResponse.getData().getConfig();
                    }
                    return configList;
                }).doOnNext(apiConfigs -> {

                });
    }
}
