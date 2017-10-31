package com.rangon.en_mmgeologydictionary.dagger.baseModules;

import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.threading.MainThreadImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by winhtaikaung on 31/10/17.
 */
@Module
public class ThreadModule {

    @Provides
    @Singleton
    public MainThread provideMainThread() {
        return MainThreadImpl.getInstance();
    }
}
