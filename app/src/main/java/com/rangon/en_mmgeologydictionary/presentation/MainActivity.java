package com.rangon.en_mmgeologydictionary.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rangon.en_mmgeologydictionary.R;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCacheImpl;
import com.rangon.en_mmgeologydictionary.data.repository.ApiConfigDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.WordsDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.ApiConfigDataStoreFactory;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.WordDataStoreFactory;
import com.rangon.en_mmgeologydictionary.data.service.WordDAL;
import com.rangon.en_mmgeologydictionary.domain.executor.impl.ThreadExecutor;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.presenters.MainScreenPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.impl.MainScreenPresenterImpl;
import com.rangon.en_mmgeologydictionary.threading.MainThreadImpl;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainScreenPresenter.View {

    private MainScreenPresenter mMainScreenPresenter;
    private WordDataStoreFactory mWordDataStoreFactory;
    private WordsDataRepository mWordDataRepository;

    private ApiConfigDataStoreFactory mApiConfigDataStoreFactory;
    private ApiConfigDataRepository mApiConfigDataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiConfigDataStoreFactory = new ApiConfigDataStoreFactory();
        mWordDataStoreFactory = new WordDataStoreFactory(new AppDataCacheImpl(this));

        mWordDataRepository = new WordsDataRepository(mWordDataStoreFactory, new WordDAL(this));
        mApiConfigDataRepository = new ApiConfigDataRepository(mApiConfigDataStoreFactory);

        mMainScreenPresenter = new MainScreenPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                this, mWordDataRepository, mApiConfigDataRepository);
        if(!new AppDataCacheImpl(this).isCached()) {
            mMainScreenPresenter.loadInitialData(1, 100);
        }
    }

    @Override
    public void onLoadInitialData(boolean isLoaded) {

    }

    @Override
    public void onWordListLoaded(List<Word> wordList) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void hideError(String message) {

    }
}
