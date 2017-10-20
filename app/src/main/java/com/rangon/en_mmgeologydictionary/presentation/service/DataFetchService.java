package com.rangon.en_mmgeologydictionary.presentation.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import com.rangon.en_mmgeologydictionary.data.DAL.WordDAL;
import com.rangon.en_mmgeologydictionary.data.cache.AppDataCacheImpl;
import com.rangon.en_mmgeologydictionary.data.repository.ApiConfigDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.WordsDataRepository;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.ApiConfigDataStoreFactory;
import com.rangon.en_mmgeologydictionary.data.repository.datasource.WordDataStoreFactory;
import com.rangon.en_mmgeologydictionary.domain.executor.impl.ThreadExecutor;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.presenters.MainScreenPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.impl.MainScreenPresenterImpl;
import com.rangon.en_mmgeologydictionary.threading.MainThreadImpl;

import java.util.List;

/**
 * Created by winhtaikaung on 20/10/17.
 */

public class DataFetchService extends IntentService implements MainScreenPresenter.View {


    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;


    private MainScreenPresenter mMainScreenPresenter;
    private WordDataStoreFactory mWordDataStoreFactory;
    private WordsDataRepository mWordDataRepository;

    private ApiConfigDataStoreFactory mApiConfigDataStoreFactory;
    private ApiConfigDataRepository mApiConfigDataRepository;
    ResultReceiver receiver;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public DataFetchService() {
        super(DataFetchService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        mApiConfigDataStoreFactory = new ApiConfigDataStoreFactory();
        mWordDataStoreFactory = new WordDataStoreFactory(new AppDataCacheImpl(this));

        mWordDataRepository = new WordsDataRepository(mWordDataStoreFactory, new WordDAL(this));
        mApiConfigDataRepository = new ApiConfigDataRepository(mApiConfigDataStoreFactory);
        mMainScreenPresenter = new MainScreenPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(),
                this, mWordDataRepository, mApiConfigDataRepository);
        if (intent != null) {
            receiver = intent.getParcelableExtra("receiver");
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);
        }
        mMainScreenPresenter.loadInitialData(1, 100);

    }

    @Override
    public void onLoadInitialData(boolean isLoaded) {

        receiver.send(STATUS_FINISHED, Bundle.EMPTY);
    }

    @Override
    public void onWordListLoaded(List<Word> wordList) {

    }

    @Override
    public void onInit() {

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
