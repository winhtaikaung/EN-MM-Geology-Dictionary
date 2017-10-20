package com.rangon.en_mmgeologydictionary.presentation.presenters.impl;

import com.rangon.en_mmgeologydictionary.domain.executor.Executor;
import com.rangon.en_mmgeologydictionary.domain.executor.MainThread;
import com.rangon.en_mmgeologydictionary.domain.interactors.GetRecentWordListInteractor;
import com.rangon.en_mmgeologydictionary.domain.interactors.impl.GetRecentWordListInteractorImpl;
import com.rangon.en_mmgeologydictionary.domain.repository.WordRepository;
import com.rangon.en_mmgeologydictionary.model.Word;
import com.rangon.en_mmgeologydictionary.presentation.presenters.AbstractPresenter;
import com.rangon.en_mmgeologydictionary.presentation.presenters.BookmarkViewPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by winhtaikaung on 19/10/17.
 */

public class BookmarkViewPresenterImpl extends AbstractPresenter implements BookmarkViewPresenter, GetRecentWordListInteractor.Callback {
    private BookmarkViewPresenter.View mView;
    private WordRepository mRepository;

    public BookmarkViewPresenterImpl(Executor executor, MainThread mainThread, BookmarkViewPresenter.View view, WordRepository wordRepo) {
        super(executor, mainThread);
        this.mView = view;
        this.mRepository = wordRepo;

    }

    @Override
    public void onRecentWordListRetrieved(Observable<List<Word>> wordListObservable) {
        wordListObservable
                .doOnError(throwable -> mView.showError(throwable.getMessage()))
                .subscribe(new Consumer<List<Word>>() {
                    @Override
                    public void accept(List<Word> words) throws Exception {
                        mView.onBookMarkDataLoaded(words);

                    }
                });
        mView.hideProgress();
        mView.hideError("");

    }

    @Override
    public void onEmptyItemReceived() {
        mView.showError("");
    }

    @Override
    public void loadInitialData() {
        mView.onInitialDataLoaded();
    }

    @Override
    public void loadBookmarksData(int limit, int page) {
        String[] tables = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        GetRecentWordListInteractor interactor = new GetRecentWordListInteractorImpl(mExecutor, mMainThread, mRepository, tables, limit, page, this);
        interactor.execute();
    }
}
