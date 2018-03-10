package com.rangon.en_mmgeologydictionary.dagger.component;

import com.rangon.en_mmgeologydictionary.MainApplication;
import com.rangon.en_mmgeologydictionary.dagger.SearchFragmentModule;
import com.rangon.en_mmgeologydictionary.dagger.baseModules.ExecutorModule;
import com.rangon.en_mmgeologydictionary.dagger.baseModules.SharedPrefModule;
import com.rangon.en_mmgeologydictionary.dagger.baseModules.ThreadModule;
import com.rangon.en_mmgeologydictionary.dagger.baseModules.WordDataModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by winhtaikaung on 31/10/17.
 */
@Component(modules = {
        ExecutorModule.class,
        ThreadModule.class,
        WordDataModule.class,
        SharedPrefModule.class

})
@Singleton
public interface AppComponent {
    void inject(MainApplication mainApplication);

    SearchFragmentComponent plus(SearchFragmentModule searchFragmentModule);
}
