package com.rangon.en_mmgeologydictionary;

import android.app.Application;

import com.rangon.en_mmgeologydictionary.dagger.baseModules.ExecutorModule;
import com.rangon.en_mmgeologydictionary.dagger.baseModules.SharedPrefModule;
import com.rangon.en_mmgeologydictionary.dagger.baseModules.ThreadModule;
import com.rangon.en_mmgeologydictionary.dagger.baseModules.WordDataModule;
import com.rangon.en_mmgeologydictionary.dagger.component.AppComponent;
import com.rangon.en_mmgeologydictionary.dagger.component.DaggerAppComponent;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCache;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCacheImpl;
import com.rangon.en_mmgeologydictionary.util.DBHelper;

/**
 * Created by winhtaikaung on 14/9/17.
 */

public class MainApplication extends Application {

    private static MainApplication instance;

    private AppComponent applicationComponent;

    public static MainApplication getInstance() {
        return instance;
    }

    public static void setInstance(MainApplication instance) {
        MainApplication.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppDataCache cache = new AppDataCacheImpl(this);
        if (!cache.isCached()) {
            DBHelper dbHelper = new DBHelper(this);
            dbHelper.MakeDB();
        }
        setInstance(this);
        applicationComponent = DaggerAppComponent.builder().executorModule(new ExecutorModule())
                .threadModule(new ThreadModule())
                .sharedPrefModule(new SharedPrefModule(this.getApplicationContext()))
                .wordDataModule(new WordDataModule(this.getApplicationContext())).build();
    }

    public AppComponent getApplicationComponent() {
        return applicationComponent;
    }
}
