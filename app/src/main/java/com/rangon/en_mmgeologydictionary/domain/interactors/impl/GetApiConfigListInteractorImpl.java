package com.rangon.en_mmgeologydictionary.domain.interactors.impl;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetApiConfigListInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.base.AbstractInteractor;
import com.rangon.en_mmgeologydictionary.domain.repository.APIConfigRepository;
import com.rangon.en_mmgeologydictionary.model.configrestmodels.ApiConfig;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by win on 9/16/17.
 */

public class GetApiConfigListInteractorImpl extends AbstractInteractor implements GetApiConfigListInteractor {
    private APIConfigRepository mApiconfigRepository;
    private Callback mCallback;
    private int mPage;
    private int mLimit;

    /**
     *
     * @param threadExecutor
     * @param mainThread
     * @param repository
     * @param page
     * @param limit
     * @param callback
     */
    public GetApiConfigListInteractorImpl(Executor threadExecutor, MainThread mainThread, APIConfigRepository repository, int page, int limit, GetApiConfigListInteractor.Callback callback) {
        super(threadExecutor, mainThread);
        mApiconfigRepository = repository;
        mCallback = callback;
        mPage = page;
        mLimit = limit;
    }

    @Override
    public void run() {
        final Observable<List<ApiConfig>> apiconfigObservable = mApiconfigRepository.getAPIConfigList(mPage, mLimit);
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onApiConfigListReceived(apiconfigObservable);
            }
        });
    }
}
