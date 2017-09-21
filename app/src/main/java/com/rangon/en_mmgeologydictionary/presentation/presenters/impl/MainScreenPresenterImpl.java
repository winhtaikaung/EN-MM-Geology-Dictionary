package com.rangon.en_mmgeologydictionary.presentation.presenters.impl;

import android.util.Log;

import com.rangon.en_mmgeologydictionary.data.repository.ApiConfigDataRepository;
import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetApiConfigListInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetWordListInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.GetApiConfigListInteractorImpl;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.GetWordListInteractorImpl;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.model.configrestmodels.ApiConfig;
import com.rangon.en_mmgeologydictionary.presentation.presenters.AbstractPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.MainScreenPresenter;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by win on 9/16/17.
 */

public class MainScreenPresenterImpl extends AbstractPresenter implements MainScreenPresenter, GetWordListInteractor.Callback, GetApiConfigListInteractor.Callback {

    private MainScreenPresenter.View mView;
    private WordRepository mWordRepository;
    private ApiConfigDataRepository mApiConfigRepository;
    private int counter = 0;

    public MainScreenPresenterImpl(Executor executor, MainThread mainThread, View view, WordRepository repository, ApiConfigDataRepository apiConfigDataRepository) {
        super(executor, mainThread);
        this.mView = view;
        this.mWordRepository = repository;
        this.mApiConfigRepository = apiConfigDataRepository;
    }

    @Override
    public void loadInitialData(int page, int limit) {
        GetApiConfigListInteractor getApiConfigListInteractor = new GetApiConfigListInteractorImpl(mExecutor, mMainThread, mApiConfigRepository, page, limit, this);
        getApiConfigListInteractor.execute();
    }

    @Override
    public void loadWordList(int page, int limit) {
        //TODO load random wordlist
    }

    @Override
    public void onWordListRetrieved(Observable<List<Word>> wordListObservable) {

        wordListObservable.doOnError(throwable -> {
            mView.showError(throwable.getMessage());
        }).subscribe(words -> mView.onWordListLoaded(words));
    }

    @Override
    public void onApiConfigListReceived(Observable<List<ApiConfig>> apiconfigList) {
        apiconfigList.doOnError(throwable ->
                mView.onLoadInitialData(false))
                .subscribe(apiConfigs -> {
                    for (ApiConfig config : apiConfigs) {
                        for (int i = 0; i <= config.getPageCount(); i++) {
                            Log.e("PAGE-->",String.valueOf(i));
                            GetWordListInteractor getWordListInteractor = new GetWordListInteractorImpl(mExecutor,
                                    mMainThread,
                                    mWordRepository, config.getWordIndex(), i, 100, MainScreenPresenterImpl.this);
                            getWordListInteractor.execute();
                        }
                        Thread.sleep(500);
                    }
                });
    }
}
