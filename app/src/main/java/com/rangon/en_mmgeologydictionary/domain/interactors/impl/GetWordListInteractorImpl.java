package com.rangon.en_mmgeologydictionary.domain.interactors.impl;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetWordListInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.base.AbstractInteractor;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.Word;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by win on 9/10/17.
 */

public class GetWordListInteractorImpl extends AbstractInteractor implements GetWordListInteractor {

    private WordRepository mRepository;
    private Callback mCallback;
    private String mWordIndex;
    private int mPage;
    private int mLimit;

    public GetWordListInteractorImpl(Executor threadExecutor, MainThread mainThread, WordRepository repository, String wordIndex, int page, int limit, Callback callback) {
        super(threadExecutor, mainThread);
        mRepository = repository;
        mCallback = callback;
        mWordIndex = wordIndex;
        mPage = page;
        mLimit = limit;
    }

    @Override
    public void run() {
        final Observable<List<Word>> wordObservable = mRepository.getWordList(mWordIndex, mPage, mLimit);

        mMainThread.post(() -> {
            mCallback.onWordListRetrieved(wordObservable);
        });
    }
}
