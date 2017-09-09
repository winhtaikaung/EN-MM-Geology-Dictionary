package presentation.presenters.impl;

import domain.executor.Executor;
import domain.executor.MainThread;
import domain.interactors.GetWordInteractor;
import domain.interactors.impl.GetWordInteractorImpl;
import domain.repository.WordRepository;
import io.reactivex.Observable;
import model.Word;
import presentation.presenters.AbstractPresenter;
import presentation.presenters.WordDetailPresenter;

/**
 * Created by winhtaikaung on 29/7/17.
 */

public class WordDetailPresenterImpl extends AbstractPresenter implements WordDetailPresenter, GetWordInteractor.Callback {

    private WordDetailPresenter.View mView;
    private WordRepository mWordRepository;

    public WordDetailPresenterImpl(Executor executor, MainThread mainThread, View view, WordRepository repository) {
        super(executor, mainThread);
        this.mView = view;
        this.mWordRepository = repository;
    }

    @Override
    public void getWordDetail(String word) {
        GetWordInteractor getWordInteractor = new GetWordInteractorImpl(mExecutor, mMainThread, mWordRepository, word, this);
        getWordInteractor.execute();
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
}
