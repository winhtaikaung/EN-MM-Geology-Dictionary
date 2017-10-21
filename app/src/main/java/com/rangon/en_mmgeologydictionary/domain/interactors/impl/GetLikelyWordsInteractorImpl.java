package com.rangon.en_mmgeologydictionary.domain.interactors.impl;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetLikelyWordsInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.base.AbstractInteractor;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.Word;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public class GetLikelyWordsInteractorImpl extends AbstractInteractor implements GetLikelyWordsInteractor {
    private WordRepository mWordRepository;
    private Callback mCallback;
    private String mWord;
    private int mLimit;
    private int mPage;


    /**
     * @param threadExecutor
     * @param mainThread
     * @param wordRespository
     * @param word
     * @param callback
     */
    public GetLikelyWordsInteractorImpl(Executor threadExecutor, MainThread mainThread, WordRepository wordRespository, String word,int limit,int page, Callback callback) {
        super(threadExecutor, mainThread);
        mWordRepository = wordRespository;
        mCallback = callback;
        mWord = word;
        mLimit = limit;
        mPage = page;
    }

    @Override
    public void run() {
        final Observable<List<Word>> wordListObservable = mWordRepository.getLikelyWord(mWord,mLimit,mPage);
        mMainThread.post(() -> {
            mCallback.onLikelyWordsRetrieved(wordListObservable);
        });


    }
}
