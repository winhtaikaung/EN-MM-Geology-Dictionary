package com.rangon.en_mmgeologydictionary.domain.interactors.impl;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetWordInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.base.AbstractInteractor;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.Word;

import io.reactivex.Observable;

/**
 * Created by win on 7/29/17.
 */

public class GetWordInteractorImpl extends AbstractInteractor implements GetWordInteractor {
    private WordRepository mRepository;
    private Callback mCallback;
    private String mWord;
    private String mWordId;

    public GetWordInteractorImpl(Executor threadExecutor, MainThread mainThread, WordRepository wordRepository, String word, String id, Callback callback) {
        super(threadExecutor, mainThread);
        mRepository = wordRepository;
        mCallback = callback;
        this.mWord = word;
        this.mWordId = id;

    }

    @Override
    public void run() {
        final Observable<Word> wordObservable = mRepository.getWord(mWord, mWordId);
        mMainThread.post(() -> {
            mCallback.onWordRetrieved(wordObservable);
        });
    }
}
