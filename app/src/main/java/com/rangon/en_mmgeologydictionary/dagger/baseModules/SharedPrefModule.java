package com.rangon.en_mmgeologydictionary.dagger.baseModules;

import android.content.Context;

import com.rangon.en_mmgeologydictionary.data.sharedPref.MySharedPref;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefModule {
    private Context mContext;

    public SharedPrefModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    public MySharedPref provideMySharedPref()

    {
        return new MySharedPref(mContext);
    }
}
