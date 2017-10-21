package com.rangon.en_mmgeologydictionary.domain.interactors.impl;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetRelatedWordsInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.base.AbstractInteractor;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.Word;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 16/7/17.
 */

public class GetRelatedWordsInteractorImpl extends AbstractInteractor implements GetRelatedWordsInteractor {
    private WordRepository mWordRepository;
    private Callback mCallback;
    private String mWord;
    private String[] mTableNames;
    private int mLimit;


    /**
     * @param threadExecutor
     * @param mainThread
     * @param wordRespository
     * @param tableNames
     * @param word
     * @param limit
     * @param callback
     */
    public GetRelatedWordsInteractorImpl(Executor threadExecutor, MainThread mainThread, WordRepository wordRespository, String[] tableNames, String word, int limit, Callback callback) {
        super(threadExecutor, mainThread);
        mWordRepository = wordRespository;
        mCallback = callback;
        mWord = word;
        mLimit = limit;
        mTableNames = tableNames;
    }

    @Override
    public void run() {
        final Observable<List<Word>> wordListObservable = mWordRepository.getRelatedWord(mTableNames, mWord, mLimit);
        mMainThread.post(() -> {
            mCallback.onRelatedWordsRetrieved(wordListObservable);
        });


    }
}
