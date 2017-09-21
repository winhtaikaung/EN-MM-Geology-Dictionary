package com.rangon.en_mmgeologydictionary.data.repository.datasource;

import com.rangon.en_mmgeologydictionary.data.cache.AppDataCache;
import com.rangon.en_mmgeologydictionary.data.service.WordDAL;

/**
 * Created by win on 7/23/17.
 */

public class WordDataStoreFactory {

    private AppDataCache appDataCache;

    public WordDataStoreFactory(AppDataCache appDataCache) {
        this.appDataCache = appDataCache;
    }

    public WordsDataStore create(WordDAL wordDAL) {
        if (appDataCache.isCached()) {
            return new WordsLocalDataStore(wordDAL,appDataCache);
        } else {
            return new WordsCloudDataStore(wordDAL,appDataCache);
        }
    }
}
