package com.rangon.en_mmgeologydictionary.dagger.baseModules;

import android.content.Context;

import com.rangon.en_mmgeologydictionary.data.DAL.WordDAL;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCache;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCacheImpl;
import com.rangon.en_mmgeologydictionary.data.repository.WordsDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.WordDataStoreFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by winhtaikaung on 31/10/17.
 */
@Module
public class WordDataModule {

    private Context mContext;


    public WordDataModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    public AppDataCache provideAppDataCache() {
        return new AppDataCacheImpl(mContext);
    }

    @Provides
    @Singleton
    public WordDAL provideWordDal() {
        return new WordDAL(mContext);
    }

    @Provides
    @Singleton
    public WordDataStoreFactory provideWordDataStoreFactory(AppDataCache appDataCache) {
        return new WordDataStoreFactory(appDataCache);
    }


    @Provides
    @Singleton
    public WordsDataRepository provideWordDataRepository(WordDataStoreFactory factory, WordDAL wordDAL) {
        return new WordsDataRepository(factory, wordDAL);
    }

}
