package com.rangon.en_mmgeologydictionary.presentation.presenters.impl;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetLikelyWordsInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.GetLikelyWordsInteractorImpl;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.presenters.AbstractPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.SearchScreenPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by winhtaikaung on 10/10/17.
 */

public class SearchScreenPresenterImpl extends AbstractPresenter implements SearchScreenPresenter, GetLikelyWordsInteractor.Callback {
    private SearchScreenPresenter.View mView;
    private WordRepository mWordsDataRepository;

    public SearchScreenPresenterImpl(Executor executor, MainThread mainThread, View view, WordRepository wordRepo) {
        super(executor, mainThread);
        this.mView = view;
        this.mWordsDataRepository = wordRepo;
    }

    @Override
    public void loadInitialData() {
        mView.onLoadInitialData();
    }

    @Override
    public void searchTextEnters(String searchText) {
        mView.onSearchTextReceived(searchText);
    }

    @Override
    public void loadLikelyWordList(String searchText, int limit, int page) {
        GetLikelyWordsInteractor getLikelyWordsInteractor = new GetLikelyWordsInteractorImpl(mExecutor, mMainThread, mWordsDataRepository,
                searchText, limit, page, this);
        getLikelyWordsInteractor.execute();
    }

    @Override
    public void onLikelyWordsRetrieved(Observable<List<Word>> likelyWordsListObservable) {
        likelyWordsListObservable.doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.showError(throwable.getMessage());
            }
        }).subscribe(wordList -> {
            if (wordList.size() == 0) {
//                mView.showError("No words Found");
                mView.onLikelyWordListLoaded(wordList);
            } else {
                mView.hideError("");
                mView.onLikelyWordListLoaded(wordList);
            }
        });
    }
}
