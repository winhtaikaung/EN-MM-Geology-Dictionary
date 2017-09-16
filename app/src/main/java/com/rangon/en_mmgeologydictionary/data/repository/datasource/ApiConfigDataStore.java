package com.rangon.en_mmgeologydictionary.data.repository.datasource;

import com.rangon.en_mmgeologydictionary.model.configrestmodels.ApiConfig;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by win on 9/16/17.
 */

public interface ApiConfigDataStore {

    Observable<List<ApiConfig>> getApiConfigList(int page, int limit);
}
