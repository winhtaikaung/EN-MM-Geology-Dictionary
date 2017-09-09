package domain.interactors.impl;

import java.util.List;

import domain.executor.Executor;
import domain.executor.MainThread;
import domain.interactors.GetWordListInteractor;
import domain.interactors.base.AbstractInteractor;
import domain.repository.WordRepository;
import io.reactivex.Observable;
import model.Word;

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
