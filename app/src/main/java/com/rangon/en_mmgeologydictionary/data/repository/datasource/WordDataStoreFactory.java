package com.rangon.en_mmgeologydictionary.data.repository.datasource;

/**
 * Created by win on 7/23/17.
 */

public class WordDataStoreFactory {


    public WordDataStoreFactory() {

    }

    public WordsDataStore create() {
        return new WordsLocalDataStore();
    }
}
