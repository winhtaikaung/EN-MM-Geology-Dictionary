package com.rangon.en_mmgeologydictionary.data.repository;

import com.rangon.en_mmgeologydictionary.data.repository.datasource.ApiConfigDataStoreFactory;
import com.rangon.en_mmgeologydictionary.domain.repository.APIConfigRepository;
import com.rangon.en_mmgeologydictionary.model.configrestmodels.ApiConfig;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by win on 9/16/17.
 */

public class ApiConfigDataRepository implements APIConfigRepository {

    private final ApiConfigDataStoreFactory mApiConfigDataStoreFactory;


    public ApiConfigDataRepository(ApiConfigDataStoreFactory apiConfigDataStoreFactory) {
        this.mApiConfigDataStoreFactory = apiConfigDataStoreFactory;
    }


    @Override
    public Observable<List<ApiConfig>> getAPIConfigList(int page, int limit) {

        return mApiConfigDataStoreFactory.create().getApiConfigList(page, limit);
    }
}
