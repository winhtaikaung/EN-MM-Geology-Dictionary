package com.rangon.en_mmgeologydictionary.data.repository.datasource;

import com.rangon.en_mmgeologydictionary.data.service.WordDAL;

/**
 * Created by win on 7/23/17.
 */

public class WordDataStoreFactory {


    public WordDataStoreFactory() {

    }

    public WordsDataStore create(WordDAL wordDAL) {
        return new WordsCloudDataStore(wordDAL);
    }
}
