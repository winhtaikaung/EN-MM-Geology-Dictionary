package com.rangon.en_mmgeologydictionary.data.repository.datasource;

/**
 * Created by win on 9/16/17.
 */

public class ApiConfigDataStoreFactory {

    public ApiConfigDataStoreFactory() {

    }

    public ApiConfigDataStore create() {
        return new ApiConfigCloudDataStore();
    }
}
