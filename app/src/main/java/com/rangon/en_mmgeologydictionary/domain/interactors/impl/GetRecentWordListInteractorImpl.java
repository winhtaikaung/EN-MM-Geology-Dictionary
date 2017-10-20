package com.rangon.en_mmgeologydictionary.domain.interactors.impl;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetRecentWordListInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.base.AbstractInteractor;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.Word;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by win on 9/10/17.
 */

public class GetRecentWordListInteractorImpl extends AbstractInteractor implements GetRecentWordListInteractor {

    private WordRepository mRepository;
    private Callback mCallback;
    private String[] mTableNames;
    private int mPage;
    private int mLimit;

    public GetRecentWordListInteractorImpl(Executor threadExecutor, MainThread mainThread, WordRepository repository, String[] wordIndex, int page, int limit, Callback callback) {
        super(threadExecutor, mainThread);
        mRepository = repository;
        mCallback = callback;
        mTableNames = wordIndex;
        mPage = page;
        mLimit = limit;
    }

    @Override
    public void run() {
        final Observable<List<Word>> wordObservable = mRepository.getRecentWord(mTableNames, mPage, mLimit);
        mMainThread.post(() -> {
            wordObservable.subscribe(new Consumer<List<Word>>() {
                @Override
                public void accept(List<Word> words) throws Exception {
                    if (mPage == 1) {
                        if (words.size() == 0) {
                            mCallback.onEmptyItemReceived();
                        } else {
                            mCallback.onRecentWordListRetrieved(wordObservable);
                        }
                    }else{
                        mCallback.onRecentWordListRetrieved(wordObservable);
                    }
                }
            });

        });
    }
}
