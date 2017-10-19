package com.rangon.en_mmgeologydictionary.domain.interactors.impl;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.DeleteWordTablesInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.base.AbstractInteractor;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 15/10/17.
 */

public class DeleteWordTablesInteractorImpl extends AbstractInteractor implements DeleteWordTablesInteractor {

    private WordRepository mRepository;
    private Callback mCallback;
    private String[] mTableName;

    /**
     *
     * @param threadExecutor
     * @param mainThread
     * @param repository
     * @param tableName
     * @param callback
     */
    public DeleteWordTablesInteractorImpl(Executor threadExecutor, MainThread mainThread, WordRepository repository, String[] tableName, Callback callback) {
        super(threadExecutor, mainThread);
        mRepository = repository;
        mCallback = callback;
        mTableName = tableName;
    }

    @Override
    public void run() {
        final Observable<Boolean> deletedStatusObservable = mRepository.deleteWordTables(mTableName);
        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.onWordTableDeleted(deletedStatusObservable);
            }
        });
    }
}
