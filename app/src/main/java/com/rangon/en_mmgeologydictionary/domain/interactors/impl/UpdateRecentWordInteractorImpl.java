package com.rangon.en_mmgeologydictionary.domain.interactors.impl;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.UpdateRecentWordInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.base.AbstractInteractor;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 18/10/17.
 */

public class UpdateRecentWordInteractorImpl extends AbstractInteractor implements UpdateRecentWordInteractor {

    private WordRepository mRepository;
    private Callback mCallback;
    private String mWordId;
    private String mWord;

    /**
     * @param threadExecutor
     * @param mainThread
     * @param repository
     * @param word
     * @param id
     * @param callback
     */
    public UpdateRecentWordInteractorImpl(Executor threadExecutor, MainThread mainThread, WordRepository repository, String word, String id, Callback callback) {
        super(threadExecutor, mainThread);
        mRepository = repository;
        mCallback = callback;
        mWordId = id;
        mWord = word;
    }

    @Override
    public void run() {
        final Observable<Boolean> wordObservable = mRepository.updateRecentWord(mWord, mWordId);
        mMainThread.post(() -> {
            mCallback.onUpdateRecentWord(wordObservable);
        });
    }
}
