package com.rangon.en_mmgeologydictionary.presentation.presenters.impl;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetWordInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.UpdateRecentWordInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.GetWordInteractorImpl;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.UpdateRecentWordInteractorImpl;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.presenters.AbstractPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.WordDetailPresenter;

import io.reactivex.Observable;

/**
 * Created by winhtaikaung on 29/7/17.
 */

public class WordDetailPresenterImpl extends AbstractPresenter implements WordDetailPresenter, GetWordInteractor.Callback, UpdateRecentWordInteractor.Callback {

    private WordDetailPresenter.View mView;
    private WordRepository mWordRepository;

    public WordDetailPresenterImpl(Executor executor, MainThread mainThread, View view, WordRepository repository) {
        super(executor, mainThread);
        this.mView = view;
        this.mWordRepository = repository;
    }

    @Override
    public void initialize() {
        mView.onInit();
    }

    @Override
    public void getWordDetail(String word, String id) {
        GetWordInteractor getWordInteractor = new GetWordInteractorImpl(mExecutor, mMainThread, mWordRepository, word, id, this);
        getWordInteractor.execute();
    }

    @Override
    public void updateRecentWord(String word, String id) {
        UpdateRecentWordInteractor updateRecentWordInteractor = new UpdateRecentWordInteractorImpl(
                mExecutor, mMainThread, mWordRepository, word, id, this
        );
        updateRecentWordInteractor.execute();
    }

    @Override
    public void onWordRetrieved(Observable<Word> wordObservable) {
        wordObservable.subscribe(word -> mView.onWordRetrieved(word));
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

    @Override
    public void onUpdateRecentWord(Observable<Boolean> wordUpdatedStatus) {
        wordUpdatedStatus.subscribe(aBoolean -> mView.onWordRecentUpdated(aBoolean));
    }
}
