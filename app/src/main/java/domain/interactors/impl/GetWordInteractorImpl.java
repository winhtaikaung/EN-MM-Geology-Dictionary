package domain.interactors.impl;

import domain.executor.Executor;
import domain.executor.MainThread;
import domain.interactors.GetWordInteractor;
import domain.interactors.base.AbstractInteractor;
import domain.repository.WordRepository;
import io.reactivex.Observable;
import model.Word;

/**
 * Created by win on 7/29/17.
 */

public class GetWordInteractorImpl extends AbstractInteractor implements GetWordInteractor {
    private WordRepository mRepository;
    private Callback mCallback;
    private String mWord;

    public GetWordInteractorImpl(Executor threadExecutor, MainThread mainThread, WordRepository wordRepository, String word, Callback callback) {
        super(threadExecutor, mainThread);
        mRepository = wordRepository;
        mCallback = callback;
        this.mWord = word;

    }

    @Override
    public void run() {
        final Observable<Word> wordObservable = mRepository.getWord(mWord);
        mMainThread.post(() -> {
            mCallback.onWordRetrieved(wordObservable);
        });
    }
}
