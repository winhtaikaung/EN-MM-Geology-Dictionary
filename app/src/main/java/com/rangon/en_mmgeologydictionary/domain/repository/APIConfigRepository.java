package com.rangon.en_mmgeologydictionary.domain.repository;

import com.rangon.en_mmgeologydictionary.model.configrestmodels.ApiConfig;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by win on 9/16/17.
 */

public interface APIConfigRepository {

    Observable<List<ApiConfig>> getAPIConfigList(int page, int limit);
}
