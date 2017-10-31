package com.rangon.en_mmgeologydictionary.dagger.baseModules;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.impl.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by winhtaikaung on 31/10/17.
 */
@Module
public class ExecutorModule {

    @Provides
    @Singleton
    public Executor provideExecutorModule() {
        return ThreadExecutor.getInstance();
    }
}
